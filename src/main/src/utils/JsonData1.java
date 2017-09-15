package utils;
/** 
 * 类说明 
 * @author 龙龙 E-mail: 247452312@qq.com
 * @version 创建时间：2017-8-26 
 */
public class JsonData1 {

	private String attrName;
	private Integer id;
	private Object o;
	public JsonData1() {
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
	public JsonData1(String attrName, Integer id, Object o) {
		super();
		this.attrName = attrName;
		this.id = id;
		this.o = o;
	}
	public JsonData1(String attrName, Object o) {
		super();
		this.attrName = attrName;
		this.o = o;
	}
	
	
	
	
}
