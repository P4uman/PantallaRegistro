import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.thoughtworks.xstream.XStream;

public class MainClass {
	public static void main(String[] args) {
		ejercicioTres();
		ejercicioCuatro();
		ejercicioCinco(); 
	}
	
	private static void ejercicioTres() {
		/*
		 Crea una clase java que almacene en un fichero binario secuencial
		‘asignaturas.dat’ 5 objetos Asignatura (Código asignatura, Nombre, professor,
		nº de horas). Los datos deben ser introducidos por teclado. 
		 */
		
		Asignatura as[]= {
				new Asignatura(201, "Acceso a Datos", "Jairo", 5),
				new Asignatura(202, "Desarrollo de interfaces", "Raquel", 6),
				new Asignatura(203, "Empresa", "Estela", 3),
				new Asignatura(204, "Inglés", "No me acuerdo", 2),
				new Asignatura(205, "Programación de servicios y procesos", "Susana", 4),
				new Asignatura(206, "Programación multimedia", "Jairo", 4),
				new Asignatura(207, "Sistemas de gestión empresarial", "Susana", 4)
		};
		
		File f = new File("./asignaturas.dat");
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);			
			
			for (int i = 0; i < as.length; ++i) {
				oos.writeObject(as[i]);
			}
			oos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
	}
	
	
	private static void ejercicioCuatro() {
		/*
		Crea una clase java que escriba el contenido de ‘asignaturas.dat’ en
		‘asignaturas.xml’ mediante DOM y lo muestre por pantalla mediante XStream
		*/	
		try {
			File f = new File("./asignaturas.dat");
			ObjectInputStream objectin = new ObjectInputStream(new FileInputStream(f));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Asignaturas", null);
			document.setXmlVersion("1.0");
	
			Asignatura a = new Asignatura();
			try {
				while (true) {
					a = (Asignatura) objectin.readObject();
					Element raiz = document.createElement("asignatura");
					/*System.out.println(a.getCodigo_asignatura() + " "
							+a.getNombre() + " "
							+a.getProfesor() + " "
							+a.getNum_horas() + " ");*/
					document.getDocumentElement().appendChild(raiz);
					addElemento("codigo", Integer.toString(a.getCodigo_asignatura()), raiz, document);
					addElemento("nombre", a.getNombre(), raiz, document);
					addElemento("profesor", a.getProfesor(), raiz, document);				
					addElemento("num_horas", Integer.toString(a.getNum_horas()), raiz, document);
					
				}
			}catch(EOFException eof) {}
			objectin.close();
			Source source = new DOMSource(document);
			Result res = new StreamResult(new java.io.File("Asignaturas.xml"));
			Transformer optimus = TransformerFactory.newInstance().newTransformer();
			optimus.transform(source, res);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void ejercicioCinco() {
		/*
		 Crea una clase java que escriba el contenido de ‘asignaturas.dat’ en
		 ‘asignaturas.xml’ mediante XStream y lo muestre por pantalla mediante SAX.
		 */
		
		XStream xstream =  new XStream();
		File f = new File("./asignaturas.dat");
		ListaAsignaturas listaas = new ListaAsignaturas();
		try {
			ObjectInputStream objectin = new ObjectInputStream(new FileInputStream(f));
			try {
				while(true) {
					Asignatura as = (Asignatura)objectin.readObject();
					listaas.add(as);
				}
			}catch(EOFException eof) {} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private static void addElemento(String nombre, String data, Element raiz, Document document) {
		Element elem  = document.createElement(nombre);
		Text text = document.createTextNode(data);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
	
	
}
