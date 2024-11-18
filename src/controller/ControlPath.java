package controller;

import models.LearningPath;
import models.Actividad;
import usuarios.Estudiante;

import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

public class ControlPath {

    private String nombrePath;
    private boolean enCurso;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private int totalActividades; // Cuántas actividades hay en el learning path
    private int actividadesCompletadas; // Cantidad de actividades completadas
    private float progreso; // Progreso en porcentaje
    private ArrayList<ControlActividad> actividades; // Lista de actividades dentro del learning path

    // Constructor
    public ControlPath(String nombrePath, Date fechaInicio, Date fechaFinalizacion,
                       int totalActividades, float progreso) {
        super();
        this.nombrePath = nombrePath;
        this.enCurso = false;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.totalActividades = totalActividades;
        this.actividadesCompletadas = 0;
        this.progreso = progreso;
        this.actividades = new ArrayList<>(); // Se inicializa la lista de actividades que tiene el learning path
    }

    // Método para iniciar el learning path
    public void iniciarLearningPath() {
        this.fechaInicio = new Date(); // Fecha actual
        this.enCurso = true;
    }

    // Método para finalizar el learning path
    public void finalizarLearningPath() {
        this.fechaFinalizacion = new Date(); // Fecha actual
        this.progreso = 100; // Completado
        this.enCurso = false;
    }

    // Método para actualizar el progreso
    public void actualizarProgreso(float nuevoProgreso) {
        if (nuevoProgreso >= 0 && nuevoProgreso <= 100) {
            this.progreso = nuevoProgreso;
        } else {
            System.out.println("Progreso inválido. Debe estar entre 0 y 100.");
        }
    }

    // Método para agregar una actividad completada y actualizar el progreso automáticamente
    public void agregarActividadCompletada() {
        if (actividadesCompletadas < totalActividades) {
            actividadesCompletadas++;
            this.progreso = ((float) actividadesCompletadas / totalActividades) * 100;
        }
    }

    // Método para verificar si el learning path ya se completó
    public boolean esLearningPathCompletado() {
        return this.progreso == 100;
    }

    // Método para agregar una nueva actividad a la lista de control actividades
    public void agregarControlActividad(ControlActividad controlActividad) {
        actividades.add(controlActividad);
    }

    // Método para obtener la lista de actividades
    public ArrayList<ControlActividad> getActividades() {
        return actividades;
    }

    //verifica si una ctividad esta completada
    public boolean isActividadCompletada(Actividad actividad) {
        for (ControlActividad controlActividad : actividades) {
            if (controlActividad.getActividad().equals(actividad)) {
                return controlActividad.getEstado().equals("completada");
            }
        }
        return false;
    }

	

    // Getters para acceder a los datos del learning path (se usa en controlador)
    public String getNombrePath() {
		return nombrePath;
	}

	public void setNombrePath(String nombrePath) {
		this.nombrePath = nombrePath;
	}

	public boolean isEnCurso() {
		return enCurso;
	}

	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public int getTotalActividades() {
		return totalActividades;
	}

	public void setTotalActividades(int totalActividades) {
		this.totalActividades = totalActividades;
	}

	public int getActividadesCompletadas() {
		return actividadesCompletadas;
	}

	public void setActividadesCompletadas(int actividadesCompletadas) {
		this.actividadesCompletadas = actividadesCompletadas;
	}

	public float getProgreso() {
		return progreso;
	}

	public void setProgreso(float progreso) {
		this.progreso = progreso;
	}

	public void setActividades(ArrayList<ControlActividad> actividades) {
		this.actividades = actividades;
	}
	
	
}
