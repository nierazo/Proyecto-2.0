package controller;

import models.Actividad;  // Relación con Actividad
import java.util.Date;

public class ControlActividad {
    private Actividad actividad;  // Relación con la clase Actividad
    private String estado;  // Estado de la actividad (en progreso, completada, etc.)
    private Date fechaCompletar;  // Fecha de finalización
    private float tiempoDedicado;  // Tiempo dedicado en horas
    private float tasaExitoFracaso;  // Porcentaje de éxito o fracaso en la actividad
    private float calificacion;  // Calificación obtenida en la actividad
    private String medioEntrega;  // Medio de entrega de la actividad (online, físico, etc.)

    // Constructor que toma una Actividad
    public ControlActividad(Actividad actividad, String medioEntrega) {
        this.actividad = actividad;
        this.estado = "no iniciada";  // Por defecto, la actividad no ha sido iniciada
        this.tiempoDedicado = 0;
        this.tasaExitoFracaso = 0;
        this.calificacion = 0;
        this.medioEntrega = medioEntrega;
    }

    // Método para completar la actividad
    public void completarActividad() {
        this.estado = "completada";
        this.fechaCompletar = new Date();  // Fecha actual de finalización
        System.out.println("La actividad " + actividad.getDescripcion() + " ha sido completada.");
    }

    // Método para obtener el resultado de la actividad, mostrando la calificación
    public String obtenerResultado() {
        if (this.estado.equals("completada")) {
            return "Actividad completada con calificación: " + this.getCalificacion();
        } else {
            return "Actividad no completada";
        }
    }

    // Método para verificar la actividad de seguimiento
    public String verificarSeguimiento() {
        if (this.estado.equals("completada") && this.tasaExitoFracaso >= 50) {
            return "Siguiente actividad desbloqueada";
        } else {
            return "Repita la actividad antes de continuar";
        }
    }

    // Método para actualizar el estado de la actividad
    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("El estado de la actividad " + actividad.getDescripcion() + " ha sido actualizado a: " + nuevoEstado);
    }

    // Método para establecer la fecha límite de la actividad
    public void establecerFechaLimite(Date fechaLimite) {
        System.out.println("La fecha límite para la actividad " + actividad.getDescripcion() + " es: " + fechaLimite);
    }

    // Método para agregar tiempo dedicado a la actividad
    public void agregarTiempoDedicado(float horas) {
        this.tiempoDedicado += horas;
        System.out.println("Tiempo dedicado a la actividad " + actividad.getDescripcion() + " actualizado: " + this.tiempoDedicado + " horas.");
    }

    // Getters y Setters

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCompletar() {
        return fechaCompletar;
    }

    public float getTiempoDedicado() {
        return tiempoDedicado;
    }

    public void setTiempoDedicado(float tiempoDedicado) {
        this.tiempoDedicado = tiempoDedicado;
    }

    public float getTasaExitoFracaso() {
        return tasaExitoFracaso;
    }

    public void setTasaExitoFracaso(float tasaExitoFracaso) {
        this.tasaExitoFracaso = tasaExitoFracaso;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getMedioEntrega() {
        return medioEntrega;
    }

    public void setMedioEntrega(String medioEntrega) {
        this.medioEntrega = medioEntrega;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}
