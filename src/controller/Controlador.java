package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.*;


import models.Actividad;
import models.Examen;
import feedback.Feedback;
import models.LearningPath;
import models.Quiz;
import usuarios.Estudiante;
import usuarios.Usuario;

public class Controlador {
	
	String rutaPaths = "./datos/paths.json";
	String rutaActividades = "./datos/actividades.json";
	String rutaUsuarios = "./datos/usuarios.json";
	
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
    private Map<String, LearningPath> datosPaths = objectMapper.readValue(new File(rutaPaths), new TypeReference<Map<String, Object>>() {});// Mapa de Paths por nombre
    private Map<String, Actividad> datosActividades = objectMapper.readValue(new File(rutaActividades), new TypeReference<Map<String, Object>>() {});// Mapa de actividades por nombre
    private Map<String, Usuario> datosUsuarios = objectMapper.readValue(new File(rutaUsuarios), new TypeReference<Map<String, Object>>() {});// Mapa de usuarios por nombre
    
    private String usuarioActual;  // Usuario que está actualmente activo
    
    
    // Métodos para buscar y actualizar datos
    
    public void agregarPath(LearningPath path) {
    	datosPaths.put(path.getTitulo(), path);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	objectMapper.writeValue(rutaPaths, path);
        System.out.println("Path agregado exitosamente.");
    	
    }
    
    public void agregarActividad(Actividad actividad) {
    	datosActividad.put(actividad.getTitulo(), actividad);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	objectMapper.writeValue(rutaActividades, actividad);
        System.out.println("Actividad agregada exitosamente.");
    	
    }
    
    public void agregarUsuario(Usuario usuario) {
    	datosUsuarios.put(usuario.getNombre(), usuario);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	objectMapper.writeValue(rutaUsuarios, usuario);
        System.out.println("Usuario agregado exitosamente.");
    	
    }
    
    public Boolean verificarUsuario(String usuario, String clave) {
    	Boolean rta = false;
    	if (datosUsuarios.containsKey(usuario)) {
    		String guardada = datosUsuarios.get(usuario).getClave();
    		
    		if (guardada == clave) {
    			rta = true;
    		}
    	}
    	return rta;
    }
    
    
    public void buscarYActualizarDatos() {
        // Implementación para buscar y actualizar los datos de Paths, Actividades y Usuarios
        System.out.println("Buscando y actualizando datos...");
    }

    // Método para validar las respuestas de las actividades
    public boolean validarActividadesRespuestasExamen(String actividad, ArrayList<String> respuestas) {
        Boolean rta = false;
    	// Implementación para validar respuestas de actividades
        System.out.println("Validando respuestas de actividades...");
        
        Examen acti = (Examen) datosActividades.get(actividad);
        
        ArrayList<String> standart = acti.getPreguntas();
        if (standart == respuestas) {
        	rta = true;
        }
        
        return rta;  
    }
    
    public boolean validarActividadesRespuestasQuiz(String actividad, String[] respuestas) {
        Boolean rta = false;
    	// Implementación para validar respuestas de actividades
        System.out.println("Validando respuestas de actividades...");
        
        Quiz acti = (Quiz) datosActividades.get(actividad);
        
        String[] standart = acti.getPreguntas();
        if (standart == respuestas) {
        	rta = true;
        }
        
        return rta;  
    }

    // Método para actualizar estadísticas del estudiante
    public void actualizarEstadisticasEstudiante(Estudiante estudiante, ControlPath control, String actividad, String path) {
    	Estudiante usuario = (Estudiante) datosUsuarios.get(estudiante.getNombre());
    	HashMap<String, ControlPath> existentes = usuario.getControl();
    	existentes.put(path, control);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	objectMapper.writeValue(rutaUsuarios, existentes);
        // Implementación para actualizar estadísticas de un estudiante
        System.out.println("Actualizando estadísticas del estudiante...");
    }

    // Método para actualizar Paths y Actividades
    public void actualizarPaths(LearningPath path) {
        // Implementación para actualizar Paths y Actividades
    	datosPaths.put(path.getTitulo(), path);
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	objectMapper.writeValue(rutaPaths, path);
        System.out.println("Actualizando Paths...");
    }
    
    public void actualizarActividades(Actividad actividad) {
        // Implementación para actualizar Paths y Actividades
    	datosActividades.put(actividad.getTitulo(), actividad);
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	objectMapper.writeValue(rutaActividades, actividad);
        System.out.println("Actualizando Actividades...");
    }

    
    public void agregarResena(Feedback resena, String path) {
    	LearningPath lp = datosPaths.get(path);
    	ArrayList<Feedback> feedback = lp.getFeedback();
    	feedback.add(resena);
    	ObjectMapper objectMapper = new ObjectMapper();
    	lp.setRating((resena.getRating() + lp.getSumaAnterior())/feedback.size());
    	objectMapper.writeValue(rutaPaths, path);
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
