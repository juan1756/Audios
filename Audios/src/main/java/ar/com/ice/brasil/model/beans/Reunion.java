package ar.com.ice.brasil.model.beans;

import ar.com.ice.brasil.model.beans.interfaces.SpringJDBCBean;

public class Reunion implements SpringJDBCBean{
	
	private int id;
	
	private String nombre;

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

	@Override
	public Object[] toArray() {
		return new Object[]{nombre, id};
	}

}
