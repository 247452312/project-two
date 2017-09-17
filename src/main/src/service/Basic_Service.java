package service;

import utils.JsonData1;
import utils.ListAndSearchInfo;
import utils.SeachInfo;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface Basic_Service<T> {

	public List<T> getAll(SeachInfo sea);
	
	public List<T> getByAttr(JsonData1 j);
	
	public T getById(int id);
	
	public void insert(T t);
	
	public void delete(int id);
	
	public void update(T t);
	
	public long getSize(SeachInfo sea);
	
	public void updateAttr(JsonData1 j);

	public ListAndSearchInfo selectByAll(SeachInfo searchInfo, int[] trem, int[] compare, String[] text, int[] join);

	public T getNew();
}
