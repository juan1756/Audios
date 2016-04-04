package ar.com.ice.brasil.model.beans;

import ar.com.ice.brasil.model.beans.interfaces.SpringJDBCBean;

public class Pedido implements SpringJDBCBean {

	private Integer id;

	private String cliente;

	private boolean abonado;

	private float montoAbonado;

	private boolean entregado;

	private String descripcion;

	private boolean baja;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public boolean isAbonado() {
		return abonado;
	}

	public void setAbonado(boolean abonado) {
		this.abonado = abonado;
	}

	public void setAbonado(int abonado) {
		this.abonado = (abonado == 1);
	}

	public float getMontoAbonado() {
		return montoAbonado;
	}

	// TODO Cambiar
	public void setMontoAbonado(String montoAbonado) {
		this.montoAbonado = Float.parseFloat(montoAbonado);
	}

	public void setMontoAbonado(float montoAbonado) {
		this.montoAbonado = montoAbonado;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public void setEntregado(int entregado) {
		this.entregado = (entregado == 1);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	@Override
	public Object[] toArray() {
		return new Object[] { cliente, descripcion, abonado, montoAbonado, entregado, baja, id };
	}
}
