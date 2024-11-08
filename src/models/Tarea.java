package models;

import java.util.Date;

public class Tarea extends Actividad {
    private String estado;  // Estado de la tarea (por ejemplo, "enviada", "completada")
    private Date fechaEntrega;
    private String medioEntrega;  // Medio de entrega (por ejemplo, "LMS", "correo")

    // Constructor
    public Tarea(String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador, Date fechaEntrega, String medioEntrega) {
        super(descripcion, objetivo, nivelDificultad, duracionEsperada, creador);
        this.estado = "pendiente";
        this.fechaEntrega = fechaEntrega;
        this.medioEntrega = medioEntrega;
    }

    // Método para obtener el estado de la tarea
    public String getEstado() {
        return this.estado;
    }

    // Método para establecer el estado de la tarea
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Implementación del método obtenerDetalles()
    @Override
    public String obtenerDetalles() {
        return "Tarea: " + getDescripcion() +
               "\nObjetivo: " + getObjetivo() +
               "\nDuración esperada: " + getDuracionEsperada() + " minutos" +
               "\nFecha de entrega: " + fechaEntrega.toString() +
               "\nMedio de entrega: " + medioEntrega;
    }

    // Obtener resultado de la tarea
    @Override
    public String obtenerResultado() {
        return isCompletada() ? "Tarea completada" : "Tarea no completada";
    }
}
