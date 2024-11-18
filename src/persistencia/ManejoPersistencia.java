package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.ControlActividad;
import controller.ControlPath;
import feedback.Feedback;
import models.Actividad;
import models.LearningPath;
import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

public class ManejoPersistencia {
	private Map<String, LearningPath> mapaPaths = new HashMap<>();
	private Map<String, Actividad> mapaActividades = new HashMap<>();
	private Map<String, Usuario> mapaUsuarios = new HashMap<>();
	
	//SECCI0N PARA ACTIVIDADES
	
	
	//Resolver parametro creador desde controlador
	//Método para crear Actividad
	   public Map<String, Actividad> crearActividadData(Date fecha, Boolean obligatorio,String nombre, String descripcion, String objetivo, String nivelDificultad, int duracionEsperada, String creador) {
		   Actividad nuevaActividad = new Actividad(fecha, obligatorio, nombre, descripcion, objetivo, nivelDificultad, duracionEsperada, creador);
		   
		   mapaActividades.put(nuevaActividad.getNombre(), nuevaActividad);
		 
		   guardarActividades();
		   
		   return mapaActividades;
	   }
	   
	   //Buscar una actividad
	   public Actividad buscarActividad(String nombre) {
		   return mapaActividades.get(nombre);
	   }
	   
	//Modificar actividad
	   
