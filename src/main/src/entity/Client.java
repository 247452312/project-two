package entity;

public class Client implements Comparable<Client>  {

	private Integer id;
	private String fexp;
	private String lxr;
	private String tel;
	private String name;
	private User userid;
	//创建日期
	private String createdate;
	private String bank;
	private String addr;
	private String telmov;
	private String code;


	public Client(){
	}

	public Client(Integer id,String fexp,String lxr,String tel,String name,User userid,String createdate,String bank,String addr,String telmov,String code){

		this.id=id;
		this.fexp=fexp;
		this.lxr=lxr;
		this.tel=tel;
		this.name=name;
		this.userid=userid;
		this.createdate=createdate;
		this.bank=bank;
		this.addr=addr;
		this.telmov=telmov;
		this.code=code;
	}

	public Client(String fexp,String lxr,String tel,String name,User userid,String createdate,String bank,String addr,String telmov,String code){

		this.fexp=fexp;
		this.lxr=lxr;
		this.tel=tel;
		this.name=name;
		this.userid=userid;
		this.createdate=createdate;
		this.bank=bank;
		this.addr=addr;
		this.telmov=telmov;
		this.code=code;
	}

	public Client(int id){
		this.id=id;
	}



	public Integer getId(){
		return id;
	}
	public String getFexp(){
		return fexp;
	}
	public String getLxr(){
		return lxr;
	}
	public String getTel(){
		return tel;
	}
	public String getName(){
		return name;
	}
	public User getUserid(){
		return userid;
	}
	public String getCreatedate(){
		return createdate;
	}
	public String getBank(){
		return bank;
	}
	public String getAddr(){
		return addr;
	}
	public String getTelmov(){
		return telmov;
	}
	public String getCode(){
		return code;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setLxr(String lxr){
		this.lxr=lxr;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setUserid(User userid){
		this.userid=userid;
	}
	public void setCreatedate(String createdate){
		this.createdate=createdate;
	}
	public void setBank(String bank){
		this.bank=bank;
	}
	public void setAddr(String addr){
		this.addr=addr;
	}
	public void setTelmov(String telmov){
		this.telmov=telmov;
	}
	public void setCode(String code){
		this.code=code;
	}

	@Override
	public int compareTo(Client o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
