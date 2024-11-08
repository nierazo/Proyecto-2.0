package feedback;

import java.util.Date;

public class Feedback {
    private String comentario;  // Comentario del estudiante o profesor
    private Date fecha;  // Fecha en que se dejó el feedback
    private String usuario;  // Usuario que dejó el feedback
    private float rating;  // Puntuación de la actividad (entre 0 y 5, por ejemplo)

    // Constructor para crear un nuevo Feedback
    public Feedback(String comentario, Date fecha, String usuario, float rating) {
        this.comentario = comentario;
        this.fecha = fecha;
        this.usuario = usuario;
        this.rating = rating;
    }

    // Getters y Setters

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            System.out.println("Rating inválido. Debe estar entre 0 y 5.");
        }
    }

    // Método para mostrar los detalles del feedback
    public String mostrarDetalles() {
        return "Feedback dejado por: " + usuario + "\nComentario: " + comentario + 
               "\nFecha: " + fecha + "\nRating: " + rating + "/5";
    }
}
