package interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotAll;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import exceptions.NotFound;
import exceptions.NotUpdated;

public interface ICrud<T>{
	void create(T t) throws NotCreated;
	T read(int id) throws NotFound;
	void update(T t) throws NotUpdated;
	void delete(T t) throws NotDeleted;
	List<T> all() throws NotAll, SQLException;
	List<T> all(int id) throws NotAll;
}
