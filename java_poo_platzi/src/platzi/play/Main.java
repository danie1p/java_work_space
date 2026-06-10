package platzi.play;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import platzi.play.contenido.Calidad;
import platzi.play.contenido.Genero;
import platzi.play.contenido.Idioma;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.Contenido;
import platzi.play.contenido.Documental;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.ContenidoExistenteExcepcion;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

public class Main {
	public static final String NOMBRE_PLATAFORMA = "PLATZI PLAY!";
	public static final String VERSION = "1.0.0";
	
	public static final int AGREGAR = 1;
	public static final int MOSTRAR_TODO = 2;
	public static final int BUSCAR_POR_TITULO = 3;
	public static final int BUSCAR_POR_GENERO = 4;
	public static final int VER_POPULARES = 5;
	public static final int REPRODUCIR = 6;
	public static final int CONTENIDO_MAS_POPULAR = 7;
	public static final int ELIMINAR = 8;
	public static final int SALIR = 9;

	public static void main(String[] args) {
		Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);		
		System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);
	
		
		cargarPeliculas(plataforma);
		
		System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido\n");
		
		while(true) {
			int opcionElegida = ScannerUtils.capturarNumero("""
					1. Agregar contenido
					2. Mostar todo
					3. Buscar por titulo
					4. Buscar por genero
					5. Ver Populares
					6. Reproducir
					7. Contenido mas popular
					8. Eliminar
					9. Salir
					""");		
			
			switch (opcionElegida) {
				case AGREGAR -> {
					int tipoContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar? \n1. Pelicula \n2. Documental");
					String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
					Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
					int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
					double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");
					Idioma idioma = ScannerUtils.capturarIdioma("Idioma del contenido");
					Calidad calidad = ScannerUtils.capturarCalidad("Calidad del contenido");
					
					
					try {
						if (tipoContenido == 1) {
							Contenido pelicula = new Pelicula(nombre, duracion, genero, idioma, calidad);
							plataforma.agregar(pelicula);
						} else {
							String narrador = ScannerUtils.capturarTexto("Narrador del documental");
							plataforma.agregar(new Documental(nombre, duracion, genero, idioma, calidad, narrador));
						}
						
					} catch (ContenidoExistenteExcepcion e) {
						System.out.println(e.getMessage());
					}
					
				}				
				case MOSTRAR_TODO ->{ 
					List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
					contenidosResumidos.forEach(ResumenContenido -> System.out.println(ResumenContenido.toString()));
				}				
				case BUSCAR_POR_TITULO -> {
					String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
					Contenido pelicula = plataforma.buscarPorTitulo(nombreBuscado);
					
					if (pelicula != null) {
						System.out.println(pelicula.obtenerFichaTenica());
					} else {
						System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
					}
				}	
				case BUSCAR_POR_GENERO -> {
					Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar.");
					List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
					System.out.println("\n" + contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado + "\n");
					contenidoPorGenero.forEach(Pelicula -> System.out.println(Pelicula.obtenerFichaTenica() + "\n"));
				}
				case VER_POPULARES -> {
					int cantidad = ScannerUtils.capturarNumero("Cantidad de resultas a mostrar?");
					List<Contenido> populares = plataforma.getPopuplares(cantidad);
					populares.forEach(Pelicula -> System.out.println(Pelicula.obtenerFichaTenica()));
				}
				
				case REPRODUCIR -> {
					String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
					Contenido contenido = plataforma.buscarPorTitulo(nombre);
					
					if (contenido != null) {
						plataforma.reproducir(contenido);
					} else {
						System.out.println(nombre + " no existe.");
					}
				}
				
				case CONTENIDO_MAS_POPULAR -> {
					System.out.println(plataforma.getLaMasPopular().obtenerFichaTenica());
				}
				
				case ELIMINAR -> {
					String tituloPorEliminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
					Contenido pelicula = plataforma.buscarPorTitulo(tituloPorEliminar);
					
					if (pelicula != null) {
						plataforma.eliminar(pelicula);
						System.out.println("El contenido: " + tituloPorEliminar + " ha sido eliminado de " + plataforma.getNombre());
					} else {
						System.out.println("El contenido: " + tituloPorEliminar + " no existe dentro de " + plataforma.getNombre());
					}
				}
				case SALIR -> System.exit(0);
			}
				
			
		}	

	}
	
	public static void cargarPeliculas(Plataforma plataforma) {
		plataforma.getContenido().addAll(FileUtils.leerContenido());
	}

}








