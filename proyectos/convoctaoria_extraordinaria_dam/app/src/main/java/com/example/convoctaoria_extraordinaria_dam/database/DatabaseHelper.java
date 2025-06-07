package com.example.convoctaoria_extraordinaria_dam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.convoctaoria_extraordinaria_dam.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase auxiliar para la gestión de la base de datos SQLite.
 * Esta clase maneja todas las operaciones de base de datos para la aplicación,
 * incluyendo la creación, actualización y consulta de tareas.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    /** Versión de la base de datos */
    private static final int DATABASE_VERSION = 2;
    
    /** Nombre de la base de datos */
    private static final String DATABASE_NAME = "TaskManager.db";

    /** Nombre de la tabla de tareas */
    public static final String TABLE_TASKS = "tasks";

    /** Nombres de las columnas */
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ASSIGNED_PERSON = "assigned_person";
    public static final String COLUMN_PHONE = "phone";

    /** SQL para crear la tabla de tareas */
    private static final String SQL_CREATE_TASKS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT NOT NULL," +
            COLUMN_DESCRIPTION + " TEXT," +
            COLUMN_ASSIGNED_PERSON + " TEXT," +
            COLUMN_PHONE + " TEXT" +
            ")";

    /** SQL para eliminar la tabla de tareas */
    private static final String SQL_DELETE_TASKS =
            "DROP TABLE IF EXISTS " + TABLE_TASKS;

    /**
     * Constructor de la clase.
     * @param context Contexto de la aplicación
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            // Forzar la creación de la base de datos
            getWritableDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "Error al crear la base de datos: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Se llama cuando la base de datos se crea por primera vez.
     * Crea la tabla de tareas con todas sus columnas.
     * @param db Instancia de la base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(TAG, "Creando tabla de tareas...");
            db.execSQL(SQL_CREATE_TASKS);
            Log.d(TAG, "Tabla de tareas creada exitosamente");
        } catch (SQLException e) {
            Log.e(TAG, "Error al crear la tabla de tareas: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Se llama cuando la base de datos necesita ser actualizada.
     * En este caso, elimina la tabla existente y la vuelve a crear.
     * @param db Instancia de la base de datos
     * @param oldVersion Versión anterior de la base de datos
     * @param newVersion Nueva versión de la base de datos
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Log.d(TAG, "Actualizando base de datos de versión " + oldVersion + " a " + newVersion);
            db.execSQL(SQL_DELETE_TASKS);
            onCreate(db);
            Log.d(TAG, "Base de datos actualizada exitosamente");
        } catch (SQLException e) {
            Log.e(TAG, "Error al actualizar la base de datos: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Inserta una nueva tarea en la base de datos.
     * @param task La tarea a insertar
     * @return El ID de la tarea insertada, o -1 si hubo un error
     */
    public long insertTask(Task task) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            
            values.put(COLUMN_NAME, task.getName());
            values.put(COLUMN_DESCRIPTION, task.getDescription());
            values.put(COLUMN_ASSIGNED_PERSON, task.getAssignedPerson());
            values.put(COLUMN_PHONE, task.getPhone());

            long id = db.insert(TABLE_TASKS, null, values);
            if (id == -1) {
                Log.e(TAG, "Error al insertar tarea: " + task.getName());
            } else {
                Log.d(TAG, "Tarea insertada exitosamente con ID: " + id);
            }
            return id;
        } catch (SQLException e) {
            Log.e(TAG, "Error al insertar tarea: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Actualiza una tarea existente en la base de datos.
     * @param task La tarea con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean updateTask(Task task) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            
            values.put(COLUMN_NAME, task.getName());
            values.put(COLUMN_DESCRIPTION, task.getDescription());
            values.put(COLUMN_ASSIGNED_PERSON, task.getAssignedPerson());
            values.put(COLUMN_PHONE, task.getPhone());

            int rowsAffected = db.update(
                TABLE_TASKS,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())}
            );

            boolean success = rowsAffected > 0;
            if (success) {
                Log.d(TAG, "Tarea actualizada exitosamente: " + task.getName());
            } else {
                Log.e(TAG, "No se pudo actualizar la tarea: " + task.getName());
            }
            return success;
        } catch (SQLException e) {
            Log.e(TAG, "Error al actualizar tarea: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Elimina una tarea de la base de datos.
     * @param taskId ID de la tarea a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean deleteTask(long taskId) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            int rowsAffected = db.delete(
                TABLE_TASKS,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(taskId)}
            );
            boolean success = rowsAffected > 0;
            if (success) {
                Log.d(TAG, "Tarea eliminada exitosamente con ID: " + taskId);
            } else {
                Log.e(TAG, "No se pudo eliminar la tarea con ID: " + taskId);
            }
            return success;
        } catch (SQLException e) {
            Log.e(TAG, "Error al eliminar tarea: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Obtiene todas las tareas de la base de datos.
     * @return Lista de todas las tareas ordenadas por nombre
     */
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        
        try {
            db = this.getReadableDatabase();
            Log.d(TAG, "Obteniendo todas las tareas...");
            
            cursor = db.query(
                TABLE_TASKS,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_ASSIGNED_PERSON, COLUMN_PHONE},
                null,
                null,
                null,
                null,
                COLUMN_NAME + " ASC"
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Task task = new Task();
                    task.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    task.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                    task.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                    task.setAssignedPerson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSIGNED_PERSON)));
                    task.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
                    taskList.add(task);
                } while (cursor.moveToNext());
                Log.d(TAG, "Se encontraron " + taskList.size() + " tareas");
            } else {
                Log.d(TAG, "No se encontraron tareas en la base de datos");
            }
            
            return taskList;
        } catch (SQLException e) {
            Log.e(TAG, "Error al obtener tareas: " + e.getMessage(), e);
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }
} 