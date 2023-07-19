package repositorys.iRepository;

import java.util.List;

public interface DAO <Key,Value>{
		public List<Value> getAll(int... page);
		//Lấy danh sách đối tượng có truyền tham số hoặc không
		public Value getById(Key id);
		//Lấy đối tượng theo thuộc tính khóa ví dụ "id"
		public boolean insert(Value object);
		//Thêm một đối tượng lên db
		public boolean update(Value object);
		//Sửa một đối tượng
		public boolean deleteById(Key id);
		//Xóa một đối tượng trên db theo thuộc tính khóa
		public List<Value> getBySql (String sql, Object...args);
		//Lấy một đối tượng theo câu lệnh sql truyền vào và danh sách tham số tùy chọn
}
