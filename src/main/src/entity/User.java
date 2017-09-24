package entity;

import utils.StatusUtils;

public class User implements Comparable<User>  {

	private Integer sex;
	private String ccode;
	//0->操作员 1->职员 2->离职 3->临时工
	private Integer status;
	private String tel;
	//创建人
	private User userid;
	//创建日期
	private String createdate;
	private String addr;
	private String code;
	//默认123
	private String pass;
	//id
	private Integer id;
	private String fexp;
	private String enterdate;//就职时间
	private Shop shopid;
	private String exitdate;
	private String name;
	private String birthdate;
	private String telmov;
	private String fright;

	private String statusString;
	private String sexString;

	public String getSexString() {
		return sexString;
	}

	public String getStatusString() {
		return statusString;
	}


	public User(){
	}

	public User(Integer sex, String ccode, Integer status, String tel, User userid, String createdate, String addr, String code, String pass, Integer id, String fexp, String enterdate, Shop shopid, String exitdate, String name, String birthdate, String telmov, String fright){

		this.sex=sex;
		this.ccode=ccode;
		this.status=status;
		this.tel=tel;
		this.userid=userid;
		this.createdate=createdate;
		this.addr=addr;
		this.code=code;
		this.pass=pass;
		this.id=id;
		this.fexp=fexp;
		this.enterdate=enterdate;
		this.shopid=shopid;
		this.exitdate=exitdate;
		this.name=name;
		this.birthdate=birthdate;
		this.telmov=telmov;
		this.fright=fright;
	}

	public User(Integer sex, String ccode, Integer status, String tel, User userid, String createdate, String addr, String code, String pass, String fexp, String enterdate, Shop shopid, String exitdate, String name, String birthdate, String telmov, String fright){

		this.sex=sex;
		this.ccode=ccode;
		this.status=status;
		this.tel=tel;
		this.userid=userid;
		this.createdate=createdate;
		this.addr=addr;
		this.code=code;
		this.pass=pass;
		this.fexp=fexp;
		this.enterdate=enterdate;
		this.shopid=shopid;
		this.exitdate=exitdate;
		this.name=name;
		this.birthdate=birthdate;
		this.telmov=telmov;
		this.fright=fright;
	}

	public User(int id){
		this.id=id;
	}



	public Integer getSex(){
		return sex;
	}
	public String getCcode(){
		return ccode;
	}
	public Integer getStatus(){
		return status;
	}
	public String getTel(){
		return tel;
	}
	public User getUserid(){
		return userid;
	}
	public String getCreatedate(){
		return createdate;
	}
	public String getAddr(){
		return addr;
	}
	public String getCode(){
		return code;
	}
	public String getPass(){
		return pass;
	}
	public Integer getId(){
		return id;
	}
	public String getFexp(){
		return fexp;
	}
	public String getEnterdate(){
		return enterdate;
	}
	public Shop getShopid(){
		return shopid;
	}
	public String getExitdate(){
		return exitdate;
	}
	public String getName(){
		return name;
	}
	public String getBirthdate(){
		return birthdate;
	}
	public String getTelmov(){
		return telmov;
	}
	public String getFright(){
		return fright;
	}
	public void setSex(Integer sex){
		this.sex=sex;
		this.sexString= StatusUtils.sex[sex];
	}
	public void setCcode(String ccode){
		this.ccode=ccode;
	}
	public void setStatus(Integer status){
		this.status=status;
		this.statusString=StatusUtils.UserStatus[status];
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public void setUserid(User userid){
		this.userid=userid;
	}
	public void setCreatedate(String createdate){
		this.createdate=createdate;
	}
	public void setAddr(String addr){
		this.addr=addr;
	}
	public void setCode(String code){
		this.code=code;
	}
	public void setPass(String pass){
		this.pass=pass;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setEnterdate(String enterdate){
		this.enterdate=enterdate;
	}
	public void setShopid(Shop shopid){
		this.shopid=shopid;
	}
	public void setExitdate(String exitdate){
		this.exitdate=exitdate;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setBirthdate(String birthdate){
		this.birthdate=birthdate;
	}
	public void setTelmov(String telmov){
		this.telmov=telmov;
	}
	public void setFright(String fright){
		this.fright=fright;
	}


	@Override
	public int compareTo(User o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
