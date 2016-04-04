package ar.com.ice.brasil.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import ar.com.ice.brasil.model.beans.Reunion;

public class ReunionDAO {

	private static final String SELECT_REUNION_BY_ID = "SELECT * from reunion where id =?";

	private static final String INSERT_NEW_REUNION = "INSERT INTO reunion (nombre) (?)";
	private static final String UPDATE_REUNION = "UPDATE reunion SET (nombre=?) WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Reunion getReunionById(int id) {
		Reunion reunion = null;

		try {
			reunion = jdbcTemplate.queryForObject(SELECT_REUNION_BY_ID,
					ParameterizedBeanPropertyRowMapper
							.newInstance(Reunion.class), id);
		} catch (Exception e) {
			System.out.println("Error");
		}

		return reunion;
	}

	public boolean commit(Reunion reunion) {

		int result = 0;

		if (reunion.getId() == 0) {

			try {
				result = jdbcTemplate.update(INSERT_NEW_REUNION,
						reunion.toArray());
			} catch (DataAccessException e) {
				return false;
			}
		} else {
			try {
				result = jdbcTemplate.update(UPDATE_REUNION, reunion.toArray());
			} catch (DataAccessException e) {
				return false;
			}
		}

		return (result == 0x01);
	}

	public List<Reunion> getReunionByCriteria(String nombre) {
		String query = "SELECT * FROM reunion where 1=1";
		List<Reunion> reuniones = null;

		if (nombre != null && !nombre.isEmpty()) {
			query += " AND nombre = '" + nombre + "'";
		}

		try {
			reuniones = jdbcTemplate.queryForList(query, Reunion.class);
		} catch (Exception e) {
		}

		if (reuniones.isEmpty()) {
			return null;
		}

		return reuniones;
	}

}
