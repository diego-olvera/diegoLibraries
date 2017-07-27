package diegoLibraries.test.repository.springJdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TicketRowMapper implements RowMapper<Ticket> {
	@Override
	public Ticket mapRow(ResultSet rs, int row) throws SQLException {
		return new Ticket(rs.getInt("id"),rs.getFloat("price"),rs.getInt("type"));
	}	
}
