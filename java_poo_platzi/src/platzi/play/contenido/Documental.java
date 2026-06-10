package platzi.play.contenido;

public class Documental extends Contenido{
	public String narrador;

	public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
		super(titulo, duracion, genero, calificacion);
		this.narrador = narrador;
	}
	
	public Documental(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad, String narrador) {
		super(titulo, duracion, genero, idioma, calidad);
		this.narrador = narrador;
	}

	public String getNarrador() {
		return narrador;
	}

	public void setNarrador(String narrador) {
		this.narrador = narrador;
	}

	@Override
	public void reproducir() {
		System.out.println("Reproduciendo documental: "+ this.getTitulo() + " narrado por " + this.getNarrador());		
	}
	
}
