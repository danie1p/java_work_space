package platzi.play.contenido;

public class Pelicula extends Contenido {

	public Pelicula(String titulo, int duracion, Genero genero, double calificacion) {
		super(titulo, duracion, genero, calificacion);
	}
	
	public Pelicula(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad) {
		super(titulo, duracion, genero, idioma, calidad);
	}

	@Override
	public void reproducir() {
		System.out.println("Reproduciendo la pelicula: " + this.getTitulo());		
	}
	
}
