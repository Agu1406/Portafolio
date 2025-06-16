package com.example.convoctaoria_extraordinaria_dam;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.RequiresApi;

import com.example.convoctaoria_extraordinaria_dam.adapters.TaskAdapter;
import com.example.convoctaoria_extraordinaria_dam.database.DatabaseHelper;
import com.example.convoctaoria_extraordinaria_dam.models.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Actividad principal de la aplicación.
 * Esta actividad muestra la lista de tareas y proporciona acceso a todas las funcionalidades
 * de la aplicación: añadir, eliminar, modificar tareas, realizar llamadas y exportar datos.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    /** Código de solicitud para permisos de almacenamiento */
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 2;

    /** Componentes de la interfaz de usuario */
    private ListView listViewTasks;
    private Button btnAddTask, btnDeleteTask, btnModifyTask, btnCall, btnExportFile, btnWhatsApp;
    private TaskAdapter adapter;
    private DatabaseHelper dbHelper;
    private List<Task> taskList;

    /**
     * Se llama cuando la actividad se crea por primera vez.
     * Inicializa la interfaz de usuario y configura los listeners de eventos.
     * @param savedInstanceState Estado guardado de la actividad, si existe
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);

            // Inicializar la base de datos
            dbHelper = new DatabaseHelper(this);

            // Inicializar vistas
            initializeViews();
            
            // Inicializar lista de tareas
            taskList = new ArrayList<>();
            adapter = new TaskAdapter(this, taskList);
            listViewTasks.setAdapter(adapter);

            // Cargar tareas
            loadTasks();

            // Configurar listeners
            setupListeners();
        } catch (Exception e) {
            Log.e(TAG, "Error en onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Error al iniciar la aplicación: " + e.getMessage(), 
                Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * Inicializa las vistas de la interfaz de usuario.
     * @throws RuntimeException si alguna vista no se encuentra
     */
    private void initializeViews() {
        try {
            listViewTasks = findViewById(R.id.listViewTasks);
            if (listViewTasks == null) throw new RuntimeException("listViewTasks no encontrado");
            
            btnAddTask = findViewById(R.id.btnAddTask);
            if (btnAddTask == null) throw new RuntimeException("btnAddTask no encontrado");
            
            btnDeleteTask = findViewById(R.id.btnDeleteTask);
            if (btnDeleteTask == null) throw new RuntimeException("btnDeleteTask no encontrado");
            
            btnModifyTask = findViewById(R.id.btnModifyTask);
            if (btnModifyTask == null) throw new RuntimeException("btnModifyTask no encontrado");
            
            btnCall = findViewById(R.id.btnCall);
            if (btnCall == null) throw new RuntimeException("btnCall no encontrado");
            
            btnExportFile = findViewById(R.id.btnExportFile);
            if (btnExportFile == null) throw new RuntimeException("btnExportFile no encontrado");
            
            btnWhatsApp = findViewById(R.id.btnWhatsApp);
            if (btnWhatsApp == null) throw new RuntimeException("btnWhatsApp no encontrado");
        } catch (Exception e) {
            Log.e(TAG, "Error al inicializar vistas: " + e.getMessage(), e);
            throw new RuntimeException("Error al inicializar vistas: " + e.getMessage(), e);
        }
    }

    /**
     * Configura los listeners de eventos para los botones.
     */
    private void setupListeners() {
        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        btnDeleteTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeleteTaskActivity.class);
            startActivity(intent);
        });

        btnModifyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CallTaskActivity.class);
                startActivity(intent);
            }
        });

        btnExportFile.setOnClickListener(v -> exportTasksToFile());

        btnWhatsApp.setOnClickListener(v -> {
            List<Task> tasks = dbHelper.getAllTasks();
            if (tasks.isEmpty()) {
                Toast.makeText(this, "No hay tareas para compartir", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder message = new StringBuilder();
            message.append("=== LISTA DE TAREAS ===\n\n");
            for (Task task : tasks) {
                message.append("Tarea: ").append(task.getName()).append("\n");
                message.append("Descripción: ").append(task.getDescription()).append("\n");
                message.append("Responsable: ").append(task.getAssignedPerson()).append("\n");
                message.append("Teléfono: ").append(task.getPhone()).append("\n");
                message.append("------------------------\n\n");
            }
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message.toString());
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            try {
                startActivity(sendIntent);
                Toast.makeText(this, "Compartiendo tareas por WhatsApp", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Tareas compartidas por WhatsApp");
            } catch (Exception e) {
                Toast.makeText(this, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error al compartir por WhatsApp: " + e.getMessage(), e);
            }
        });
    }

    /**
     * Carga las tareas desde la base de datos y las muestra en la lista.
     */
    private void loadTasks() {
        try {
            taskList.clear();
            List<Task> tasks = dbHelper.getAllTasks();
            taskList.addAll(tasks);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "Tareas cargadas exitosamente: " + taskList.size() + " tareas");
        } catch (Exception e) {
            Log.e(TAG, "Error al cargar tareas: " + e.getMessage(), e);
            Toast.makeText(this, "Error al cargar las tareas: " + e.getMessage(), 
                Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Se llama cuando la actividad se reanuda.
     * Recarga la lista de tareas para mostrar los cambios.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadTasks(); // Recargar tareas cuando se vuelve a la actividad
    }

    /**
     * Verifica si se tiene permiso de almacenamiento.
     * @return true si se tiene el permiso, false en caso contrario
     */
    private void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13 y superior: no necesitamos permisos específicos para escribir en Downloads
            exportTasksToFile();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 y 12: usar MediaStore sin permisos específicos
            exportTasksToFile();
        } else {
            // Android 10 y anteriores: necesitamos WRITE_EXTERNAL_STORAGE
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_REQUEST_CODE);
            } else {
                exportTasksToFile();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportTasksToFile();
            } else {
                Toast.makeText(this, "Se requiere permiso para exportar las tareas", 
                    Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Exporta las tareas a un archivo CSV en el almacenamiento externo.
     */
    private void exportTasksToFile() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(new Date());
            String fileName = "tareas_" + timestamp + ".txt";
            
            ContentValues values = new ContentValues();
            values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
            values.put(MediaStore.Downloads.MIME_TYPE, "text/plain");
            values.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/GestorTareas");

            ContentResolver resolver = getContentResolver();
            Uri uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);

            if (uri != null) {
                try (OutputStream os = resolver.openOutputStream(uri)) {
                    if (os != null) {
                        List<Task> tasks = dbHelper.getAllTasks();
                        
                        StringBuilder content = new StringBuilder();
                        content.append("=== LISTA DE TAREAS ===\n\n");
                        for (Task task : tasks) {
                            content.append("Tarea: ").append(task.getName()).append("\n");
                            content.append("Descripción: ").append(task.getDescription()).append("\n");
                            content.append("Responsable: ").append(task.getAssignedPerson()).append("\n");
                            content.append("Teléfono: ").append(task.getPhone()).append("\n");
                            content.append("------------------------\n\n");
                        }
                        
                        os.write(content.toString().getBytes());
                        os.flush();

                        String message = "Tareas exportadas correctamente a Descargas/GestorTareas/" + fileName;
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                        Log.i(TAG, "Tareas exportadas usando MediaStore: " + fileName);
                    }
                }
            } else {
                throw new IOException("No se pudo crear el archivo");
            }
        } catch (IOException e) {
            String errorMessage = "Error al exportar las tareas: " + e.getMessage();
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            Log.e(TAG, errorMessage, e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
} 