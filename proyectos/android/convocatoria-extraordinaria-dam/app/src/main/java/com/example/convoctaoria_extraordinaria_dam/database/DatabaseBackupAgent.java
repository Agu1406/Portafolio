package com.example.convoctaoria_extraordinaria_dam.database;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;

public class DatabaseBackupAgent extends BackupAgentHelper {
    private static final String DB_NAME = "tasks.db";
    private static final String PREFS_NAME = "com.example.convoctaoria_extraordinaria_dam_preferences";

    @Override
    public void onCreate() {
        // Backup de la base de datos
        FileBackupHelper dbHelper = new FileBackupHelper(this, DB_NAME);
        addHelper("database", dbHelper);

        // Backup de las preferencias
        SharedPreferencesBackupHelper prefsHelper = new SharedPreferencesBackupHelper(this, PREFS_NAME);
        addHelper("preferences", prefsHelper);
    }
} 