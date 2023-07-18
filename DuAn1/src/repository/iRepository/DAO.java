package repository.iRepository;

import java.util.List;

public interface DAO <Key,Value>{
		public List<Value> selectAll(int... page);
		public Value getById(Key id);
		public boolean insert(Value object);
		public boolean update(Value object);
		public boolean delete(Key id);
		public List<Value> getBySql (String sql, Object...args);
}
