package diegoLibraries.repository.springJdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import diegoLibraries.repository.DAOManagerInterface;
import diegoLibraries.repository.IdElement;

public abstract class DAOManager<Data extends IdElement<Key>,Key> implements DAOManagerInterface<Data, Key> {
	protected JdbcTemplate jdbcTemplate; 
	protected RowMapper<Data> rowMapper;
	
	protected String dataBaseName;
	
	public DAOManager() {}
	public DAOManager(JdbcTemplate jdbcTemplate, RowMapper<Data> rowMapper,
			String dataBaseName) {
		setJdbcTemplate(jdbcTemplate);
		setRowMapper(rowMapper);
		setDataBaseName(dataBaseName);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RowMapper<Data> getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper<Data> rowMapper) {
		this.rowMapper = rowMapper;
	}

	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#getDataBaseName()
	 */
	@Override
	public String getDataBaseName() {
		return dataBaseName;
	}

	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#setDataBaseName(java.lang.String)
	 */
	@Override
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#getAll()
	 */
	@Override
	public List<Data> getAll() {
		return jdbcTemplate.query("select * from "+getDataBaseName(),rowMapper);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#create(java.util.ArrayList)
	 */
	@Override
	public ArrayList<Key> create(ArrayList<Data> elements) throws DataAccessException{
		ArrayList<Key> keys=new ArrayList<>();
		elements.forEach((e)->keys.add(create(e)));
		return keys; 
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<Data> elements) throws DataAccessException{
		elements.forEach((e)->update(e));
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#deleteElements(java.util.ArrayList)
	 */
	@Override
	public void deleteElements(ArrayList<Data> elements) throws DataAccessException{
		elements.forEach((e)->delete(e.getId()));
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#delete(java.util.ArrayList)
	 */
	@Override
	public void delete(ArrayList<Key> elements) throws DataAccessException{
		elements.forEach((e)->delete(e));
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#create(Data)
	 */
	@Override
	public abstract Key create(Data data) throws DataAccessException;
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#update(Data)
	 */
	@Override
	public abstract void update(Data ride) throws DataAccessException;
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#deleteElement(Data)
	 */
	@Override
	public boolean deleteElement(Data d) {
		return delete(d.getId());
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#delete(Key)
	 */
	@Override
	public boolean delete(Key id) {
		return jdbcTemplate.update("delete from "+getDataBaseName()+" where id=?",id)>=1;
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#disable(Key)
	 */
	@Override
	public boolean disable(Key id) {
		return jdbcTemplate.update("update  "+getDataBaseName()+" set enabled=0 where id=?",id)>=1;
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#enable(Key)
	 */
	@Override
	public boolean enable(Key id) {
		return jdbcTemplate.update("update  "+getDataBaseName()+" set enabled=1 where id=?",id)>=1;
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.DAOManagerInterface#getById(Key)
	 */
	@Override
	public Data getById(Key id) {
		try{
			return jdbcTemplate.queryForObject("select * from "+getDataBaseName()+" where id=?",
					rowMapper,id);
		}catch(DataAccessException e){
			return null;
		}
		//or	
		/*
		List<Data> elements=jdbcTemplate.query(new PreparedStatementCreator() {		
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement("select * from ride where id=?");
				ps.setInt(1,id);
				return ps;
			}
		}, rowMapper);
		return elements.size()>=1?elements.get(0):null;
		*/
	}
}
