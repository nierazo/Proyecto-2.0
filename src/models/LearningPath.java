package models;

import java.util.ArrayList;
import feedback.Feedback;


//revisar que parametros si se pueden modificar
public class LearningPath {
    private String creador;  //. USUARIO NO MODIFICA
    private String titulo;//1. 
    private String descripcion; //2.
    private String objetivo; //3
    private String contenido; //4
	private String nivelDificultad; //5
    private int duracion;  //6 Duración total en minutos
    private ArrayList<Actividad> actividades;  //7 Estructura de actividades (la lista de actividades)
    private ArrayList<Feedback> feedback;  //USUARIO NO MODIFICA Lista de feedbacks asociados
    private String fechaCreacion; //USUARIO NO MODIFICA
    private String fechaModificacion; //USUARIO NO MODIFICA
    private float rating; //USUARIO NO MODIFICA
    private float sumaRating; //USUARIO NO MODIFICA

    // Constructor
    public LearningPath(int duracion, String creador, String titulo, String descripcion, String objetivo, String contenido, int nivelDificultad, String fechaCreacion) {
        this.creador = creador;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.contenido = contenido;
        this.nivelDificultad = nivelDificultad;
        this.duracion = duracion;
        this.actividades = new ArrayList<>();
        this.feedback = new ArrayList<>();
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaCreacion;
        this.rating = 0;
        this.sumaRating = 0;
    }

    // Métodos para gestionar feedback, actividades y la información del Learning Path

    // Modificar el Learning Path
    public void modificarLearningPath(String titulo, String descripcion, String nivelDificultad) {
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
    
    public float getSumaRating() {
		return sumaRating;
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

    public String getNivelDificultad() {
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

    public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public void setFeedback(ArrayList<Feedback> feedback) {
		this.feedback = feedback;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public void setSumaRating(float sumaRating) {
		this.sumaRating = sumaRating;
	}
}
