package database.entity;

import java.io.Serializable;
import java.util.Objects;

/***
 * Entidad de BBDD
 */
public class Alumno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String nombre = null;
	private String apellido = null;
	private int numero = 0;
	
	public Alumno(int id, String nombre, String apellido, int numero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numero = numero;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido, id, nombre, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellido, other.apellido) && id == other.id && Objects.equals(nombre, other.nombre)
				&& numero == other.numero;
	}
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", numero=" + numero + "]";
	}
	
	
	

}
