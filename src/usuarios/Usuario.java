package usuarios;

//1 para profesor, 2 para estudiante
public class Usuario {
    
    private String nombre; //1
    private String email;//2
    private String clave;//3

    // Constructor
    public Usuario(String nombre, String email, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        
    }

    public String getClave() {
		return clave;
	}

	public void setClave(String nuevaClave) {
		this.clave = nuevaClave;
		System.out.println("La clave del usuario ha sido actualizado a: " + nuevaClave);
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para cambiar el email del usuario
    public void cambiarEmail(String nuevoEmail) {
        this.email = nuevoEmail;
        System.out.println("El email del usuario ha sido actualizado a: " + nuevoEmail);
    }

    // Método para mostrar la información del usuario
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
    }
}

