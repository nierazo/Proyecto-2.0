package models;

import java.util.ArrayList;

public class Examen extends Actividad {
    private ArrayList<String> preguntas;  // Preguntas abiertas del examen

    // Constructor
    public Examen(String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador) {
        super(descripcion, objetivo, nivelDificultad, duracionEsperada, creador);
        this.preguntas = new ArrayList<>();  // Inicializa la lista de preguntas vacía
    }

    // Método para agregar una pregunta
    public void agregarPregunta(String pregunta) {
        this.preguntas.add(pregunta);
    }

    // Método para obtener las preguntas
    public ArrayList<String> getPreguntas() {
        return this.preguntas;
    }

    // Implementación del método obtenerDetalles()
    @Override
    public String obtenerDetalles() {
        return "Examen: " + getDescripcion() +
               "\nObjetivo: " + getObjetivo() +
               "\nDuración esperada: " + getDuracionEsperada() + " minutos" +
               "\nPreguntas: " + preguntas.toString();
    }

    // Obtener resultado del examen
    @Override
    public String obtenerResultado() {
        return isCompletada() ? "Examen completado" : "Examen no completado";
    }
}
