package usuarios;

import controller.ControlPath;
import models.Actividad;
import models.LearningPath;
import java.util.ArrayList;

public class Estudiante extends Usuario {
    private ArrayList<LearningPath> learningPathsInscritos;  // Lista de Learning Paths en los que está inscrito
    private ArrayList<ControlPath> controlPaths;  // Lista de ControlPath para gestionar el progreso de cada Learning Path

    // Constructor
    public Estudiante(String nombre, String email) {
        super(nombre, email);  // Llama al constructor de Usuario
        this.learningPathsInscritos = new ArrayList<>();
        this.controlPaths = new ArrayList<>();  // Inicializa la lista de ControlPath
    }

    // Método para inscribirse en un Learning Path
    public void inscribirseEnLearningPath(LearningPath learningPath) {
        this.learningPathsInscritos.add(learningPath);
        ControlPath nuevoControlPath = new ControlPath(learningPath.getTitulo(), false, new java.util.Date(), null, learningPath.getActividades().size(), 0, 0, new ArrayList<>());
        this.controlPaths.add(nuevoControlPath);
        System.out.println(getNombre() + " se ha inscrito en el Learning Path: " + learningPath.getTitulo());
    }

    // Método para mostrar el progreso de un Learning Path usando ControlPath
    public void mostrarProgresoLearningPath(LearningPath learningPath) {
        for (ControlPath cp : controlPaths) {
            if (cp.getNombrePath().equals(learningPath.getTitulo())) {
                System.out.println(getNombre() + " ha completado el " + cp.getProgreso() + "% del Learning Path: " + learningPath.getTitulo());
                return;
            }
        }
        System.out.println("No se encontró el progreso para el Learning Path: " + learningPath.getTitulo());
    }

    // Mostrar los Learning Paths en los que está inscrito
    public void mostrarLearningPathsInscritos() {
        System.out.println("Learning Paths inscritos para " + getNombre() + ":");
        for (LearningPath lp : learningPathsInscritos) {
            System.out.println("- " + lp.getTitulo());
        }
    }
    
 // Método para que el estudiante realice una actividad
    public void realizarActividad(Actividad actividad) {
        if (actividad.isCompletada()) {
            System.out.println(getNombre() + " ya completó la actividad: " + actividad.getDescripcion());
        } else {
            // Marca la actividad como completada
            actividad.setResultado(1);  // Puedes ajustar este valor para representar diferentes tipos de resultados
            System.out.println(getNombre() + " ha completado la actividad: " + actividad.getDescripcion());
        }
    }
}
