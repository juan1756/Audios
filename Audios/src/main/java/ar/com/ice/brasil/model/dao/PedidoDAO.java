package ar.com.ice.brasil.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import ar.com.ice.brasil.model.beans.Pedido;

public class PedidoDAO {

	private static final String SELECT_BY_ID = "SELECT * from pedido where id =?";

	private static final String INSERT_NEW = "INSERT INTO pedido (cliente, descripcion, abonado, montoAbonado, entregado) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE pedido SET cliente=?, descripcion=?, abonado=?, montoAbonado=?, entregado=?, baja=? WHERE id=?";

	private static final String UPDATE_DELETE = "UPDATE pedido SET baja=1 WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Pedido getPedidoById(int id) {
		Pedido pedido = null;

		pedido = jdbcTemplate.queryForObject(SELECT_BY_ID, ParameterizedBeanPropertyRowMapper.newInstance(Pedido.class), id);

		return pedido;
	}

	public void commit(Pedido pedido) throws DataAccessException {

		if (pedido.getId() == null) {
			jdbcTemplate.update(INSERT_NEW,
					new Object[] { pedido.getCliente(), pedido.getDescripcion(), pedido.isAbonado(), pedido.getMontoAbonado(), pedido.isEntregado() });
		} else {
			jdbcTemplate.update(UPDATE, pedido.toArray());
		}
	}

	public void delete(int id) throws DataAccessException {
		jdbcTemplate.update(UPDATE_DELETE, new Object[] { id });
	}

	// TODO Agregar MontoAbonado en la criteria
	public List<Pedido> getPedidoByCriteria(String cliente, String descripcion, Boolean abonado, Boolean entregado) {
		String query = "SELECT * FROM pedido where 1=1";
		List<Pedido> pedidos = null;

		if (cliente != null && !cliente.isEmpty()) {
			query += " AND cliente = '" + cliente.trim() + "'";
		}
		if (descripcion != null && !descripcion.isEmpty()) {
			query += " AND descripcion = '" + descripcion.trim() + "'";
		}
		if (abonado != null) {
			if (abonado) {
				query += " AND abonado = 1";
			} else {
				query += " AND abonado = 0";
			}
		}
		if (entregado != null) {
			if (entregado) {
				query += " AND entregado = 1";
			} else {
				query += " AND entregado = 0";
			}
		}
		query += " AND baja = 0";

		try {
			pedidos = jdbcTemplate.query(query, ParameterizedBeanPropertyRowMapper.newInstance(Pedido.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (pedidos == null || pedidos.isEmpty()) {
			return null;
		}

		return pedidos;
	}
}
