package diegoLibraries.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DAOManagerInterface<Data extends IdElement<Key>, Key> {

	String getDataBaseName();

	void setDataBaseName(String dataBaseName);

	List<Data> getAll();

	ArrayList<Key> create(ArrayList<Data> elements) throws DataAccessException;

	void update(ArrayList<Data> elements) throws DataAccessException;

	void deleteElements(ArrayList<Data> elements) throws DataAccessException;

	void delete(ArrayList<Key> elements) throws DataAccessException;

	Key create(Data data) throws DataAccessException;

	void update(Data data) throws DataAccessException;

	boolean deleteElement(Data d);

	boolean delete(Key id);

	boolean disable(Key id);

	boolean enable(Key id);

	Data getById(Key id);

}