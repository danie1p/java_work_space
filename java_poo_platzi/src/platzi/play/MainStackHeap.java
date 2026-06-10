package platzi.play;

import platzi.play.contenido.Calidad;
import platzi.play.contenido.Genero;
import platzi.play.contenido.Idioma;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.Contenido;

public class MainStackHeap {
	public static void main(String[] args) {
		Contenido reyleon = new Pelicula("El Rey Leon", 135, Genero.ANIMADA, Idioma.ES, Calidad.UHD);
		Contenido harryPotter = new Pelicula("Harry Potter", 200, Genero.FANTASIA, Idioma.EN, Calidad.HD);
		
		reyleon = harryPotter;
		reyleon.setTitulo( "El Hobbit");
		
		System.out.println("rey leon: " + reyleon.getTitulo());
		System.out.println("harry potter: " + harryPotter.getTitulo());
	}
}
