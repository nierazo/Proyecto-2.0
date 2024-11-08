package models;

import java.util.ArrayList;
import feedback.Feedback;

public class LearningPath {
    private String creador;  // Se refiere al profesor que crea el Learning Path
    private String titulo;
    private String descripcion;
    private String objetivo;
    private String contenido;
    private int nivelDificultad;
    private int duracion;  // Duración total en minutos
    private ArrayList<Actividad> actividades;  // Estructura de actividades (la lista de actividades)
    private ArrayList<Feedback> feedback;  // Lista de feedbacks asociados
    private String fechaCreacion;
    private String fechaModificacion;
    private float rating;
    private float sumaRating;

    // Constructor
    public LearningPath(String creador, String titulo, String descripcion, String objetivo, String contenido, int nivelDificultad, String fechaCreacion) {
        this.creador = creador;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.contenido = contenido;
        this.nivelDificultad = nivelDificultad;
        this.duracion = 0;
        this.actividades = new ArrayList<>();
        this.feedback = new ArrayList<>();
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaCreacion;
        this.rating = 0;
        this.sumaRating = 0;
    }

    // Métodos para gestionar feedback, actividades y la información del Learning Path

    // Modificar el Learning Path
    public void modificarLearningPath(String titulo, String descripcion, int nivelDificultad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nivelDificultad = nivelDificultad;
        this.fechaModificacion = new java.util.Date().toString();  // Actualiza la fecha de modificación
        System.out.println("El Learning Path ha sido modificado.");
    }

    // Agregar una actividad al Learning Path
    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
        actualizarDuracion();
        System.out.println("Actividad '" + actividad.getDescripcion() + "' añadida al Learning Path.");
    }

    // Eliminar una actividad del Learning Path
    public void eliminarActividad(Actividad actividad) {
        if (actividades.contains(actividad)) {
            this.actividades.remove(actividad);
            actualizarDuracion();
            System.out.println("Actividad '" + actividad.getDescripcion() + "' eliminada del Learning Path.");
        } else {
            System.out.println("Error: La actividad no está en este Learning Path.");
        }
    }

    // Actualizar la duración total del Learning Path
    public void actualizarDuracion() {
        int totalDuracion = 0;
        for (Actividad actividad : actividades) {
            totalDuracion += actividad.getDuracionEsperada();
        }
        this.duracion = totalDuracion;
    }

    // Calcular el rating del Learning Path basado en el feedback recibido
    public void calcularRating() {
        if (!feedback.isEmpty()) {
            float total = 0;
            for (Feedback fb : feedback) {
                total += fb.getRating();
            }
            this.sumaRating = total;
            this.rating = sumaRating / feedback.size();
        }
    }

    // Agregar feedback al Learning Path
    public void agregarFeedback(Feedback fb) {
        this.feedback.add(fb);
        calcularRating();
        System.out.println("Feedback añadido con éxito.");
    }

    // Getters

    public String getCreador() {
        return creador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getContenido() {
        return contenido;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public int getDuracionTotal() {
        return duracion;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public ArrayList<Feedback> getFeedback() {
        return feedback;
    }

    public float getRating() {
        return rating;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
