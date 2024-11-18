package usuarios;

import models.LearningPath;
import models.Actividad;
import java.util.ArrayList;
import java.util.Date;

public class Profesor extends Usuario {

    // Constructor para inicializar al profesor
    public Profesor(String nombre, String email, String clave) {
        super(nombre, email, clave);
    }

    // Método para crear un nuevo Learning Path
    public LearningPath crearLearningPath(String titulo, String descripcion, String objetivo, String contenido, int nivelDificultad) {
        String fechaCreacion = new Date().toString();  // Usa la fecha actual como fecha de creación
        LearningPath learningPath = new LearningPath(this.getNombre(), titulo, descripcion, objetivo, contenido, nivelDificultad, fechaCreacion);
        this.learningPaths.add(learningPath);  // Añadir el Learning Path a la lista del profesor
        return learningPath;
    }

    // Método para modificar un Learning Path existente
    public void modificarLearningPath(LearningPath learningPath, String titulo, String descripcion, int nivelDificultad) {
        learningPath.modificarLearningPath(titulo, descripcion, nivelDificultad);
        System.out.println("Learning Path '" + learningPath.getTitulo() + "' ha sido modificado.");
    }

    // Método para agregar una actividad a un Learning Path
    public void agregarActividadALearningPath(LearningPath learningPath, Actividad actividad) {
        learningPath.agregarActividad(actividad);
        System.out.println("Actividad añadida al Learning Path '" + learningPath.getTitulo() + "'.");
    }

    // Método para eliminar una actividad de un Learning Path
    public void eliminarActividadDeLearningPath(LearningPath learningPath, Actividad actividad) {
        learningPath.eliminarActividad(actividad);
        System.out.println("Actividad eliminada del Learning Path '" + learningPath.getTitulo() + "'.");
    }

    // Método para mostrar todos los Learning Paths creados por el profesor
    public void mostrarLearningPaths() {
        System.out.println("Learning Paths creados por " + this.getNombre() + ":");
        for (LearningPath lp : this.learningPaths) {
            System.out.println("Título: " + lp.getTitulo() + " | Descripción: " + lp.getDescripcion());
        }
    }

    // Getter para obtener los Learning Paths
    public ArrayList<LearningPath> getLearningPaths() {
        return learningPaths;
    }
}



