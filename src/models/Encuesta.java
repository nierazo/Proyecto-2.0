package models;

import java.util.ArrayList;

public class Encuesta extends Actividad {
    private ArrayList<String> preguntas;

    // Constructor que acepta los parámetros necesarios
    public Encuesta(String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador, String[] preguntasEncuesta) {
        super(descripcion, objetivo, nivelDificultad, duracionEsperada, creador);
        this.preguntas = new ArrayList<>();
        // Agregar las preguntas a la lista
        for (String pregunta : preguntasEncuesta) {
            this.preguntas.add(pregunta);
        }
    }

    // Método para agregar una pregunta a la encuesta
    public void agregarPregunta(String pregunta) {
        this.preguntas.add(pregunta);
    }

    // Método para obtener la lista de preguntas de la encuesta
    public ArrayList<String> getPreguntas() {
        return this.preguntas;
    }

    // Obtener resultado de la encuesta
    @Override
    public String obtenerResultado() {
        return isCompletada() ? "Encuesta completada" : "Encuesta no completada";
    }

    // Implementación del método obtenerDetalles
    @Override
    public String obtenerDetalles() {
        StringBuilder detalles = new StringBuilder("Encuesta: " + getDescripcion() +
                "\nObjetivo: " + getObjetivo() +
                "\nDuración esperada: " + getDuracionEsperada() + " minutos" +
                "\nPreguntas:");
        
        for (String pregunta : preguntas) {
            detalles.append("\n").append(pregunta);
        }

        return detalles.toString();
    }
}

