package entity;

public class Producttype implements Comparable<Producttype>  {

	private Integer id;
	private String fexp;
	private String name;


	public Producttype(){
	}

	public Producttype(Integer id,String fexp,String name){

		this.id=id;
		this.fexp=fexp;
		this.name=name;
	}

	public Producttype(String fexp,String name){

		this.fexp=fexp;
		this.name=name;
	}

	public Producttype(int id){
		this.id=id;
	}



	public Integer getId(){
		return id;
	}
	public String getFexp(){
		return fexp;
	}
	public String getName(){
		return name;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setFexp(String fexp){
		this.fexp=fexp;
	}
	public void setName(String name){
		this.name=name;
	}

	@Override
	public int compareTo(Producttype o) {
		if(o.getId()==null||this.id==o.getId())return 0;
		else if(this.id>o.getId())return 1;
		else if(this.id<o.getId())return -1;
		else return 0;
	}
}
