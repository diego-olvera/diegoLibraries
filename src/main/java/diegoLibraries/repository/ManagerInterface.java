package diegoLibraries.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ManagerInterface<Data extends IdElement<Key>, Key, DAOType extends DAOManagerInterface<Data, Key>> {

	DAOType getDao();

	void setDao(DAOManagerInterface<Data, Key> dao);

	List<Data> getAll();

	ArrayList<Key> create(ArrayList<Data> elements) throws Exception;

	void update(ArrayList<Data> elements) throws DataAccessException;

	void deleteElements(ArrayList<Data> elements) throws DataAccessException;

	void delete(ArrayList<Key> elements) throws DataAccessException;

	Key create(Data data) throws DataAccessException;

	void update(Data ride) throws DataAccessException;

	boolean deleteElement(Data d);

	boolean delete(Key id);

	Data getById(Key id);

	boolean disable(Key id);

	boolean enable(Key id);

}