package com.example.convoctaoria_extraordinaria_dam;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convoctaoria_extraordinaria_dam.database.DatabaseHelper;
import com.example.convoctaoria_extraordinaria_dam.models.Task;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Actividad para añadir nuevas tareas al sistema.
 * Esta actividad proporciona un formulario para ingresar los datos de una nueva tarea
 * y la guarda en la base de datos.
 */
public class AddTaskActivity extends AppCompatActivity {
    private static final String TAG = "AddTaskActivity";
    
    /** Campos de entrada para los datos de la tarea */
    private TextInputEditText etTaskName, etTaskDescription, etAssignedPerson, etPhone;
    
    /** Botones de la interfaz */
    private Button btnSaveTask, btnCancel;
    
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
        setContentView(R.layout.activity_add_task);

        // Inicializar la base de datos
        dbHelper = new DatabaseHelper(this);

        // Inicializar vistas
        initializeViews();
        
        // Configurar listeners
        setupListeners();
    }

    /**
     * Inicializa las vistas de la interfaz de usuario.
     */
    private void initializeViews() {
        etTaskName = findViewById(R.id.etTaskName);
        etTaskDescription = findViewById(R.id.etTaskDescription);
        etAssignedPerson = findViewById(R.id.etAssignedPerson);
        etPhone = findViewById(R.id.etPhone);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);
    }

    /**
     * Configura los listeners de eventos para los botones.
     */
    private void setupListeners() {
        btnSaveTask.setOnClickListener(v -> saveTask());
        btnCancel.setOnClickListener(v -> finish());
    }

    /**
     * Guarda la nueva tarea en la base de datos.
     * Valida que todos los campos requeridos estén completos antes de guardar.
     */
    private void saveTask() {
        // Obtener los valores de los campos
        String name = etTaskName.getText().toString().trim();
        String description = etTaskDescription.getText().toString().trim();
        String assignedPerson = etAssignedPerson.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        // Validar campos obligatorios
        if (name.isEmpty() || assignedPerson.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar en la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_ASSIGNED_PERSON, assignedPerson);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);

        try {
            long newRowId = db.insert(DatabaseHelper.TABLE_TASKS, null, values);
            if (newRowId != -1) {
                // Mostrar mensaje de éxito
                Toast.makeText(this, "Tarea añadida correctamente", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Nueva tarea añadida: " + name);
                
                // Limpiar campos
                clearFields();
                
                // Volver a la actividad principal
                finish();
            } else {
                Toast.makeText(this, "Error al añadir la tarea", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error al insertar la tarea en la base de datos");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al añadir la tarea", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al insertar la tarea: " + e.getMessage());
        } finally {
            db.close();
        }
    }

    private void clearFields() {
        etTaskName.setText("");
        etTaskDescription.setText("");
        etAssignedPerson.setText("");
        etPhone.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
} 