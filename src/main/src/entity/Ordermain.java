package entity;

public class Ordermain implements Comparable<Ordermain>  {

	private Integer status;
	private String orderdate;
	private String createdate;
	private User userid;
	private Integer ordertype;
	private Vip vipid;
	private Shop destshopid;
	private Integer id;
	private Double amount;
	private Double vipamount;
	private Client clientid;
	private String fexp;
	private Double point;
	private Shop shopid;
	private String ordercode;


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

	@Override
	public int compareTo(Ordermain o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
