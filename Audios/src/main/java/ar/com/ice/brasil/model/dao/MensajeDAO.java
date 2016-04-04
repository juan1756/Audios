package ar.com.ice.brasil.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import ar.com.ice.brasil.model.beans.Mensaje;

public class MensajeDAO {

	private static final String SELECT_MENSAJE_BY_ID = "SELECT * from mensaje where id =?";

	private static final String INSERT_NEW_MENSAJE = "INSERT INTO mensaje (titulo, orador, tema, idReunion, lugar) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE_MENSAJE = "UPDATE mensaje SET (titulo=?, orador=?, tema=?, idReunion=?, lugar=?) WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Mensaje getMensajeById(int id) {
		Mensaje mensaje = null;

		mensaje = jdbcTemplate.queryForObject(SELECT_MENSAJE_BY_ID, ParameterizedBeanPropertyRowMapper.newInstance(Mensaje.class), id);

		return mensaje;
	}

	public void commit(Mensaje mensaje) throws DataAccessException {

		if (mensaje.getId() == null) {
			jdbcTemplate.update(INSERT_NEW_MENSAJE,
					new Object[] { mensaje.getTitulo(), mensaje.getOrador(), mensaje.getTema(), mensaje.getIdReunion(), mensaje.getLugar() });
		} else {
			jdbcTemplate.update(UPDATE_MENSAJE, mensaje.toArray());
		}
	}

	public List<Mensaje> getMensajeByCriteria(String titulo, String orador, String tema, Integer idReunion, String lugar) {
		String query = "SELECT * FROM mensaje where 1=1";
		List<Mensaje> mensajes = null;

		if (titulo != null && !titulo.isEmpty()) {
			query += " AND titulo = '" + titulo + "'";
		}
		if (orador != null && !orador.isEmpty()) {
			query += " AND orador = '" + orador + "'";
		}
		if (tema != null && !tema.isEmpty()) {
			query += " AND tema = '" + tema + "'";
		}
		if (idReunion != null) {
			query += " AND idReunion = " + idReunion;
		}
		if (lugar != null && !lugar.isEmpty()) {
			query += " AND lugar = '" + lugar + "'";
		}

		try {
			mensajes = jdbcTemplate.query(query, ParameterizedBeanPropertyRowMapper.newInstance(Mensaje.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (mensajes == null || mensajes.isEmpty()) {
			return null;
		}

		return mensajes;
	}
}
