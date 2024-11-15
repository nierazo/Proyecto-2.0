package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	   
	   String nombreCSV = "data/" + nuevaActividad.getNombre() + ".csv";
	   
	   ArrayList<String> lineaActividad = formatoActividad(nuevaActividad);
	
	   
	   try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreCSV))) {
           for (String dato: lineaActividad) {
        	   writer.write(dato);
        	   writer.newLine();
           }
      
           System.out.println("La actividad se ha guardado exitosamente");
           
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   
   
   public ArrayList<String> formatoActividad(Actividad a){
	   ArrayList<String> rta = new ArrayList<>();
	   
	   String nombre = a.getNombre();
	   String descripcion = a.getDescripcion();
	   String objetivo = a.getObjetivo();
	   String nivel = a.getNivel();
	   String duracion = String.valueOf(a.getDuracionEsperada());
	   
	   //Se transforma tipo Date en str
	   SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
       String fecha = formato.format(a.getFechaLimite());
	   
       String obligatorio = String.valueOf(a.isObligatorioOpcional());
       String resultado = String.valueOf(a.getResultado());
       String rating = String.valueOf(a.getRating());
       String sumaRating = String.valueOf(a.getSumaRating());
       String creador = a.getCreador();
       
       String prerequisitos = "";
       
       ArrayList<String> p = a.getPrerequisitos();
       for(String s: p) {
    	   prerequisitos = prerequisitos + ", " + s;
       }
	   
       rta.add(nombre);
       rta.add(descripcion);
       rta.add(objetivo);
       rta.add(nivel);
       rta.add(duracion);
       rta.add(fecha);
       rta.add(obligatorio);
       rta.add(resultado);
       rta.add(rating);
       rta.add(sumaRating);
       rta.add(creador);
       rta.add(prerequisitos);
       
       return rta;
   }
   
   public Actividad leerActividad(String nombreActividad) {
	   String ruta = "/data/" + nombreActividad + ".csv";
	   
	   String nombre = "";
       String descripcion = "";
       String objetivo = "";
       String nivel = "";
       int duracion = 0;
       
       long milisegundos = 1636627200000L; //para inicializar date
       Date fecha = new Date(milisegundos);
      
       Boolean obligatorio = true;
       int resultado = 0;
       float rating = 0;
       float suma = 0;
       String creador = "";
       ArrayList<String> prerequisitos = new ArrayList<>();
	   
	   try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
           nombre = br.readLine();
           descripcion = br.readLine();
           objetivo = br.readLine();
           nivel = br.readLine();
           duracion = Integer.parseInt(br.readLine());
           
           SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
           String f = br.readLine();
           
           try {
			fecha = formato.parse(f);
		} catch (ParseException e) {
			e.printStackTrace();
		}
           
           obligatorio = Boolean.parseBoolean(br.readLine());
           resultado = Integer.parseInt(br.readLine());
           rating = Float.parseFloat(br.readLine());
           suma = Float.parseFloat(br.readLine());
           creador = br.readLine();
           
           String linea = br.readLine();
           String[] p = linea.split(",");
           
           for (String s: p) {
        	   prerequisitos.add(s);
           }
           
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	   
	   Actividad actividad = new Actividad(nombre, descripcion, objetivo, nivel, duracion, creador);
	   
	   actividad.setFechaLimiteBasadaEnActividadAnterior(fecha);
	   actividad.setObligatorioOpcional(obligatorio);
	   actividad.setResultado(resultado);
	   actividad.setRating(rating);
	   actividad.setSumaRating(suma);
	   actividad.setPrerequisitos(prerequisitos);
	   
	   return actividad;
	   
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

    
}
