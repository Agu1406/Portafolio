package com.example.convoctaoria_extraordinaria_dam.models;

/**
 * Clase que representa una tarea en el sistema.
 * Esta clase modela los datos de una tarea, incluyendo su identificador,
 * nombre, descripción, persona asignada y número de teléfono.
 */
public class Task {
    /** Identificador único de la tarea en la base de datos */
    private long id;
    
    /** Nombre o título de la tarea */
    private String name;
    
    /** Descripción detallada de la tarea */
    private String description;
    
    /** Nombre de la persona asignada a la tarea */
    private String assignedPerson;
    
    /** Número de teléfono de contacto para la tarea */
    private String phone;

    /**
     * Constructor por defecto.
     * Crea una nueva instancia de Task sin inicializar sus campos.
     */
    public Task() {
    }

    /**
     * Constructor completo que inicializa todos los campos de la tarea.
     * @param id Identificador único de la tarea
     * @param name Nombre de la tarea
     * @param description Descripción de la tarea
     * @param assignedPerson Persona asignada
     * @param phone Número de teléfono
     */
    public Task(long id, String name, String description, String assignedPerson, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.phone = phone;
    }

    /**
     * Constructor para nuevas tareas sin ID.
     * Este constructor se usa cuando se crea una nueva tarea que aún no tiene ID en la base de datos.
     * @param name Nombre de la tarea
     * @param description Descripción de la tarea
     * @param assignedPerson Persona asignada
     * @param phone Número de teléfono
     */
    public Task(String name, String description, String assignedPerson, String phone) {
        this.name = name;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.phone = phone;
    }

    /**
     * Obtiene el ID de la tarea.
     * @return El identificador único de la tarea
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el ID de la tarea.
     * @param id El nuevo identificador de la tarea
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la tarea.
     * @return El nombre o título de la tarea
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la tarea.
     * @param name El nuevo nombre de la tarea
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción de la tarea.
     * @return La descripción detallada de la tarea
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la tarea.
     * @param description La nueva descripción de la tarea
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la persona asignada a la tarea.
     * @return El nombre de la persona asignada
     */
    public String getAssignedPerson() {
        return assignedPerson;
    }

    /**
     * Establece la persona asignada a la tarea.
     * @param assignedPerson El nombre de la nueva persona asignada
     */
    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    /**
     * Obtiene el número de teléfono asociado a la tarea.
     * @return El número de teléfono de contacto
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono asociado a la tarea.
     * @param phone El nuevo número de teléfono
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Devuelve una representación en texto de la tarea.
     * @return Una cadena con el nombre de la tarea y la persona asignada
     */
    @Override
    public String toString() {
        return name + " - " + assignedPerson;
    }
} 