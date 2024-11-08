package models;

import java.util.ArrayList;
import java.util.Date;


public abstract class Actividad {
    protected String descripcion;
    protected String objetivo;
    protected String nivelDificultad;
    protected int duracionEsperada;
    protected ArrayList<Actividad> prerequisitos;
    protected Date fechaLimiteBasadaEnActividadAnterior;
    protected boolean obligatorioOpcional;
    protected int resultado;
    protected float rating;
    protected String titulo;
    private float sumaRating;//PARA QUE NATA HAGA LO DEL PROMEDIO DE LAS RESEÑAS
    protected String creador;

    // Constructor
    public Actividad(String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador, String titulo) {
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.nivelDificultad = nivelDificultad;
        this.duracionEsperada = duracionEsperada;
        this.prerequisitos = new ArrayList<>();
        this.rating = 0;
        this.resultado = 0;
        this.sumaRating = 0;
        this.creador = creador;
        this.titulo= titulo;
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

    //implementado por las subclases para obtener los detalles de la actividad
    public abstract String obtenerDetalles();

	public String getTitulo() {
		return titulo;
	}
}
