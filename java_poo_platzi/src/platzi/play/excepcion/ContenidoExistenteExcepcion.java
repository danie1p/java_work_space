package platzi.play.excepcion;

public class ContenidoExistenteExcepcion extends RuntimeException {
	public ContenidoExistenteExcepcion(String titulo) {
		super("El contenido: " + titulo + " ya existe.");
	}
}