package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	private Map<String, LearningPath> mapaPaths = new HashMap<>();
	private Map<String, Actividad> mapaActividades = new HashMap<>();
	private Map<String, Usuario> mapaUsuarios = new HashMap<>();
	
    private Usuario usuarioLocal;  //tipo de usuario

    //Método para crear usuario
    
   public void crearUsuario(String nombre, String email, String clave, int tipo) {
	   if (tipo == 1) { // 1 para profesor
		   Profesor nuevoU = new Profesor(nombre, email, clave);
	   }
	   else {
		   Estudiante nuevoU = new Estudiante(nombre, email, clave);
	   }
	   
	   
	   
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
