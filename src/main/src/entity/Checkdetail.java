package entity;

public class Checkdetail implements Comparable<Checkdetail>{

	private Integer id;
	private String fexp;
	private Double count;
	private Product productid;
	private Checkmain checkid;


	public Checkdetail(){
	}

	public Checkdetail(Integer id, String fexp, Double count, Product productid, Checkmain checkid){

		this.id=id;
		this.fexp=fexp;
		this.count=count;
		this.productid=productid;
		this.checkid=checkid;
	}

	public Checkdetail(String fexp, Double count, Product productid, Checkmain checkid){

		this.fexp=fexp;
		this.count=count;
		this.productid=productid;
		this.checkid=checkid;
	}

	public Checkdetail(int id){
		this.id=id;
	}



	public Integer getId(){
		return id;
	}
	public String getFexp(){
		return fexp;
	}
	public Double getCount(){
		return count;
	}
	public Product getProductid(){
		return productid;
	}
	public Checkmain getCheckid(){
		return checkid;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setCount(Double count){
		this.count=count;
	}
	public void setProductid(Product productid){
		this.productid=productid;
	}
	public void setCheckid(Checkmain checkid){
		this.checkid=checkid;
	}

	@Override
	public int compareTo(Checkdetail o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
