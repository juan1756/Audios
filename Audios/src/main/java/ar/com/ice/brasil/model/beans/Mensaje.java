package ar.com.ice.brasil.model.beans;

import java.util.Date;

import ar.com.ice.brasil.model.beans.interfaces.SpringJDBCBean;

public class Mensaje implements SpringJDBCBean {

	private Integer id;

	private String titulo;

	private String orador;

	private String tema;

	private int idReunion;

	private Date fecha;

	private String lugar;

	private String ruta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getOrador() {
		return orador;
	}

	public void setOrador(String orador) {
		this.orador = orador;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getIdReunion() {
		return idReunion;
	}

	public void setIdReunion(int idReunion) {
		this.idReunion = idReunion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public Object[] toArray() {
		return new Object[] { titulo, orador, tema, idReunion, lugar, id };
	}

}
