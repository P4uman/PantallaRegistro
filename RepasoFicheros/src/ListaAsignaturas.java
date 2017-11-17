import java.util.ArrayList;
import java.util.List;

public class ListaAsignaturas {
	private List<Asignatura> lista = new ArrayList<Asignatura>();
	public ListaAsignaturas() {}
	public void add(Asignatura as){
	lista.add(as);
	}
	public List<Asignatura> getListaEmpleados(){
	return lista;
	}
}
