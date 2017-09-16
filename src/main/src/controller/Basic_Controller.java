package controller;

import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Basic_Service;
import utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Basic_Controller<T> {

	private Basic_Service<T> getService() {
		try {
			Field f = getClass().getDeclaredField("service");
			f.setAccessible(true);
			return (Basic_Service<T>) f.get(this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("getStatus")
	public @ResponseBody
	StatusUtils getStatus(){
		return StatusUtils.getSingle();
	}


	@SuppressWarnings("rawtypes")
	protected String getTypeName() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class cls = (Class) type.getActualTypeArguments()[0];
		String tableName = cls.getSimpleName();
		return "table/" + StringUtils.getLittleName(tableName);
	}

	@RequestMapping("select")
	public @ResponseBody ListAndSearchInfo select(SeachInfo sea) {
		sea.setRowcount(getService().getSize(sea));
		if(sea.getPageno()>sea.getPagecount())
			sea.setPageno(sea.getPagecount());
		List<T> list = getService().getAll(sea);
		System.out.println(JSONObject.fromObject(sea).toString());
		return new ListAndSearchInfo(sea,list);
	}

	@RequestMapping("del")
	public @ResponseBody ListAndSearchInfo delete(int id, ModelMap m) {
		getService().delete(id);
		return select(new SeachInfo());
	}

	@RequestMapping("ad")
	public String ad(ModelMap m) {
		m.put("cmd", "add");
		return getTypeName() + "/add";
	}

	@RequestMapping("updat")
	public String updat(int id, ModelMap m) {
		m.put("object", getService().getById(id));
		m.put("cmd", "update");
		return getTypeName() + "/add";
	}

	@RequestMapping("edit")
	public @ResponseBody ListAndSearchInfo edit(String cmd, T s, ModelMap m,HttpSession session) {
		if (cmd.equals("update")){
			getService().update(s);
		}
		else
			getService().insert(s);
		return select(new SeachInfo());
	}

	@RequestMapping("change")
	public @ResponseBody
	String change(HttpServletRequest req) {
		String ds = req.getParameter("ds");
		ObjectMapper o = new ObjectMapper();
		JsonData1[] j = null;
		try {
			j = o.readValue(ds, JsonData1[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (JsonData1 jsonData1 : j) {
			getService().updateAttr(jsonData1);
		}
		return "{\"status\":1}";
	}

	@RequestMapping("ajax")
	public @ResponseBody
	String ajax(String cmd, T t) {
		if (cmd.equals("update")) {
			getService().update(t);
		} else {
			getService().insert(t);
		}
		return "{\"status\":1}";
	}

	@RequestMapping(value = "selectByAll")
	public @ResponseBody
	ListAndSearchInfo<T> selectByAll(SeachInfo searchInfo, int[] trem, int[] compare, String[] text, int[] join){
		if(text==null||text.length==0){
			return select(searchInfo);
		}
		if(searchInfo.getWhere()==null || searchInfo.getWhere().length()==0) {
			return getService().selectByAll(searchInfo,trem,compare,text,join);
		}
		return select(searchInfo);
	}

}
