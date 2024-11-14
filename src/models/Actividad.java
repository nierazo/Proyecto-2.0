package models;

import java.util.ArrayList;
import java.util.Date;

public class Actividad {
	private String nombre;
    private String descripcion;
    private String objetivo;
    private String nivelDificultad;
    private int duracionEsperada;
    private ArrayList<Actividad> prerequisitos;
    private Date fechaLimiteBasadaEnActividadAnterior;
    private boolean obligatorioOpcional;
    private int resultado;
    private float rating;
    private float sumaRating;
    private String creador;

    // Constructor
    public Actividad(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador) {
        this.nombre = nombre;
    	this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.nivelDificultad = nivelDificultad;
        this.duracionEsperada = duracionEsperada;
        this.prerequisitos = new ArrayList<>();
        this.rating = 0;
        this.resultado = 0;
        this.sumaRating = 0;
        this.creador = creador;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Método para obtener el resultado de la actividad
    public String obtenerResultado() {
        return "Resultado de la actividad: " + resultado;
    }

    // Método para establecer el resultado de la actividad
    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    // Método para verificar si la actividad está completada
    public boolean isCompletada() {
        return resultado > 0;  // Se asume que resultado > 0 indica que la actividad está completada
    }

    // Método para agregar un prerrequisito
    public void agregarPrerequisito(Actividad actividad) {
        prerequisitos.add(actividad);
    }

    // Método para mostrar advertencia sobre prerrequisitos
    public boolean advertenciaPrerequisitos() {
        for (Actividad prerequisito : prerequisitos) {
            if (!prerequisito.isCompletada()) {
                System.out.println("Advertencia: El prerrequisito " + prerequisito.getDescripcion() + " no está completado.");
                return false;
            }
        }
        return true;
        
    }
    

    // Getters y Setters
    public int getDuracionEsperada() {
        return duracionEsperada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaLimite() {
        return fechaLimiteBasadaEnActividadAnterior;
    }

    public float getRating() {
        return rating;
    }
    
    public String getObjetivo() {
        return objetivo;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public float getSumaRating() {
        return sumaRating;
    }
    //implementado por las subclases para obtener los detalles de la actividad
    //public abstract String obtenerDetalles();
}
