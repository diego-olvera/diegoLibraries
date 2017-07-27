package diegoLibraries.repository.springJdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import diegoLibraries.repository.DAOManagerInterface;
import diegoLibraries.repository.IdElement;
import diegoLibraries.repository.ManagerInterface;

public class Manager<Data extends IdElement<Key>,Key,DAOType extends DAOManagerInterface<Data, Key>> implements ManagerInterface<Data, Key, DAOType>{
	protected DAOManagerInterface<Data, Key> dao;

	
	public Manager(DAOManagerInterface<Data, Key> dao) {
		setDao(dao);
	}

	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#getDao()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DAOType getDao() {
		return (DAOType) dao;
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#setDao(diegoLibraries.repository.DAOManagerInterface)
	 */
	@Override
	public void setDao(DAOManagerInterface<Data, Key> dao) {
		this.dao = dao;
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#getAll()
	 */
	@Override
	public List<Data> getAll(){
		return dao.getAll();
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#create(java.util.ArrayList)
	 */
	@Override
	public ArrayList<Key> create(ArrayList<Data> elements) throws DataAccessException{
		return dao.create(elements);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<Data> elements)throws DataAccessException {
		dao.update(elements);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#deleteElements(java.util.ArrayList)
	 */
	@Override
	public void deleteElements(ArrayList<Data> elements) throws DataAccessException {
		dao.deleteElements(elements);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#delete(java.util.ArrayList)
	 */
	@Override
	public void delete(ArrayList<Key> elements) throws DataAccessException{
		dao.delete(elements);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#create(Data)
	 */
	@Override
	public Key create(Data data) throws DataAccessException{
		return dao.create(data);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#update(Data)
	 */
	@Override
	public void update(Data ride) throws DataAccessException{
		dao.update(ride);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#deleteElement(Data)
	 */
	@Override
	public boolean deleteElement(Data d) {
		return dao.deleteElement(d);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#delete(Key)
	 */
	@Override
	public boolean delete(Key id) {
		return dao.delete(id);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#getById(Key)
	 */
	@Override
	public Data getById(Key id) {
		return dao.getById(id);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#disable(Key)
	 */
	@Override
	public boolean disable(Key id) {
		return dao.disable(id);
	}
	/* (non-Javadoc)
	 * @see diegoLibraries.repository.springJdbc.ManagerInterface#enable(Key)
	 */
	@Override
	public boolean enable(Key id) {
		return dao.enable(id);
	}

}