	   public Map<String, Actividad> modificarActividad(int parametro, String modificar, String actividad){
		   Actividad a = mapaActividades.get(actividad);  
		   
		   if (parametro == 1) {
			   a.setNombre(modificar);
		   }
		   
		   else if(parametro == 2) {
			   a.setDescripcion(modificar);
		   }
		   
		   else if(parametro == 3) {
			   a.setObjetivo(modificar);
		   }
		   
		   else if(parametro == 4) {
			   a.setNivelDificultad(modificar);
		   }
		   
		   else if(parametro == 5) {
			   int m = Integer.parseInt(modificar);
			   a.setDuracionEsperada(m);
		   }
		   
		   else if(parametro == 6) {
			   ArrayList<String> prerequisitos = a.getPrerequisitos();
			   
			   String[] p = modificar.split(",");
			   
			   System.out.println("Ingrese 1 para agregar los prerrequisitos ingresados, o 2 para eliminarlos.");
			   
			   Scanner scanner = new Scanner(System.in);
		       int numero = scanner.nextInt();
		       scanner.close();
			   
			   if (numero == 1) {
				   for (String pre: p) {
					   prerequisitos.add(pre);
				   }
			   }
			   
			   else if (numero == 2) {
				   for (String pre: p) {
					   prerequisitos.remove(pre);
				   }
			   }
			   
			   a.setPrerequisitos(prerequisitos);;
		   }
		   
		   else if(parametro == 7) {
			   
			   long milisegundos = 1636627200000L; //para inicializar date
		       Date fecha = new Date(milisegundos);
		       
			   SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	           
	           try {
	        	   fecha = formato.parse(modificar);
	           } catch (ParseException e) {
				e.printStackTrace();
	           		}
	           a.setFechaLimiteBasadaEnActividadAnterior(fecha);
		   }
		   
		   else if(parametro == 8) {
			   Boolean bol = Boolean.parseBoolean(modificar);
			   a.setObligatorioOpcional(bol);
		   }
		   
		   else if(parametro == 9) {
			   int m = Integer.parseInt(modificar);
			   a.setResultado(m);
		   }
		   
		   guardarActividades();
		   
		   return mapaActividades;
	   }
	   
	   
	//Guardar actividades
	   public Map<String, Actividad> guardarActividades(){
		   String nombreCSV = "data/datosActividades.csv";
		   
		   try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreCSV))) {
	           for (String llave: mapaActividades.keySet()) {
	        	   ArrayList<String> lineaActividad = formatoActividad(mapaActividades.get(llave));
	        	   String line = String.join(";", lineaActividad);
	               writer.write(line);
	               writer.newLine();
	           }
	      
	           System.out.println("Se ha guardado exitosamente.");
	           
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
		   
		   return mapaActividades;
	   }
	
	//formato en string de cada actividad
	public ArrayList<String> formatoActividad(Actividad a){
		   ArrayList<String> rta = new ArrayList<>();
		   
		   String nombre = a.getNombre();
		   String descripcion = a.getDescripcion();
		   String objetivo = a.getObjetivo();
		   String nivel = a.getNivel();
		   String duracion = String.valueOf(a.getDuracionEsperada());
		   
		   //Se transforma tipo Date en str
		   SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	       String fecha = formato.format(a.getFechaLimite());
		   
	       String obligatorio = String.valueOf(a.isObligatorioOpcional());
	       String resultado = String.valueOf(a.getResultado());
	       String rating = String.valueOf(a.getRating());
	       String sumaRating = String.valueOf(a.getSumaRating());
	       String creador = a.getCreador();
	       
	       String prerequisitos = "";
	       
	       ArrayList<String> p = a.getPrerequisitos();
	       for(String s: p) {
	    	   if (prerequisitos.length() == 0) {
	    		   prerequisitos = prerequisitos + s;
	    	   } else {
	    		   prerequisitos = prerequisitos + ", " + s;
	    	   }
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
	
	//Se lee el archivo y se crea el mapa de actividades
	public Map<String, Actividad> crearMapaActividades(){
		String nombreCSV = "data/datosActividades.csv";
		
		long milisegundos = 1636627200000L; //para inicializar date
	       Date fecha = new Date(milisegundos);
	       
		try (BufferedReader br = new BufferedReader(new FileReader(nombreCSV))) {
            String line;
            
            if ((line = br.readLine()) == null) {
            	System.out.println("No existen actividades. Debe primero crear una.");
            	
            	Scanner scanner = new Scanner(System.in);
            	System.out.print("Introduce la fecha (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			    Date fechaIn = new Date(milisegundos);
				try {
					fechaIn = dateFormat.parse(fechaStr);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}

                // Leer booleano
                System.out.print("Es obligatorio (true/false): ");
                Boolean obligatorioIn = scanner.nextBoolean();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                // Leer nombre
                System.out.print("Introduce el nombre: ");
                String nombreIn = scanner.nextLine();

                // Leer descripción
                System.out.print("Introduce la descripción: ");
                String descripcionIn = scanner.nextLine();

                // Leer objetivo
                System.out.print("Introduce el objetivo: ");
                String objetivoIn = scanner.nextLine();

                // Leer nivel de dificultad
                System.out.print("Introduce el nivel de dificultad: ");
                String nivelDificultadIn = scanner.nextLine();

                // Leer duración esperada
                System.out.print("Introduce la duración esperada (en horas): ");
                int duracionEsperadaIn = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                // Leer creador
                System.out.print("Introduce el nombre de usuario del creador: ");
                String creadorIn = scanner.nextLine();
            	
                scanner.close();
                
            	crearActividadData(fechaIn,obligatorioIn , nombreIn,  descripcionIn, objetivoIn, nivelDificultadIn, duracionEsperadaIn, creadorIn);
            }
            
            else {
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                
                String nombre = values[0];
                String descripcion = values[1];
                String objetivo = values[2];
                String nivel = values[3];
                int duracion = Integer.parseInt(values[4]);
                
                
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                String f = values[5];
                
                try {
     			fecha = formato.parse(f);
     		} catch (ParseException e) {
     			e.printStackTrace();
     		}
                
                Boolean obligatorio = Boolean.parseBoolean(values[6]);
                int resultado = Integer.parseInt(values[7]);
                float rating = Float.parseFloat(values[8]);
                float suma = Float.parseFloat(values[9]);
                String creador = values[10];
                
                ArrayList<String> prerequisitos = new ArrayList<>();
                
                String[] p = values[11].split(",");
                
                for (String s: p) {
                	prerequisitos.add(s);
                }
                
                Actividad actividad = new Actividad(fecha, obligatorio, nombre, descripcion, objetivo, nivel, duracion, creador);
                
         	    actividad.setResultado(resultado);
         	    actividad.setRating(rating);
         	    actividad.setSumaRating(suma);
         	    actividad.setPrerequisitos(prerequisitos);
                
         	    mapaActividades.put(actividad.getNombre(), actividad);
                
            }
		}
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.out.println("Las actividades se han cargado exitosamente.");
		
		return mapaActividades;
	}
		
	//FIN SECCION PARA ACTIVIDADES
		
		
	//SECCION PARA LEARNING PATHS
		
		public Map<String, LearningPath> crearPathData(int duracion, String creador, String titulo, String descripcion,
				String objetivo, String contenido, int nivelDificultad, String fechaCreacion) {
			
			LearningPath nuevoPath = new LearningPath(duracion, creador, titulo, descripcion, objetivo, contenido, nivelDificultad, fechaCreacion);
			
			mapaPaths.put(nuevoPath.getTitulo(), nuevoPath);
			
			guardarPaths();
			   
			return mapaPaths;
		   }
		
		//Buscar un path
		   public LearningPath buscarPath(String nombre) {
			   return mapaPaths.get(nombre);
		   }
		
		
		public Map<String, LearningPath> modificarPath(int parametro, String modificar, String path){
			   LearningPath lp = mapaPaths.get(path); 
			   
			   LocalDate fechaHoy = LocalDate.now();
		       DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		       String fechaFormateada = fechaHoy.format(formateador);
			   
		       lp.setFechaModificacion(fechaFormateada);
		       
			   if (parametro == 1) {
				   lp.setTitulo(modificar);
			   }
			   
			   else if (parametro == 2) {
				   lp.setDescripcion(modificar);
			   }
			   
			   else if (parametro == 3) {
				   lp.setObjetivo(modificar);
			   }
			   
			   else if (parametro == 4) {
				   lp.setContenido(modificar);
			   }
			   
			   else if (parametro == 5) {
				   int m = Integer.parseInt(modificar);
				   lp.setNivelDificultad(m);
			   }
			   
			   else if (parametro == 6) {
				   int m = Integer.parseInt(modificar);
				   lp.setDuracion(m);
			   }
			   
			   else if (parametro == 7) {				   
				   ArrayList<Actividad> actividades = lp.getActividades();
				   Actividad actividad = mapaActividades.get(modificar);
				   
				   System.out.println("Ingrese 1 para agregar la actividad ingresada, o 2 para eliminarla.");
				   
				   Scanner scanner = new Scanner(System.in);
			       int numero = scanner.nextInt();
			       scanner.close();
				   
				   if (numero == 1) {
					   actividades.add(actividad);
				   }
				   
				   else if (numero == 2) {
					   actividades.remove(actividad);
				   }
				   
				   lp.setActividades(actividades);
				   
			   }
			   
			   guardarPaths();
			   
			   return mapaPaths;   
		}
			   
		//formato en string de cada path
		public ArrayList<String> formatoPath(LearningPath lp){
			   ArrayList<String> rta = new ArrayList<>();
			   
			   String creador = lp.getCreador();
			   String titulo = lp.getTitulo();
			   String descripcion = lp.getDescripcion();
			   String objetivo = lp.getObjetivo();
			   String contenido = lp.getContenido();
			   String nivelDificultad = String.valueOf(lp.getNivelDificultad());
			   String duracion = String.valueOf(lp.getDuracionTotal());
			   String fechaCreacion = lp.getFechaCreacion();
			   String fechaModificacion = lp.getFechaModificacion();
			   String rating = String.valueOf(lp.getRating());
			   String sumaRating = String.valueOf(lp.getSumaRating());
			   
			   ArrayList<Actividad> acti = lp.getActividades();
			   String actividades = "";
			   
			   for (Actividad a: acti) {
				   String nombreActividad = a.getNombre();
				   
				   if (actividades.length() == 0) {
		    		   actividades = actividades + nombreActividad;
		    	   } else {
		    		   actividades = actividades + ", " + nombreActividad;
		    	   }   
			   }
			   
			   ArrayList<Feedback> feed = lp.getFeedback();
			   String feedback = "";
			   
			   for (Feedback f: feed) {
				   String miniString = "";
				   String com = f.getComentario();
				 
				   SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			       String fecha = formato.format(f.getFecha());
			       
			       String usu = f.getUsuario();
			       String ra = String.valueOf(f.getRating());
			       
			       miniString = miniString + com + ", " + fecha + ", " + usu + ", " + ra + "/";
			       feedback = feedback + miniString;
				   
			   }
			   
			   rta.add(creador);
			   rta.add(titulo);
			   rta.add(descripcion);
			   rta.add(objetivo);
			   rta.add(contenido);
			   rta.add(nivelDificultad);
			   rta.add(duracion);
			   rta.add(fechaCreacion);
			   rta.add(fechaModificacion);
			   rta.add(rating);
			   rta.add(sumaRating);
			   rta.add(actividades);
			   rta.add(feedback);
			   
			   
		       return rta;
		   }
		
		//Guardar paths
		   public Map<String, LearningPath> guardarPaths(){
			   String nombreCSV = "data/datosPaths.csv";
			   
			   try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreCSV))) {
		           for (String llave: mapaPaths.keySet()) {
		        	   ArrayList<String> lineaPath = formatoPath(mapaPaths.get(llave));
		        	   String line = String.join(";", lineaPath);
		               writer.write(line);
		               writer.newLine();
		           }
		      
		           System.out.println("Se ha guardado exitosamente.");
		           
		       } catch (IOException e) {
		           e.printStackTrace();
		       }
			   
			   return mapaPaths;
		   }
		   
		 //Se lee el archivo y se crea el mapa de paths
			public Map<String, LearningPath> crearMapaPaths(){
				String nombreCSV = "data/datosPaths.csv";
				
				long milisegundos = 1636627200000L; //para inicializar date
			       Date fechaFeed = new Date(milisegundos);
				
				try (BufferedReader br = new BufferedReader(new FileReader(nombreCSV))) {
		            String line;
		            while ((line = br.readLine()) != null) {
		                String[] values = line.split(";");
		                
		                String creador = values[0];
		                String titulo = values[1];
		                String descripcion = values[2];
		                String objetivo = values[3];
		                String contenido = values[4];
		                int nivelDificultad = Integer.parseInt(values[5]);
		                int duracion = Integer.parseInt(values[6]);
		                String fechaCreacion = values[7];
		                String fechaModificacion = values[8];
		                float rating = Float.parseFloat(values[9]);
		                float sumaRating = Float.parseFloat(values[10]);
		               
		                ArrayList<Actividad> actividades = new ArrayList<>();
		                String[] act = values[11].split(",");
		                
		                for (String nombreActividad: act) {
		                	Actividad actividadAgregar = mapaActividades.get(nombreActividad);
		                	actividades.add(actividadAgregar);
		                }
		                
		                ArrayList<Feedback> feedback = new ArrayList<>();
		                String[] feed = values[12].split("/");
		                
		                for (String f: feed) {
		                	String[] fifi = f.split(",");
		                	
		                	String comentarioFeed = fifi[0];
		                	
		                	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		                    String fech = fifi[1];
		                    
		                    try {
		         			fechaFeed = formato.parse(fech);
		         		} catch (ParseException e) {
		         			e.printStackTrace();
		         		}
		                    String usuarioFeed = fifi[2];
		                    float ratingFeed = Float.parseFloat(fifi[3]);
		                    
		                	Feedback feedbackGuardar = new Feedback(comentarioFeed, fechaFeed, usuarioFeed, ratingFeed);
		                	feedback.add(feedbackGuardar);
		                	
		                }
		                
		                LearningPath lpAgregar = new LearningPath(duracion, creador, titulo, descripcion, objetivo, contenido, nivelDificultad, fechaCreacion);
		                lpAgregar.setActividades(actividades);
		                lpAgregar.setFeedback(feedback);
		                lpAgregar.setRating(rating);
		                lpAgregar.setSumaRating(sumaRating);
		                lpAgregar.setFechaModificacion(fechaModificacion);
		                
		                mapaPaths.put(lpAgregar.getTitulo(), lpAgregar);
		           
		            }
		                         
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
				System.out.println("Los paths se han cargado exitosamente.");
				
				return mapaPaths;
			}	   
	
	//FIN SECCION PARA LEARNING PATHS
		
	//SECCION PARA USUARIOS
			public Map<String, Usuario> crearUsuarioData(String nombre, String email, String clave, int tipo) {
				
				if (tipo == 1) {
					Profesor usu = new Profesor(nombre, email, clave);
					mapaUsuarios.put(usu.getNombre(), usu);
				}
				else if (tipo == 2) {
					Estudiante usu = new Estudiante(nombre, email, clave);
					mapaUsuarios.put(usu.getNombre(), usu);
				}
				
				guardarUsuarios(tipo);
				   
				return mapaUsuarios;
			   }
			
			//Buscar un usuario
			   public Usuario buscarUsuario(String nombre, int tipo) {
				   Usuario usu = mapaUsuarios.get(nombre);
				   
				   if (tipo == 1) {
					   usu = (Profesor) usu;
					  
				   }
				   else if (tipo == 2) {
					   usu = (Estudiante) usu;
					   
				   }
				   return usu;
				   
			   }

			//Modificar usuario
			   
			   public Map<String, Usuario> modificarUsuario(int parametro, String modificar, String usuario, int tipo){
				   if (tipo == 1) {
					   Profesor profe = (Profesor) mapaUsuarios.get(usuario);
					   if (parametro == 1) {
						   profe.setNombre(modificar);
					   }
					   else if (parametro == 2) {
						   profe.setEmail(modificar);
					   }
					   else if (parametro ==3) {
						   profe.setClave(modificar);
					   }
				   
				   }
				   else if (tipo == 2) {
					   Estudiante estu = (Estudiante) mapaUsuarios.get(usuario);
					   if (parametro == 1) {
						   estu.setNombre(modificar);
					   }
					   else if (parametro == 2) {
						   estu.setEmail(modificar);
					   }
					   else if (parametro ==3) {
						   estu.setClave(modificar);
					   }
				  
				   }
				 
				   guardarUsuarios(tipo);
				   
					return mapaUsuarios;
			   }
			
			 //formato en string de cada usuario
				public ArrayList<String> formatoUsuario(Usuario usu, int tipo){
					   ArrayList<String> rta = new ArrayList<>(); 
					   
					   if (tipo == 1) {
						   
						   String nombre = usu.getNombre();
						   String email = usu.getEmail();
						   String clave = usu.getClave();
						   
						   rta.add(String.valueOf(tipo));
						   rta.add(nombre);
						   rta.add(email);
						   rta.add(clave);
					   }
					   else if(tipo == 2) {
						   Estudiante estu = (Estudiante) usu;
						   
						   String nombre = estu.getNombre();
						   String email = estu.getEmail();
						   String clave = estu.getClave();
						   
						   String controlPath = "";
						   
						   ArrayList<ControlPath> control = estu.getControlPaths();
						   
						   for (ControlPath c: control) {
							   String miniString = "";
							   
							   String nombrePath = c.getNombrePath();
							   String enCurso = String.valueOf(c.isEnCurso());
							   
							   SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
						       String fechaInicio = formato.format(c.getFechaInicio());
						       String fechaFinalizacion = formato.format(c.getFechaFinalizacion());
						       
						       String totalActividades = String.valueOf(c.getTotalActividades());
						       String actividadesCompletadas = String.valueOf(c.getActividadesCompletadas());
						       String progreso = String.valueOf(c.getProgreso());
						       String controlActividades = "";
						       
						       ArrayList<ControlActividad> controlActi = c.getActividades();
						       
						       for (ControlActividad ca: controlActi) {
						    	   String miniCa = "";
						    	   
						    	   String nombreCa = ca.getNombreActividad();
						    	   String estadoCa = ca.getEstado();
						    	   String fechaCompletarCa = formato.format(ca.getFechaCompletar());
						    	   String tiempoDedicadoCa = String.valueOf(ca.getTiempoDedicado());
						    	   String tasaExito = String.valueOf(ca.getTasaExitoFracaso());
						    	   String calificacionCa = String.valueOf(ca.getCalificacion());
						    	   String medioEntrega = ca.getMedioEntrega();
						    	   
						    	   miniCa = miniCa + nombreCa + ", " + estadoCa + ", " + fechaCompletarCa + ", ";
						    	   miniCa = miniCa + tiempoDedicadoCa + ", " + tasaExito + ", " + calificacionCa + ", " + medioEntrega + "/";
						    	   
						    	   controlActividades = controlActividades + miniCa;
						    	   
						    	   //revisar como se estan separando los datos
						       }
						       
						       miniString = miniString + nombrePath + "$" + enCurso + "$" + fechaInicio + "$" + fechaFinalizacion;
						       miniString = miniString + totalActividades + "$" + actividadesCompletadas + "$" + progreso + "$" + controlActividades + "*";
						       
						       controlPath = controlPath + miniString;
							   
						   }
						   
						   rta.add(String.valueOf(tipo));
						   rta.add(nombre);
						   rta.add(email);
						   rta.add(clave);
						   rta.add(controlPath);
					   }
					   
					   return rta;
			   
				}
				
				
				//Guardar usuarios
				   public Map<String, Usuario> guardarUsuarios(int tipo){
					   String nombreCSV = "data/datosUsuarios.csv";
					   
					   try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreCSV))) {
				           for (String llave: mapaUsuarios.keySet()) {
				        	   ArrayList<String> lineaPath = formatoUsuario(mapaUsuarios.get(llave), tipo);
				        	   String line = String.join(";", lineaPath);
				               writer.write(line);
				               writer.newLine();
				           }
				      
				           System.out.println("Se ha guardado exitosamente.");
				           
				       } catch (IOException e) {
				           e.printStackTrace();
				       }
					   
					   return mapaUsuarios;
				   }
			
				   
				 //Se lee el archivo y se crea el mapa de usuarios
					public Map<String, Usuario> crearMapaUsuarios(){
						String nombreCSV = "data/datosUsuarios.csv";
						
						long milisegundos = 1636627200000L; //para inicializar date
					       Date fechaInicio = new Date(milisegundos);
					       Date fechaFinalizacion = new Date(milisegundos);
					       Date fechaCompletar = new Date(milisegundos);
						
						try (BufferedReader br = new BufferedReader(new FileReader(nombreCSV))) {
				            String line;
				            while ((line = br.readLine()) != null) {
				                String[] values = line.split(";");
				                
				                int tipo = Integer.parseInt(values[0]);
				                if (tipo == 1) {
				                	String nombre = values[1];
				                	String email = values[2];
				                	String clave = values[3];
				                	
				                	Profesor profesor = new Profesor(nombre, email, clave);
				                	mapaUsuarios.put(profesor.getNombre(), profesor);
				                }
				                else if (tipo == 2) {
				                	String nombre = values[1];
				                	String email = values[2];
				                	String clave = values[3];
				                	
				                	ArrayList<ControlPath> controlPaths = new ArrayList<>();
				                	
				                	String[] lineaControlPaths = values[4].split("*");
				                	for (String cp: lineaControlPaths) {
				                		String[] atributosControlPath = cp.split("$");
				                		String nombrePath = atributosControlPath[0];
				                		Boolean enCurso = Boolean.parseBoolean(atributosControlPath[1]);
				                		
				                		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
						                String f = atributosControlPath[2];
				                		
						                try {
							     			fechaInicio = formato.parse(f);
							     		} catch (ParseException e) {
							     			e.printStackTrace();
							     		}
						                
						                String fa = atributosControlPath[3];
				                		
						                try {
							     			fechaFinalizacion = formato.parse(fa);
							     		} catch (ParseException e) {
							     			e.printStackTrace();
							     		}
						                
						                int totalActividades = Integer.parseInt(atributosControlPath[4]);
						                int actividadesCompletadas = Integer.parseInt(atributosControlPath[5]);
						                float progreso = Float.parseFloat(atributosControlPath[6]);
						                
						                ArrayList<ControlActividad> actividades = new ArrayList<>();
						                
						                String[] controlesActividad = atributosControlPath[7].split("/");
						                
						                for (String lineaControlActividad: controlesActividad) {
						                	String[] atributosActividad = lineaControlActividad.split(",");
						                	
						                	String nombreAc = atributosActividad[0];
						                	String estadoAc = atributosActividad[1];
						                	
						                	String fc = atributosActividad[2];
					                		
							                try {
								     			fechaCompletar = formato.parse(fc);
								     		} catch (ParseException e) {
								     			e.printStackTrace();
								     		}
						                	
						                	float tiempoDedicadoAc = Float.parseFloat(atributosActividad[3]);
						                	float tasaExitoFAc = Float.parseFloat(atributosActividad[4]);
						                	float calificacionAc = Float.parseFloat(atributosActividad[5]);
						                	
						                	String medioEntregaAc = atributosActividad[6];
						                	
						                	ControlActividad controlActividadLocal = new ControlActividad(nombreAc, medioEntregaAc);
						                	controlActividadLocal.setEstado(estadoAc);
						                	controlActividadLocal.setFechaCompletar(fechaCompletar);
						                	controlActividadLocal.setTiempoDedicado(tiempoDedicadoAc);
						                	controlActividadLocal.setTasaExitoFracaso(tasaExitoFAc);
						                	controlActividadLocal.setCalificacion(calificacionAc);
						                	controlActividadLocal.setMedioEntrega(medioEntregaAc);
						                	
						                	actividades.add(controlActividadLocal);
						                	
						                }
						                
						                ControlPath controlPathLocal = new ControlPath(nombrePath, fechaInicio, fechaFinalizacion,
						                		totalActividades, progreso);
						                controlPathLocal.setEnCurso(enCurso);
						                controlPathLocal.setActividadesCompletadas(actividadesCompletadas);
						                controlPathLocal.setActividades(actividades);
						                
						                controlPaths.add(controlPathLocal);
						                
				                	}
				                	
				                	Estudiante estudiante = new Estudiante(nombre, email, clave);
				                	estudiante.setControlPaths(controlPaths);
				                	
				                	mapaUsuarios.put(estudiante.getNombre(), estudiante);
				                	
				                	
				                }
				              
				                
				            }
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
						
						System.out.println("Los usuarios se han cargado exitosamente.");
						
						return mapaUsuarios;
					}	   
				   

	//FIN SECCION PARA USUARIOS
	}
	
