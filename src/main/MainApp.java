package main;

import models.LearningPath;
import models.RecursoEducativo;
import models.Quiz;
import models.Examen;
import models.Encuesta;
import usuarios.Profesor;
import usuarios.Estudiante;
import feedback.Feedback;
import controller.ControlPath;

import java.util.ArrayList;
import java.util.Date;

public class MainApp {
    public static void main(String[] args) {
        // Crear un profesor
        Profesor profesor1 = new Profesor("Carlos Pérez", "cperez@learning.com");

        // Crear un Learning Path
        LearningPath learningPath1 = profesor1.crearLearningPath(
                "Aprender Programación en Java", 
                "Curso para aprender Java desde cero", 
                "Introducción a la programación orientada a objetos", 
                "Contenido de programación en Java", 
                2  // Nivel de dificultad
        );

        // Crear una actividad (Recurso Educativo)
        RecursoEducativo actividad1 = new RecursoEducativo("Video Introducción a Java", 
                "Aprender los conceptos básicos de Java", 
                30, 
                "https://videojava.com");

        // Crear un Quiz (Actividad)
        String[] preguntasQuiz = {"¿Qué es una clase?", "¿Qué es un objeto?", "¿Cómo se define un método en Java?"};
        String[][] opcionesQuiz = {
                {"Definición de estructura", "Definición de comportamiento", "Entidad en POO", "Ninguna de las anteriores"},
                {"Instancia de una clase", "Declaración de una clase", "Método de una clase", "Ninguna de las anteriores"},
                {"public void", "static", "private", "abstract"}
        };
        Quiz actividad2 = new Quiz("Quiz sobre Java básico", 
                "Preguntas de opción múltiple sobre conceptos básicos de Java", 
                "Intermedio", 
                20, 
                "Carlos Pérez", 
                preguntasQuiz, 
                opcionesQuiz);

        // Crear un Examen (Actividad)
        Examen actividad3 = new Examen("Examen Final de Java", 
                "Evaluar los conocimientos adquiridos en el curso", 
                "Avanzado", 
                60, 
                "Carlos Pérez");
        actividad3.agregarPregunta("Describe la diferencia entre una clase y un objeto.");
        actividad3.agregarPregunta("Explica el concepto de herencia en Java.");

        // Crear una Encuesta (Actividad)
        String[] preguntasEncuesta = {"¿Qué te pareció el curso?", "¿Cómo mejorarías el contenido?"};
        Encuesta actividad4 = new Encuesta("Encuesta de Satisfacción", 
                "Obtener feedback de los estudiantes", 
                "Básico", 
                10, 
                "Carlos Pérez", 
                preguntasEncuesta);

        // Agregar actividades al Learning Path
        profesor1.agregarActividadALearningPath(learningPath1, actividad1);
        profesor1.agregarActividadALearningPath(learningPath1, actividad2);
        profesor1.agregarActividadALearningPath(learningPath1, actividad3);
        profesor1.agregarActividadALearningPath(learningPath1, actividad4);

        // Mostrar los Learning Paths creados por el profesor
        profesor1.mostrarLearningPaths();

        // Crear un estudiante
        Estudiante estudiante1 = new Estudiante("Juan López", "jlopez@learning.com");
        Estudiante estudiante2 = new Estudiante("Ana García", "agarcia@learning.com");

        // Inscribir a los estudiantes en el Learning Path
        estudiante1.inscribirseEnLearningPath(learningPath1);
        estudiante2.inscribirseEnLearningPath(learningPath1);

        // Crear control de progreso para estudiante 1
        ControlPath controlEstudiante1 = new ControlPath(
                learningPath1.getTitulo(),
                true,
                new Date(),
                null,
                learningPath1.getActividades().size(),
                0,
                0,
                new ArrayList<>()
        );

        // Crear control de progreso para estudiante 2
        ControlPath controlEstudiante2 = new ControlPath(
                learningPath1.getTitulo(),
                true,
                new Date(),
                null,
                learningPath1.getActividades().size(),
                0,
                0,
                new ArrayList<>()
        );

        // Estudiante 1 completa el Quiz
        estudiante1.realizarActividad(actividad2);  // Método que el estudiante completa la actividad
        controlEstudiante1.agregarActividadCompletada();
        controlEstudiante1.actualizarProgreso((float) controlEstudiante1.getActividadesCompletadas() / learningPath1.getActividades().size() * 100);

        // Estudiante 2 completa el Recurso Educativo y el Examen
        estudiante2.realizarActividad(actividad1);
        estudiante2.realizarActividad(actividad3);
        controlEstudiante2.agregarActividadCompletada();
        controlEstudiante2.agregarActividadCompletada();
        controlEstudiante2.actualizarProgreso((float) controlEstudiante2.getActividadesCompletadas() / learningPath1.getActividades().size() * 100);

        // Mostrar progreso de los estudiantes
        System.out.println("Progreso de Juan López: " + controlEstudiante1.getProgreso() + "%");
        System.out.println("Progreso de Ana García: " + controlEstudiante2.getProgreso() + "%");

        // Dejar feedback en el Learning Path
        Feedback feedback1 = new Feedback("Excelente curso", new Date(), "Juan López", 5);
        Feedback feedback2 = new Feedback("Muy buen material, pero el examen fue muy largo", new Date(), "Ana García", 4);
        learningPath1.agregarFeedback(feedback1);
        learningPath1.agregarFeedback(feedback2);

        // Mostrar el feedback recibido para el Learning Path
        for (Feedback fb : learningPath1.getFeedback()) {
            System.out.println(fb.mostrarDetalles());
        }

        // Modificar el Learning Path
        profesor1.modificarLearningPath(learningPath1, 
                "Java para Principiantes", 
                "Curso básico de programación en Java", 
                1);

        // Eliminar una actividad del Learning Path
        profesor1.eliminarActividadDeLearningPath(learningPath1, actividad4);

        // Volver a mostrar los Learning Paths del profesor después de la modificación
        profesor1.mostrarLearningPaths();
    }
}



