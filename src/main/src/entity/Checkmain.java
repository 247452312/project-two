package entity;

public class Checkmain implements Comparable<Checkmain> {

	private Integer id;
	private String fexp;
	private Shop shopid;
	private String checkcode;
	private String checkdate;
	private Integer status;
	private String checkname;


	public Checkmain(){
	}

	public Checkmain(Integer id,String fexp,Shop shopid,String checkcode,String checkdate,Integer status,String checkname){

		this.id=id;
		this.fexp=fexp;
		this.shopid=shopid;
		this.checkcode=checkcode;
		this.checkdate=checkdate;
		this.status=status;
		this.checkname=checkname;
	}

	public Checkmain(String fexp,Shop shopid,String checkcode,String checkdate,Integer status,String checkname){

		this.fexp=fexp;
		this.shopid=shopid;
		this.checkcode=checkcode;
		this.checkdate=checkdate;
		this.status=status;
		this.checkname=checkname;
	}

	public Checkmain(int id){
		this.id=id;
	}



	public Integer getId(){
		return id;
	}
	public String getFexp(){
		return fexp;
	}
	public Shop getShopid(){
		return shopid;
	}
	public String getCheckcode(){
		return checkcode;
	}
	public String getCheckdate(){
		return checkdate;
	}
	public Integer getStatus(){
		return status;
	}
	public String getCheckname(){
		return checkname;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setShopid(Shop shopid){
		this.shopid=shopid;
	}
	public void setCheckcode(String checkcode){
		this.checkcode=checkcode;
	}
	public void setCheckdate(String checkdate){
		this.checkdate=checkdate;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public void setCheckname(String checkname){
		this.checkname=checkname;
	}

	@Override
	public int compareTo(Checkmain o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
