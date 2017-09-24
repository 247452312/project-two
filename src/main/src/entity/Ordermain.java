package entity;

import utils.StatusUtils;

public class Ordermain implements Comparable<Ordermain>  {

	private Integer status=0;
	private String orderdate="";
	private String createdate="";
	private User userid=new User(0);
	private Integer ordertype=0;
	private Vip vipid=new Vip(0);
	private Shop destshopid=new Shop(0);
	private Integer id=0;
	private Double amount=0.0;
	private Double vipamount=0.0;
	private Client clientid=new Client(0);
	private String fexp="";
	private Double point=0.0;
	private Shop shopid=new Shop(0);
	private String ordercode="";
	private String statusString="";
	private String typeString="";

	public Ordermain(){
	}

	public Ordermain(Integer status, String orderdate, String createdate, User userid, Integer ordertype, Vip vipid, Shop destshopid, Integer id, Double amount, Double vipamount, Client clientid, String fexp, Double point, Shop shopid, String ordercode){

		this.status=status;
		this.orderdate=orderdate;
		this.createdate=createdate;
		this.userid=userid;
		this.ordertype=ordertype;
		this.vipid=vipid;
		this.destshopid=destshopid;
		this.id=id;
		this.amount=amount;
		this.vipamount=vipamount;
		this.clientid=clientid;
		this.fexp=fexp;
		this.point=point;
		this.shopid=shopid;
		this.ordercode=ordercode;
	}

	public Ordermain(Integer status, String orderdate, String createdate, User userid, Integer ordertype, Vip vipid, Shop destshopid, Double amount, Double vipamount, Client clientid, String fexp, Double point, Shop shopid, String ordercode){

		this.status=status;
		this.orderdate=orderdate;
		this.createdate=createdate;
		this.userid=userid;
		this.ordertype=ordertype;
		this.vipid=vipid;
		this.destshopid=destshopid;
		this.amount=amount;
		this.vipamount=vipamount;
		this.clientid=clientid;
		this.fexp=fexp;
		this.point=point;
		this.shopid=shopid;
		this.ordercode=ordercode;
	}

	public Ordermain(int id){
		this.id=id;
	}



	public Integer getStatus(){
		return status;
	}
	public String getOrderdate(){
		return orderdate;
	}
	public String getCreatedate(){
		return createdate;
	}
	public User getUserid(){
		return userid;
	}
	public Integer getOrdertype(){
		return ordertype;
	}
	public Vip getVipid(){
		return vipid;
	}
	public Shop getDestshopid(){
		return destshopid;
	}
	public Integer getId(){
		return id;
	}
	public Double getAmount(){
		return amount;
	}
	public Double getVipamount(){
		return vipamount;
	}
	public Client getClientid(){
		return clientid;
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
	public String getOrdercode(){
		return ordercode;
	}
	public void setStatus(Integer status){
		this.status=status;
		this.statusString= StatusUtils.OrdermainStatus[status];
	}
	public void setOrderdate(String orderdate){
		this.orderdate=orderdate;
	}
	public void setCreatedate(String createdate){
		this.createdate=createdate;
	}
	public void setUserid(User userid){
		this.userid=userid;
	}
	public void setOrdertype(Integer ordertype){
		this.ordertype=ordertype;
		this.typeString=StatusUtils.OrdermainType[ordertype];
	}
	public void setVipid(Vip vipid){
		this.vipid=vipid;
	}
	public void setDestshopid(Shop destshopid){
		this.destshopid=destshopid;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setAmount(Double amount){
		this.amount=amount;
	}
	public void setVipamount(Double vipamount){
		this.vipamount=vipamount;
	}
	public void setClientid(Client clientid){
		this.clientid=clientid;
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
	public void setOrdercode(String ordercode){
		this.ordercode=ordercode;
	}

	public String getStatusString() {
		return statusString;
	}

	public String getTypeString() {
		return typeString;
	}

	@Override
	public int compareTo(Ordermain o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
