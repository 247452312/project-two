package entity;

public class Shop implements Comparable<Shop>  {

	private Integer id;
	private String fexp;
	private String tel;
	private String name;
	private Integer userid;
	private String username;
	private String createdate;
	private String addr;
	private String telmov;
	private String code;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFexp() {
		return fexp;
	}

	public void setFexp(String fexp) {
		this.fexp = fexp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTelmov() {
		return telmov;
	}

	public void setTelmov(String telmov) {
		this.telmov = telmov;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Shop(){
	}



	public Shop(Integer id) {
		this.id = id;
	}

	public Shop(String fexp, String tel, String name, Integer userid, String username, String createdate, String addr, String telmov, String code) {
		this.fexp = fexp;
		this.tel = tel;
		this.name = name;
		this.userid = userid;
		this.username = username;
		this.createdate = createdate;
		this.addr = addr;
		this.telmov = telmov;
		this.code = code;
	}

	public Shop(Integer id, String fexp, String tel, String name, Integer userid, String username, String createdate, String addr, String telmov, String code) {
		this.id = id;
		this.fexp = fexp;
		this.tel = tel;
		this.name = name;
		this.userid = userid;
		this.username = username;
		this.createdate = createdate;
		this.addr = addr;
		this.telmov = telmov;
		this.code = code;
	}

	@Override
	public int compareTo(Shop o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
