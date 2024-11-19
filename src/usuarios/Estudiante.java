package usuarios;

import controller.ControlPath;
import models.Actividad;
import models.LearningPath;
import java.util.ArrayList;

public class Estudiante extends Usuario {
    private ArrayList<ControlPath> controlPaths;  // Lista de ControlPath para gestionar el progreso de cada Learning Path

    // Constructor
    public Estudiante(String nombre, String email, String clave) {
        super(nombre, email, clave);  // Llama al constructor de Usuario
        this.controlPaths = new ArrayList<>();  // Inicializa la lista de ControlPath
    }

    // Método para inscribirse en un Learning Path
    public ControlPath inscribirseEnLearningPath(LearningPath learningPath) {
        ControlPath nuevoControlPath = new ControlPath(learningPath.getTitulo(), new java.util.Date(), null, learningPath.getActividades().size(), 0);
        this.controlPaths.add(nuevoControlPath);
        System.out.println(getNombre() + " se ha inscrito en el Learning Path: " + learningPath.getTitulo());
        return nuevoControlPath;
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
//Revisar metodo
    // Mostrar los Learning Paths en los que está inscrito
    //public void mostrarLearningPathsInscritos() {
        //System.out.println("Learning Paths inscritos para " + getNombre() + ":");
        //for (LearningPath lp : learningPathsInscritos) {
            //System.out.println("- " + lp.getTitulo());
        //}
    //}
    public void mostrarLearningPathsInscritos(ControlPath nuevoControlPath) {
    	
    	
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

	public ArrayList<ControlPath> getControlPaths() {
		return controlPaths;
	}

	public void setControlPaths(ArrayList<ControlPath> controlPaths) {
		this.controlPaths = controlPaths;
	}
    
}
