package models;

import java.util.ArrayList;
import java.util.Date;


//para modificar la actividad se toma el orden de todos los atributos
//1 para nombre, 2 para descripción, etc

//revisar qué atributos si se deben poder modificar

public class Actividad {
	private String nombre; //1. SE PUEDE CAMBIAR? diria que no
    private String descripcion; //2
    private String objetivo; //3
    private String nivelDificultad; //4
    private int duracionEsperada; //5
    private ArrayList<String> prerequisitos; //6. recordar separar por comas las actividades
    private Date fechaLimiteBasadaEnActividadAnterior; //7
    private boolean obligatorioOpcional; //8
    private int resultado; //9
    private float rating; //10. Da rating mas no modifica
    private float sumaRating; //11. USUARIO NO MODIFICA.
    private String creador; //12. USUARIO NO MODIFICA

    // Constructor
    public Actividad(Date fecha, Boolean obligatorio, String nombre, String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador) {
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
        this.obligatorioOpcional = obligatorio;
        this.fechaLimiteBasadaEnActividadAnterior = fecha;
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
    public void agregarPrerequisito(String nombre) {
        prerequisitos.add(nombre);
    }

    
    REVISAR METODO PORQUE SE CAMBIO A ARRAY DE STRING Y NO DE ACTIVIDADES
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
    

    public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public void setDuracionEsperada(int duracionEsperada) {
		this.duracionEsperada = duracionEsperada;
	}

	public void setPrerequisitos(ArrayList<String> prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

	public void setFechaLimiteBasadaEnActividadAnterior(Date fechaLimiteBasadaEnActividadAnterior) {
		this.fechaLimiteBasadaEnActividadAnterior = fechaLimiteBasadaEnActividadAnterior;
	}

	public void setObligatorioOpcional(boolean obligatorioOpcional) {
		this.obligatorioOpcional = obligatorioOpcional;
	}

	public void setSumaRating(float sumaRating) {
		this.sumaRating = sumaRating;
	}

	// Getters y Setters
    public int getDuracionEsperada() {
        return duracionEsperada;
    }
    
    public String getNivel() {
        return nivelDificultad;
    }
    
	public ArrayList<String> getPrerequisitos() {
		return prerequisitos;
	}


	public boolean isObligatorioOpcional() {
		return obligatorioOpcional;
	}

	public int getResultado() {
		return resultado;
	}

	public String getCreador() {
		return creador;
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
