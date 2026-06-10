package platzi.play.plataforma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.Contenido;
import platzi.play.contenido.Documental;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.ContenidoExistenteExcepcion;
import platzi.play.util.FileUtils;

public class Plataforma {
	private String nombre;
	private List<Contenido> contenido;
	private Map<Contenido, Integer> visualizaciones;
	
	public Plataforma(String nombre) {
		this.nombre = nombre;
		this.contenido = new ArrayList<Contenido>();
		this.visualizaciones = new HashMap<>();
	}
	
	public void agregar(Contenido elemento) {
		Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());
		
		if (contenido != null) {
			throw new ContenidoExistenteExcepcion(elemento.getTitulo());
		}
		
		FileUtils.escribirContenido(elemento);
		this.contenido.add(elemento);
	}
	
	public void reproducir(Contenido contenido) {
		int conteoActual = visualizaciones.getOrDefault(contenido, 0);
		System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces");
		this.contarVisualizaciones(contenido);
		contenido.reproducir();
	}
	
	private void contarVisualizaciones(Contenido contenido) {
		int conteoActual = visualizaciones.getOrDefault(contenido, 0);
		visualizaciones.put(contenido, conteoActual + 1);
	}
	
	public List<String> getTitulos() {
//		for (int i = 0; i < contenido.size(); i++) {
//			System.out.println(contenido.get(i).getTitulo());
//		}
		
//		for (Contenido pelicula : contenido) {
//			System.out.println(pelicula.getTitulo());
//		}
		
		return contenido
					    .stream()
					    .map(Contenido::getTitulo)
					    .toList();
	}
	
	public void eliminar(Contenido pelicula) {
		contenido.remove(pelicula);
	}
	
	public List<ResumenContenido> getResumenes() {
		return contenido
					.stream()
					.map(Pelicula -> new ResumenContenido(Pelicula.getTitulo(), Pelicula.getDuracion(), Pelicula.getGenero()))
					.toList();
	}
	
	public Contenido buscarPorTitulo(String titulo) {
//		for (Contenido pelicula : contenido) {
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
	
	public List<Contenido> buscarPorGenero(Genero genero) {
		return contenido
						.stream()
						.filter(Pelicula -> Pelicula.getGenero().equals(genero))
						.toList();
	}
	
	public List<Contenido> getPopuplares(int numero) {
		return contenido
						.stream()
						.sorted(Comparator.comparingDouble(Contenido::getCalificacion)
						.reversed())
						.limit(numero)
						.toList();
	}
	
	public List<Pelicula> getPeliculas() {
		return contenido.stream()
				.filter(contenido -> contenido instanceof Pelicula)
				.map(contenidoFiltrado -> (Pelicula) contenidoFiltrado)
				.toList();
	}
	
	public List<Documental> getDocumentales() {
		return contenido.stream()
				.filter(contenido -> contenido instanceof Documental)
				.map(contenidoFiltrado -> (Documental) contenidoFiltrado)
				.toList();
	}
	
	public List<Contenido> getPeliculasConCalificacionMayorA(int calificacion) {
		return contenido
						.stream()
						.filter(Pelicula -> Pelicula.getCalificacion() >= calificacion)
						.toList();
	}
	
	public Contenido getLaMasPopular() {
		return this.getPopuplares(1).get(0);
	}
	
	public int getDuracionTotal() {
		return contenido
						.stream()
						.mapToInt(Contenido::getDuracion)
						.sum();
	}
	
	public List<Contenido> getContenido() {
		return contenido;
	}
	
	public String getNombre() {
		return nombre;
	}
}















 