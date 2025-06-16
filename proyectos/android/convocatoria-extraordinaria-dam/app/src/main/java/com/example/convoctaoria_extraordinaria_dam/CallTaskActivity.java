package com.example.convoctaoria_extraordinaria_dam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.convoctaoria_extraordinaria_dam.database.DatabaseHelper;
import com.example.convoctaoria_extraordinaria_dam.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad para realizar llamadas telefónicas a los contactos de las tareas.
 * Esta actividad permite al usuario seleccionar una tarea de una lista
 * y realizar una llamada al número de teléfono asociado.
 */
public class CallTaskActivity extends AppCompatActivity {
    /** Código de solicitud para permisos de llamada */
    private static final int CALL_PERMISSION_CODE = 1;
    
    /** Lista de tareas disponibles para llamar */
    private List<Task> taskList;
    
    /** Componentes de la interfaz de usuario */
    private ListView listViewTasks;
    private Button btnCall;
    private Button btnCancel;
    
    /** Tarea seleccionada actualmente */
    private Task selectedTask;
    
    /** Helper para la gestión de la base de datos */
    private DatabaseHelper dbHelper;

    /**
     * Se llama cuando la actividad se crea por primera vez.
     * Inicializa la interfaz de usuario y configura los listeners de eventos.
     * @param savedInstanceState Estado guardado de la actividad, si existe
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_task);

        initializeViews();
        setupDatabase();
        loadTasks();
        setupListView();
        setupButtonListeners();
    }

    /**
     * Inicializa las vistas de la interfaz de usuario.
     */
    private void initializeViews() {
        listViewTasks = findViewById(R.id.listViewTasks);
        btnCall = findViewById(R.id.btnCall);
        btnCancel = findViewById(R.id.btnCancel);
        taskList = new ArrayList<>();
    }

    /**
     * Configura la conexión con la base de datos.
     */
    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
    }

    /**
     * Carga las tareas desde la base de datos.
     */
    private void loadTasks() {
        taskList.clear();
        taskList.addAll(dbHelper.getAllTasks());
    }

    /**
     * Configura el ListView y su adaptador.
     */
    private void setupListView() {
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);

        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            selectedTask = taskList.get(position);
            if (selectedTask != null && !selectedTask.getPhone().isEmpty()) {
                btnCall.setEnabled(true);
                Toast.makeText(this, "Tarea seleccionada: " + selectedTask.getName(), 
                    Toast.LENGTH_SHORT).show();
            } else {
                btnCall.setEnabled(false);
                Toast.makeText(this, "Esta tarea no tiene número de teléfono", 
                    Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Configura los listeners de eventos para los botones.
     */
    private void setupButtonListeners() {
        btnCall.setOnClickListener(v -> {
            if (selectedTask != null) {
                makePhoneCall();
            } else {
                Toast.makeText(this, getString(R.string.no_task_selected), Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    /**
     * Realiza una llamada telefónica al número asociado a la tarea seleccionada.
     * Verifica los permisos necesarios antes de realizar la llamada.
     */
    private void makePhoneCall() {
        if (selectedTask == null || selectedTask.getPhone().isEmpty()) {
            Toast.makeText(this, getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneNumber = selectedTask.getPhone().trim();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Número de teléfono inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PERMISSION_CODE);
        } else {
            startCall(phoneNumber);
        }
    }

    /**
     * Inicia la llamada telefónica usando el intent de llamada.
     */
    private void startCall(String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
            Log.i(TAG, "Iniciando llamada a: " + phoneNumber);
        } catch (SecurityException e) {
            Toast.makeText(this, getString(R.string.error_call_permission), 
                Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al realizar la llamada: " + e.getMessage());
        } catch (Exception e) {
            Toast.makeText(this, "Error al realizar la llamada: " + e.getMessage(), 
                Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al realizar la llamada: " + e.getMessage());
        }
    }

    /**
     * Se llama cuando el usuario responde a la solicitud de permisos.
     * @param requestCode Código de la solicitud de permisos
     * @param permissions Permisos solicitados
     * @param grantResults Resultados de la concesión de permisos
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (selectedTask != null) {
                    startCall(selectedTask.getPhone().trim());
                }
            } else {
                Toast.makeText(this, "Se requiere permiso para realizar llamadas", 
                    Toast.LENGTH_SHORT).show();
            }
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