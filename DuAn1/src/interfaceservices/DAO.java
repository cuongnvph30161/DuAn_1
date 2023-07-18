package interfaceservices;

import java.util.List;

public interface DAO<Key,Value> {
	List<Value> getAll();
	Value getByID(Key id);
	boolean insert(Value object);
	boolean update(Key id,Value object);
	boolean delete(Key id);
	List<Value> getByQuerry(String querry);
	
}
