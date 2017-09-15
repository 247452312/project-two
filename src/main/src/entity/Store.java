package entity;

public class Store implements Comparable<Store>  {

	private Integer id;
	private Shop shopid;
	private Double defprice;
	private Double count;
	private Product productid;
	private Double cbprice;


	public Store(){
	}

	public Store(Integer id,Shop shopid,Double defprice,Double count,Product productid,Double cbprice){

		this.id=id;
		this.shopid=shopid;
		this.defprice=defprice;
		this.count=count;
		this.productid=productid;
		this.cbprice=cbprice;
	}

	public Store(Shop shopid,Double defprice,Double count,Product productid,Double cbprice){

		this.shopid=shopid;
		this.defprice=defprice;
		this.count=count;
		this.productid=productid;
		this.cbprice=cbprice;
	}

	public Store(int id){
		this.id=id;
	}



	public Integer getId(){
		return id;
	}
	public Shop getShopid(){
		return shopid;
	}
	public Double getDefprice(){
		return defprice;
	}
	public Double getCount(){
		return count;
	}
	public Product getProductid(){
		return productid;
	}
	public Double getCbprice(){
		return cbprice;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setShopid(Shop shopid){
		this.shopid=shopid;
	}
	public void setDefprice(Double defprice){
		this.defprice=defprice;
	}
	public void setCount(Double count){
		this.count=count;
	}
	public void setProductid(Product productid){
		this.productid=productid;
	}
	public void setCbprice(Double cbprice){
		this.cbprice=cbprice;
	}

	@Override
	public int compareTo(Store o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
