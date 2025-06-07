package com.example.convoctaoria_extraordinaria_dam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.convoctaoria_extraordinaria_dam.R;
import com.example.convoctaoria_extraordinaria_dam.models.Task;

import java.util.List;

/**
 * Adaptador personalizado para mostrar las tareas en un ListView.
 * Muestra el nombre de la tarea y la persona asignada.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    private final Context context;
    private final List<Task> tasks;

    /**
     * Constructor del adaptador.
     * @param context Contexto de la aplicación
     * @param tasks Lista de tareas a mostrar
     */
    public TaskAdapter(@NonNull Context context, @NonNull List<Task> tasks) {
        super(context, R.layout.item_task, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_task, parent, false);
        }

        Task task = tasks.get(position);
        TextView textViewName = view.findViewById(R.id.textViewTaskName);
        TextView textViewAssignedPerson = view.findViewById(R.id.textViewAssignedPerson);

        textViewName.setText(task.getName());
        textViewAssignedPerson.setText(task.getAssignedPerson());

        return view;
    }

    /**
     * Actualiza la lista de tareas y notifica al adaptador del cambio.
     * @param newTasks Nueva lista de tareas
     */
    public void updateTasks(List<Task> newTasks) {
        this.tasks.clear();
        this.tasks.addAll(newTasks);
        notifyDataSetChanged();
    }
} 