package ar.com.ice.brasil.model.beans.interfaces;

/**
 * Todo Bean que se usen en una transacción contra la base debe implementar esta
 * clase y respetar el orden de declaracion de atributos.
 */
public interface SpringJDBCBean {

	public Object[] toArray();
}
