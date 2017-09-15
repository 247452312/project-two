package entity;

public class Orderdetail implements Comparable<Orderdetail>  {

	private Double amount;
	private Integer id;
	private String fexp;
	private Double point;
	private Double price;
	private Double count;
	private Product productid;
	private Ordermain orderid;
	private Double cbprice;


	public Orderdetail(){
	}

	public Orderdetail(Double amount,Integer id,String fexp,Double point,Double price,Double count,Product productid,Ordermain orderid,Double cbprice){

		this.amount=amount;
		this.id=id;
		this.fexp=fexp;
		this.point=point;
		this.price=price;
		this.count=count;
		this.productid=productid;
		this.orderid=orderid;
		this.cbprice=cbprice;
	}

	public Orderdetail(Double amount,String fexp,Double point,Double price,Double count,Product productid,Ordermain orderid,Double cbprice){

		this.amount=amount;
		this.fexp=fexp;
		this.point=point;
		this.price=price;
		this.count=count;
		this.productid=productid;
		this.orderid=orderid;
		this.cbprice=cbprice;
	}

	public Orderdetail(int id){
		this.id=id;
	}



	public Double getAmount(){
		return amount;
	}
	public Integer getId(){
		return id;
	}
	public String getFexp(){
		return fexp;
	}
	public Double getPoint(){
		return point;
	}
	public Double getPrice(){
		return price;
	}
	public Double getCount(){
		return count;
	}
	public Product getProductid(){
		return productid;
	}
	public Ordermain getOrderid(){
		return orderid;
	}
	public Double getCbprice(){
		return cbprice;
	}
	public void setAmount(Double amount){
		this.amount=amount;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setPoint(Double point){
		this.point=point;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	public void setCount(Double count){
		this.count=count;
	}
	public void setProductid(Product productid){
		this.productid=productid;
	}
	public void setOrderid(Ordermain orderid){
		this.orderid=orderid;
	}
	public void setCbprice(Double cbprice){
		this.cbprice=cbprice;
	}

	@Override
	public int compareTo(Orderdetail o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
