package diegoLibraries.repository.springJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import diegoLibraries.repository.DoubleLongKey;

public class DAOTwoLongKeyManager extends DAOManager<DoubleLongKey,Object[]>{
	protected JdbcTemplate jdbcTemplate; 
	protected RowMapper<DoubleLongKey> rowMapper=new RowMapper<DoubleLongKey>() {

		@Override
		public DoubleLongKey mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new DoubleLongKey(rs.getLong(getFirstKeyName()),rs.getLong(getSecondKeyName()));
		}
	};
		
	private String firstKeyName;
	private String secondKeyName;
	public DAOTwoLongKeyManager() {}
	public DAOTwoLongKeyManager(JdbcTemplate jdbcTemplate,
			String dataBaseName,String firstKeyName,String secondKeyName) {
		setJdbcTemplate(jdbcTemplate);
		setDataBaseName(dataBaseName);
		setFirstKeyName(firstKeyName);
		setSecondKeyName(secondKeyName);
	}	

	public String getFirstKeyName() {
		return firstKeyName;
	}
	public void setFirstKeyName(String firstKeyName) {
		this.firstKeyName = firstKeyName;
	}
	public String getSecondKeyName() {
		return secondKeyName;
	}
	public void setSecondKeyName(String secondKeyName) {
		this.secondKeyName = secondKeyName;
	}
	protected PreparedStatement getUpdateKey2Ps(){
		try {
			return jdbcTemplate.getDataSource().getConnection().prepareStatement(
					"UPDATE `"+getDataBaseName()
					+"` set "+getSecondKeyName()+"=? WHERE "+getFirstKeyName()+"=?");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected PreparedStatement getUpdateKey1Ps(){
		try {
			return jdbcTemplate.getDataSource().getConnection().prepareStatement(
					"UPDATE `"+getDataBaseName()
					+"` set "+getFirstKeyName()+"=? WHERE "+getSecondKeyName()+"=?");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected PreparedStatement getDeletePs(){
		try {
			return jdbcTemplate.getDataSource().getConnection().prepareStatement("delete from "+getDataBaseName()
					+" where "+getFirstKeyName()+"=? and "+getSecondKeyName()+"=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected PreparedStatement getFindByKey1Ps(){
		try {
			return jdbcTemplate.getDataSource().getConnection().prepareStatement(
					"SELECT * FROM "+getDataBaseName()+" WHERE "+getFirstKeyName()+"=?;"
					);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected PreparedStatement getFindByKey2Ps(){
		try {
			return jdbcTemplate.getDataSource().getConnection().prepareStatement(
					"SELECT * FROM "+getDataBaseName()+" WHERE "+getSecondKeyName()+"=?;"
					);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Object[] create(DoubleLongKey data) throws DataAccessException {
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {		
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement("insert into "+getDataBaseName()+" (name,duration) values (?,?)");
				ps.setLong(1,data.getFirstKey());
				ps.setLong(2,data.getSecondKey());
				return ps;
			}
		}, keyHolder);
		return data.keys;
	}
	public boolean updateKey2(DoubleLongKey id) throws DataAccessException {
		PreparedStatement ps=getUpdateKey2Ps();
		try {
			ps.setLong(2,id.getFirstKey());
			ps.setLong(1,id.getSecondKey());
			return ps.executeUpdate()>=1;
		} catch (SQLException e) {
			throw new diegoLibraries.repository.DataAccessException(e.getMessage());
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean updateKey1(DoubleLongKey id) throws DataAccessException {
		PreparedStatement ps=getUpdateKey1Ps();
		try {
			ps.setLong(1,id.getFirstKey());
			ps.setLong(2,id.getSecondKey());
			return ps.executeUpdate()>=1;
		} catch (SQLException e) {
			throw new diegoLibraries.repository.DataAccessException(e.getMessage());
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean delete(DoubleLongKey id) throws DataAccessException {
		PreparedStatement ps=getDeletePs();
		try {
			ps.setLong(1,id.getFirstKey());
			ps.setLong(2,id.getSecondKey());
			return ps.executeUpdate()>=1;
		} catch (SQLException e) {
			throw new diegoLibraries.repository.DataAccessException(e.getMessage());
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<DoubleLongKey> getElements(ResultSet result){
		return getElements(new ArrayList<DoubleLongKey>(),result);
	}
	public ArrayList<DoubleLongKey> getElements(ArrayList<DoubleLongKey> elementos,
			ResultSet result){
		try {
			while(result.next()){
				elementos.add(rowMapper.mapRow(result,0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return elementos;
	}
	public ArrayList<DoubleLongKey> getByKey1(long id){
		PreparedStatement ps=getFindByKey1Ps();
		try {			
			ps.setLong(1, id);
			return getElements(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<DoubleLongKey> getByKey2(long id){
		PreparedStatement ps=getFindByKey2Ps();
		try {			
			ps.setLong(1, id);
			return getElements(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(ArrayList<DoubleLongKey> newKeys,int id,boolean updateKey1)
			throws DataAccessException{
		for(DoubleLongKey key:updateKey1?getByKey2(id):getByKey1(id)){
			delete(key);
		}
		for(DoubleLongKey newKey:newKeys){
			create(newKey);
		}
	}
	@Override
	public void update(DoubleLongKey ride) throws DataAccessException {
		throw new diegoLibraries.repository.DataAccessException("Not implemented");	
	}}
