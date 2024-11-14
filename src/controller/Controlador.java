package controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;


import usuarios.Estudiante;
import usuarios.Profesor;


import models.Actividad;
import models.LearningPath;
import usuarios.Estudiante;
import usuarios.Usuario;

public class Controlador {
    private LearningPath pathLocal;  
    private Actividad actividadLocal;
    private Usuario usuarioLocal;  

    //Método para crear usuario
    
   public void crearUsuario(String nombre, String email, String clave, int tipo) {
	   if (tipo == 1) { // 1 para profesor
		   Profesor nuevoU = new Profesor(nombre, email, clave);
	   }
	   else {
		   Estudiante nuevoU = new Estudiante(nombre, email, clave);
	   }
	   
	   
	   
   }
   
   
   //Método para crear Learning Path
   public void crearLearningPath(String titulo, String descripcion, String objetivo, String contenido, int nivelDificultad, String fechaCreacion) {
	   LearningPath nuevoPath = new LearningPath(usuarioLocal.getNombre(), titulo, descripcion, objetivo, contenido, nivelDificultad, fechaCreacion);
  
	   
	   Scanner scanner = new Scanner(System.in);
	   int centinela = 1;
	   
	   while (centinela == 1) {
		   System.out.println("Para agregar una actividad al Learning Path ingrese 1. Si no desea seguir agregando actvidades ingrese 0.");
		   centinela = scanner.nextInt();
		   System.out.println("Ingrese el nombre de la actividad a agregar.");
		   String nombreActividad = scanner.nextLine();
		   
		   
	   }
	   
	   
   }
   
  //Método para crear Actividad
   public void crearActividad(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracionEsperada) {
	   Actividad nuevaActividad = new Actividad(nombre, descripcion, objetivo, nivelDificultad, duracionEsperada, usuarioLocal.getNombre());
	   
	   String nombreCSV = nuevaActividad.getNombre() + ".csv";
	   String separador = ";";
	   
	   ArrayList<String> cabecera = new ArrayList<>();
	   
	   String[] lista = {"Nombre", "Descripcion", "Objetivo", "Nivel de dificultad",
			   "Duracion esperada", "Prerrequisitos", "Fecha limite", "Obligatorio",
			   "Resultado", "Rating", "Suma rating", "creador"};
	    for (String l: lista) {
	    	cabecera.add(l);
	    }
	    
	    
	    linea = {};
	   
	   
	   
	   
	   
	   
	   String nombree = nuevaActividad.getNombre();
	   String 
	   
	   System.out.println("La actividad se ha guardado exitosamente");
   }
   
   public void leerActividad(String nombre) {
	   String ruta = nombre + ".csv";
	   
   }
   
    
    
    
    // Método para buscar y actualizar datos
    public void buscarYActualizarDatos() {
        // Implementación para buscar y actualizar los datos de Paths, Actividades y Usuarios
        System.out.println("Buscando y actualizando datos...");
    }

    // Método para validar las respuestas de las actividades
    public boolean validarActividadesRespuestas() {
        // Implementación para validar respuestas de actividades
        System.out.println("Validando respuestas de actividades...");
        return true;  // Se asume que la validación fue exitosa
    }

    // Método para actualizar estadísticas del estudiante
    public void actualizarEstadisticasEstudiante(Estudiante estudiante) {
        // Implementación para actualizar estadísticas de un estudiante
        System.out.println("Actualizando estadísticas del estudiante...");
    }

    // Método para actualizar Paths y Actividades
    public void actualizarPathsActividades(LearningPath path) {
        // Implementación para actualizar Paths y Actividades
        System.out.println("Actualizando Paths y Actividades...");
    }

    // Getters y Setters

    public Map<String, LearningPath> getDatosPaths() {
        return datosPaths;
    }

    public void setDatosPaths(Map<String, LearningPath> datosPaths) {
        this.datosPaths = datosPaths;
    }

    public Map<String, Actividad> getDatosActividades() {
        return datosActividades;
    }

    public void setDatosActividades(Map<String, Actividad> datosActividades) {
        this.datosActividades = datosActividades;
    }

    public Map<String, Usuario> getDatosUsuarios() {
        return datosUsuarios;
    }

    public void setDatosUsuarios(Map<String, Usuario> datosUsuarios) {
        this.datosUsuarios = datosUsuarios;
    }

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}
