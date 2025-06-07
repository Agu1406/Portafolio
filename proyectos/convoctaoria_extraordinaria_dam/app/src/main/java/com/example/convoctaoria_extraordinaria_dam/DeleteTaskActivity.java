package com.example.convoctaoria_extraordinaria_dam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.convoctaoria_extraordinaria_dam.adapters.TaskAdapter;
import com.example.convoctaoria_extraordinaria_dam.database.DatabaseHelper;
import com.example.convoctaoria_extraordinaria_dam.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad para eliminar tareas del sistema.
 * Esta actividad muestra una lista de tareas existentes y permite al usuario
 * seleccionar y eliminar una tarea, con una confirmación previa.
 */
public class DeleteTaskActivity extends AppCompatActivity {
    private static final String TAG = "DeleteTaskActivity";
    
    /** Lista de tareas disponibles para eliminar */
    private List<Task> taskList;
    
    /** Componentes de la interfaz de usuario */
    private ListView listViewTasksToDelete;
    private Button btnDeleteTask, btnCancel;
    
    /** Tarea seleccionada actualmente */
    private int selectedPosition = -1;
    
    /** Helper para la gestión de la base de datos */
    private DatabaseHelper dbHelper;
    private TaskAdapter adapter;

    /**
     * Se llama cuando la actividad se crea por primera vez.
     * Inicializa la interfaz de usuario y configura los listeners de eventos.
     * @param savedInstanceState Estado guardado de la actividad, si existe
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_task);

        // Inicializar la base de datos
        dbHelper = new DatabaseHelper(this);

        // Inicializar vistas
        initializeViews();
        
        // Cargar tareas
        loadTasks();
        
        // Configurar listeners
        setupListeners();
    }

    /**
     * Inicializa las vistas de la interfaz de usuario.
     */
    private void initializeViews() {
        listViewTasksToDelete = findViewById(R.id.listViewTasksToDelete);
        btnDeleteTask = findViewById(R.id.btnDeleteTask);
        btnCancel = findViewById(R.id.btnCancel);
        taskList = new ArrayList<>();
        adapter = new TaskAdapter(this, taskList);
        listViewTasksToDelete.setAdapter(adapter);
    }

    /**
     * Configura los listeners de eventos para los botones.
     */
    private void setupListeners() {
        listViewTasksToDelete.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            // Cambiar el color de fondo del item seleccionado
            for (int i = 0; i < parent.getChildCount(); i++) {
                parent.getChildAt(i).setBackgroundResource(android.R.color.transparent);
            }
            view.setBackgroundResource(android.R.color.holo_blue_light);
        });

        btnDeleteTask.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                showDeleteConfirmationDialog();
            } else {
                Toast.makeText(this, "Por favor, seleccione una tarea", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    /**
     * Muestra un diálogo de confirmación antes de eliminar la tarea.
     */
    private void showDeleteConfirmationDialog() {
        Task selectedTask = taskList.get(selectedPosition);
        new AlertDialog.Builder(this)
            .setTitle("Confirmar eliminación")
            .setMessage("¿Está seguro de que desea eliminar la tarea: " + selectedTask.getName() + "?")
            .setPositiveButton("Sí", (dialog, which) -> deleteTask())
            .setNegativeButton("No", null)
            .show();
    }

    /**
     * Elimina la tarea seleccionada de la base de datos.
     */
    private void deleteTask() {
        if (selectedPosition == -1) return;

        Task taskToDelete = taskList.get(selectedPosition);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            int deletedRows = db.delete(
                DatabaseHelper.TABLE_TASKS,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(taskToDelete.getId())}
            );

            if (deletedRows > 0) {
                // Mostrar mensaje de éxito
                Toast.makeText(this, "Tarea eliminada correctamente", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Tarea eliminada: " + taskToDelete.getName());
                
                // Actualizar la lista
                taskList.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                selectedPosition = -1;
                
                // Volver a la actividad principal
                finish();
            } else {
                Toast.makeText(this, "Error al eliminar la tarea", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error al eliminar la tarea de la base de datos");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al eliminar la tarea", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al eliminar la tarea: " + e.getMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Carga las tareas desde la base de datos.
     */
    private void loadTasks() {
        taskList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        Cursor cursor = db.query(
            DatabaseHelper.TABLE_TASKS,
            null,
            null,
            null,
            null,
            null,
            DatabaseHelper.COLUMN_ID + " DESC"
        );

        while (cursor.moveToNext()) {
            Task task = new Task(
                cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ASSIGNED_PERSON)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE))
            );
            taskList.add(task);
        }

        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
} 