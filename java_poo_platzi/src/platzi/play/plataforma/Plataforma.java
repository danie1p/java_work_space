package platzi.play.plataforma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteExcepcion;
import platzi.play.util.FileUtils;

public class Plataforma {
	private String nombre;
	private List<Pelicula> contenido;
	private Map<Pelicula, Integer> visualizaciones;
	
	public Plataforma(String nombre) {
		this.nombre = nombre;
		this.contenido = new ArrayList<Pelicula>();
		this.visualizaciones = new HashMap<>();
	}
	
	public void agregar(Pelicula elemento) {
		Pelicula contenido = this.buscarPorPelicula(elemento.getTitulo());
		
		if (contenido != null) {
			throw new PeliculaExistenteExcepcion(elemento.getTitulo());
		}
		
		FileUtils.escribirContenido(elemento);
		this.contenido.add(elemento);
	}
	
	public void reproducir(Pelicula contenido) {
		int conteoActual = visualizaciones.getOrDefault(contenido, 0);
		System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces");
		this.contarVisualizaciones(contenido);
		contenido.reproducir();
	}
	
	private void contarVisualizaciones(Pelicula contenido) {
		int conteoActual = visualizaciones.getOrDefault(contenido, 0);
		visualizaciones.put(contenido, conteoActual + 1);
	}
	
	public List<String> getTitulos() {
//		for (int i = 0; i < contenido.size(); i++) {
//			System.out.println(contenido.get(i).getTitulo());
//		}
		
//		for (Pelicula pelicula : contenido) {
//			System.out.println(pelicula.getTitulo());
//		}
		
		return contenido
					    .stream()
					    .map(Pelicula::getTitulo)
					    .toList();
	}
	
	public void eliminar(Pelicula pelicula) {
		contenido.remove(pelicula);
	}
	
	public List<ResumenContenido> getResumenes() {
		return contenido
					.stream()
					.map(Pelicula -> new ResumenContenido(Pelicula.getTitulo(), Pelicula.getDuracion(), Pelicula.getGenero()))
					.toList();
	}
	
	public Pelicula buscarPorPelicula(String titulo) {
//		for (Pelicula pelicula : contenido) {
//			if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
//				return pelicula;
//			}
//		}
		
		return contenido.stream()
				 .filter(Pelicula -> Pelicula.getTitulo().equalsIgnoreCase(titulo))
				 .findFirst()
				 .orElse(null);
		
//		return null;
	}
	
	public List<Pelicula> buscarPorGenero(Genero genero) {
		return contenido
						.stream()
						.filter(Pelicula -> Pelicula.getGenero().equals(genero))
						.toList();
	}
	
	public List<Pelicula> getPopuplares(int numero) {
		return contenido
						.stream()
						.sorted(Comparator.comparingDouble(Pelicula::getCalificacion)
						.reversed())
						.limit(numero)
						.toList();
	}
	
	public List<Pelicula> getPeliculasConCalificacionMayorA(int calificacion) {
		return contenido
						.stream()
						.filter(Pelicula -> Pelicula.getCalificacion() >= calificacion)
						.toList();
	}
	
	public Pelicula getLaMasPopular() {
		return this.getPopuplares(1).get(0);
	}
	
	public int getDuracionTotal() {
		return contenido
						.stream()
						.mapToInt(Pelicula::getDuracion)
						.sum();
	}
	
	public List<Pelicula> getContenido() {
		return contenido;
	}
	
	public String getNombre() {
		return nombre;
	}
}















 