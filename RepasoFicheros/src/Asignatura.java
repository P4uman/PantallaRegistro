import java.io.Serializable;

public class Asignatura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo_asignatura;
	private String nombre;
	private String profesor;
	private int num_horas;
	
	public Asignatura() {
		
	}
	
	public Asignatura(int cod, String nombre, String profesor, int num_horas) {
		codigo_asignatura = cod;
		this.nombre = nombre;
		this.profesor = profesor;
		this.num_horas = num_horas;
	}
	
	public int getNum_horas() {
		return num_horas;
	}
	public void setNum_horas(int num_horas) {
		this.num_horas = num_horas;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo_asignatura() {
		return codigo_asignatura;
	}
	public void setCodigo_asignatura(int codigo_asignatura) {
		this.codigo_asignatura = codigo_asignatura;
	}
}
