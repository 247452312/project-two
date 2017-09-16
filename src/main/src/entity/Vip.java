package entity;

import utils.StatusUtils;

public class Vip implements Comparable<Vip>  {

	private Integer sex;
	private String ccode;
	//0-禁用,1->启用
	private Integer status;
	private String tel;
	//录入人姓名
	private User userid;
	//创建日期
	private String createdate;
	private String addr;
	private String code;
	private String jsr;
	private Integer id;
	private Double amount;
	private String fexp;
	private Double point;
	private Shop shopid;
	private String name;
	private String birthdate;
	private Viptype viptypeid;
	private String telmov;

	private String sexString;
	private String statusString;

	public String getSexString() {
		return sexString;
	}

	public String getStatusString() {
		return statusString;
	}

	public Vip(){
	}

	public Vip(Integer sex, String ccode, Integer status, String tel, User userid, String createdate, String addr, String code, String jsr, Integer id, Double amount, String fexp, Double point, Shop shopid, String name, String birthdate, Viptype viptypeid, String telmov){

		this.sex=sex;
		this.ccode=ccode;
		this.status=status;
		this.tel=tel;
		this.userid=userid;
		this.createdate=createdate;
		this.addr=addr;
		this.code=code;
		this.jsr=jsr;
		this.id=id;
		this.amount=amount;
		this.fexp=fexp;
		this.point=point;
		this.shopid=shopid;
		this.name=name;
		this.birthdate=birthdate;
		this.viptypeid=viptypeid;
		this.telmov=telmov;
	}

	public Vip(Integer sex, String ccode, Integer status, String tel, User userid, String createdate, String addr, String code, String jsr, Double amount, String fexp, Double point, Shop shopid, String name, String birthdate, Viptype viptypeid, String telmov){

		this.sex=sex;
		this.ccode=ccode;
		this.status=status;
		this.tel=tel;
		this.userid=userid;
		this.createdate=createdate;
		this.addr=addr;
		this.code=code;
		this.jsr=jsr;
		this.amount=amount;
		this.fexp=fexp;
		this.point=point;
		this.shopid=shopid;
		this.name=name;
		this.birthdate=birthdate;
		this.viptypeid=viptypeid;
		this.telmov=telmov;
	}

	public Vip(int id){
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
	public String getJsr(){
		return jsr;
	}
	public Integer getId(){
		return id;
	}
	public Double getAmount(){
		return amount;
	}
	public String getFexp(){
		return fexp;
	}
	public Double getPoint(){
		return point;
	}
	public Shop getShopid(){
		return shopid;
	}
	public String getName(){
		return name;
	}
	public String getBirthdate(){
		return birthdate;
	}
	public Viptype getViptypeid(){
		return viptypeid;
	}
	public String getTelmov(){
		return telmov;
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
		this.statusString= StatusUtils.VIPStatus[status];
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
	public void setJsr(String jsr){
		this.jsr=jsr;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setAmount(Double amount){
		this.amount=amount;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setPoint(Double point){
		this.point=point;
	}
	public void setShopid(Shop shopid){
		this.shopid=shopid;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setBirthdate(String birthdate){
		this.birthdate=birthdate;
	}
	public void setViptypeid(Viptype viptypeid){
		this.viptypeid=viptypeid;
	}
	public void setTelmov(String telmov){
		this.telmov=telmov;
	}

	public int compareTo(Vip o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
