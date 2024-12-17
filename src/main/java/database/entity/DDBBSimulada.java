package database.entity;

import java.util.ArrayList;
import java.util.List;

public class DDBBSimulada {
	private List<Alumno> alumnos = null;

	private static DDBBSimulada instance = null;
	
	public static DDBBSimulada getintstance(){
		if(instance == null) {
			instance = new DDBBSimulada();
		}
		return instance;
		
	}
	
	public DDBBSimulada() {
		super();
		alumnos = new ArrayList<Alumno>();
		
		Alumno alumno = new Alumno(0,"patata","papa",23124131);
		alumnos.add(alumno);
	}

	public Alumno buscarAlumno(String nombre,String apellido) {
		Alumno ret = null;

		for (Alumno alumno : alumnos) {
			if (alumno.getNombre().equalsIgnoreCase(nombre) && alumno.getApellido().equalsIgnoreCase(apellido)) {
				alumno = ret;
			}
		}
		return ret;
	}

	public void insertarAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}
}
