package dao;

import java.util.List;

public interface IDAO<T> {

	boolean add(T object);

	T get(long id);

	boolean delete(long id);

	boolean update(T object);

	List<T> getList();
}
