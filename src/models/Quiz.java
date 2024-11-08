package models;

public class Quiz extends Actividad {
    private String[] preguntas;
    private String[][] opciones;  // Opciones de cada pregunta

    // Constructor
    public Quiz(String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador, String[] preguntas, String[][] opciones) {
        super(descripcion, objetivo, nivelDificultad, duracionEsperada, creador);
        this.preguntas = preguntas;
        this.opciones = opciones;
    }

    // Método para obtener las preguntas
    public String[] getPreguntas() {
        return this.preguntas;
    }

    // Método para obtener las opciones de una pregunta
    public String[][] getOpciones() {
        return this.opciones;
    }

    // Implementación del método obtenerDetalles()
    @Override
    public String obtenerDetalles() {
        StringBuilder detalles = new StringBuilder("Quiz: " + getDescripcion() +
                "\nObjetivo: " + getObjetivo() +
                "\nDuración esperada: " + getDuracionEsperada() + " minutos" +
                "\nPreguntas:");

        for (int i = 0; i < preguntas.length; i++) {
            detalles.append("\n").append(preguntas[i]).append(" Opciones: ");
            for (String opcion : opciones[i]) {
                detalles.append(opcion).append(" ");
            }
        }
        return detalles.toString();
    }

    // Obtener resultado del quiz
    @Override
    public String obtenerResultado() {
        return isCompletada() ? "Quiz completado" : "Quiz no completado";
    }
}
