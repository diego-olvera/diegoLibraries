package diegoLibraries.test.repository.springJdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import diegoLibraries.repository.springJdbc.DAOManager;
import diegoLibraries.repository.springJdbc.SpringJdbcConnector;


public class TicketDAO extends DAOManager<Ticket,Long>{

	public TicketDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new TicketRowMapper(),"ticket");
	}
	public Long create(Ticket ticket) throws DataAccessException{
		Number key;
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {		
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(
						"insert into ticket (price,type) values (?,?)"
						,new String[]{"id"});
				ps.setFloat(1,ticket.getPrice());
				ps.setInt(2,ticket.getType());
				return ps;
			}
		}, keyHolder);
		key=keyHolder.getKey();
		return key.longValue();
		//return getById(key.intValue());	
	}
	public void update(Ticket ticket) throws DataAccessException{
		jdbcTemplate.update("update ride set price=?,type=? where id=?",
				ticket.getPrice(),ticket.getType(),ticket.getId());
		//return ticket;
	}	
	public static void main(String[] args) {
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ride_tracker?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="ride_tracker_user";
		String pwd="ZAPDLNSMrMh6wEpK";
		JdbcTemplate jdbc=SpringJdbcConnector.getConnection(driver, url, user, pwd);
		TicketDAO ticketDao=new TicketDAO(jdbc);
		for(Ticket t:ticketDao.getAll()) {
			System.out.println(t);
		}
	}
}
