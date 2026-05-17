package platzi.play.plataforma;

import java.time.LocalDateTime;

import platzi.play.contenido.Pelicula;

public class Usuario {
	private String nombre;
	private String email;
	private LocalDateTime fechaRegistro;
	
	public Usuario(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
		this.fechaRegistro = LocalDateTime.now();
	}

	public void ver(Pelicula pelicula) {
		System.out.println(nombre + " esta viendo...");
		pelicula.reproducir();
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

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
}
