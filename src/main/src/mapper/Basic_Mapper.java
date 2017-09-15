package mapper;

import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


public interface Basic_Mapper<T> {
	
	public List<T> getAll(SeachInfo sea);
	
	public List<T> getByAttr(JsonData1 j);
	
	public T getById(int id);
	
	public void insert(T t);
	
	public void delete(int id);
	
	public void update(T t);
	
	public long getSize(SeachInfo sea);
	
	public void updateAttr(JsonData1 j);
	
	public List<T> getByMathod_in(String ids);

	public T getNew();

}
