package controller;

import java.util.Map;

import models.Actividad;
import models.LearningPath;
import usuarios.Estudiante;
import usuarios.Usuario;

public class Controlador {
    private Map<String, LearningPath> datosPaths;  // Mapa de Paths por nombre
    private Map<String, Actividad> datosActividades;  // Mapa de actividades por nombre
    private Map<String, Usuario> datosUsuarios;  // Mapa de usuarios por nombre
    private String usuarioActual;  // Usuario que está actualmente activo

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
