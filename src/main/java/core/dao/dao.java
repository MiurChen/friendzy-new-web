package core.dao;

import java.util.List;

public interface dao<T> {

	int insert(T item) throws Exception;

	int update(T item) throws Exception;

	int deleteBy(String id) throws Exception;

	T seleteBy(String id) throws Exception;

	List<T> seleteAll() throws Exception;


	

}
