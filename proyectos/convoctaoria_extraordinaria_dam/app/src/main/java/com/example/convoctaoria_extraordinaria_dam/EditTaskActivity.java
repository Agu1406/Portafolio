package com.example.convoctaoria_extraordinaria_dam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import com.example.convoctaoria_extraordinaria_dam.database.DatabaseHelper;
import com.example.convoctaoria_extraordinaria_dam.models.Task;
import com.example.convoctaoria_extraordinaria_dam.adapters.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad para modificar tareas existentes.
 * Esta actividad permite al usuario seleccionar una tarea de una lista
 * y modificar sus datos, incluyendo nombre, descripción, persona asignada y teléfono.
 */
public class EditTaskActivity extends AppCompatActivity {
    private static final String TAG = "EditTaskActivity";
    /** Lista de tareas disponibles para modificar */
    private List<Task> taskList;
    
    /** Componentes de la interfaz de usuario */
    private ListView listViewTasks;
    private TextInputEditText editTextName;
    private TextInputEditText editTextDescription;
    private TextInputEditText editTextAssignedPerson;
    private TextInputEditText editTextPhone;
    private Button btnSave;
    private Button btnCancel;
    
    /** Tarea seleccionada actualmente */
    private Task selectedTask;
    
    /** Helper para la gestión de la base de datos */
    private DatabaseHelper dbHelper;
    
    /** Adaptador para la lista de tareas */
    private TaskAdapter adapter;

    /**
     * Se llama cuando la actividad se crea por primera vez.
     * Inicializa la interfaz de usuario y configura los listeners de eventos.
     * @param savedInstanceState Estado guardado de la actividad, si existe
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Inicializar vistas
        initializeViews();
        
        // Inicializar base de datos
        dbHelper = new DatabaseHelper(this);
        
        // Cargar tareas
        loadTasks();
        
        // Configurar listeners
        setupListeners();
    }

    /**
     * Inicializa las vistas de la interfaz de usuario.
     */
    private void initializeViews() {
        listViewTasks = findViewById(R.id.listViewTasksToEdit);
        editTextName = findViewById(R.id.etTaskName);
        editTextDescription = findViewById(R.id.etTaskDescription);
        editTextAssignedPerson = findViewById(R.id.etAssignedPerson);
        editTextPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSaveChanges);
        btnCancel = findViewById(R.id.btnCancel);
        
        // Deshabilitar campos inicialmente
        setFieldsEnabled(false);
    }

    /**
     * Carga las tareas desde la base de datos.
     */
    private void loadTasks() {
        taskList = dbHelper.getAllTasks();
        List<String> taskNames = new ArrayList<>();
        for (Task task : taskList) {
            taskNames.add(task.getName());
        }
        
        adapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(adapter);
    }

    /**
     * Configura los listeners de eventos para los botones.
     */
    private void setupListeners() {
        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            selectedTask = taskList.get(position);
            populateFields(selectedTask);
            setFieldsEnabled(true);
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTask != null) {
                    showConfirmationDialog();
                } else {
                    Toast.makeText(EditTaskActivity.this, 
                        "Por favor, seleccione una tarea", 
                        Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Rellena los campos del formulario con los datos de la tarea seleccionada.
     * @param task Tarea cuyos datos se mostrarán en el formulario
     */
    private void populateFields(Task task) {
        editTextName.setText(task.getName());
        editTextDescription.setText(task.getDescription());
        editTextAssignedPerson.setText(task.getAssignedPerson());
        editTextPhone.setText(task.getPhone());
    }

    private void setFieldsEnabled(boolean enabled) {
        editTextName.setEnabled(enabled);
        editTextDescription.setEnabled(enabled);
        editTextAssignedPerson.setEnabled(enabled);
        editTextPhone.setEnabled(enabled);
        btnSave.setEnabled(enabled);
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Confirmar modificación")
            .setMessage("¿Está seguro de que desea modificar esta tarea?")
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    updateTask();
                }
            })
            .setNegativeButton("No", null)
            .show();
    }

    /**
     * Actualiza la tarea seleccionada con los nuevos datos del formulario.
     */
    private void updateTask() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String assignedPerson = editTextAssignedPerson.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || assignedPerson.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", 
                Toast.LENGTH_SHORT).show();
            return;
        }

        selectedTask.setName(name);
        selectedTask.setDescription(description);
        selectedTask.setAssignedPerson(assignedPerson);
        selectedTask.setPhone(phone);

        boolean success = dbHelper.updateTask(selectedTask);
        
        if (success) {
            String message = "Tarea modificada correctamente";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Tarea modificada: " + selectedTask.getName());
            
            // Actualizar la lista
            loadTasks();
            // Limpiar selección
            listViewTasks.clearChoices();
            adapter.notifyDataSetChanged();
            // Limpiar campos
            clearFields();
            setFieldsEnabled(false);
            selectedTask = null;
        } else {
            Toast.makeText(this, "Error al modificar la tarea", 
                Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al modificar la tarea: " + selectedTask.getName());
        }
    }

    private void clearFields() {
        editTextName.setText("");
        editTextDescription.setText("");
        editTextAssignedPerson.setText("");
        editTextPhone.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
} 