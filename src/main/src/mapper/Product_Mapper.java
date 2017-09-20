package mapper;

import entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Product_Mapper")
public interface Product_Mapper extends Basic_Mapper<Product> {

	@Select("select * from `product` ${where} ${sort} ${limit}")
	@Results({
		@Result(column="status",property="status"),
		@Result(column="defaultprice",property="defaultprice"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="code",property="code"),
		@Result(column="mode",property="mode"),
		@Result(column="producttypeid",property="producttypeid",one=@One(select="mapper.Producttype_Mapper.getById")),
		@Result(id=true,column="id",property="id"),
		@Result(column="peso",property="peso"),
		@Result(column="defaultpoint",property="defaultpoint"),
		@Result(column="fexp",property="fexp"),
		@Result(column="unit",property="unit"),
		@Result(column="name",property="name"),
		@Result(column="zjm",property="zjm"),
	})
	public List<Product> getAll(SeachInfo sea);

	@Select("select * from `product` where ${attrName}=#{o}")
	@Results({
		@Result(column="status",property="status"),
		@Result(column="defaultprice",property="defaultprice"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="code",property="code"),
		@Result(column="mode",property="mode"),
		@Result(column="producttypeid",property="producttypeid",one=@One(select="mapper.Producttype_Mapper.getById")),
		@Result(id=true,column="id",property="id"),
		@Result(column="peso",property="peso"),
		@Result(column="defaultpoint",property="defaultpoint"),
		@Result(column="fexp",property="fexp"),
		@Result(column="unit",property="unit"),
		@Result(column="name",property="name"),
		@Result(column="zjm",property="zjm"),
	})
	public List<Product> getByAttr(JsonData1 j);

	@Select("select * from `product` where id = #{id}")
	@Results({
		@Result(column="status",property="status"),
		@Result(column="defaultprice",property="defaultprice"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="code",property="code"),
		@Result(column="mode",property="mode"),
		@Result(column="producttypeid",property="producttypeid",one=@One(select="mapper.Producttype_Mapper.getById")),
		@Result(id=true,column="id",property="id"),
		@Result(column="peso",property="peso"),
		@Result(column="defaultpoint",property="defaultpoint"),
		@Result(column="fexp",property="fexp"),
		@Result(column="unit",property="unit"),
		@Result(column="name",property="name"),
		@Result(column="zjm",property="zjm"),
	})
	public Product getById(SeachInfo sea);

	@Insert("insert into product(status,defaultprice,userid,createdate,code,mode,producttypeid,peso,defaultpoint,fexp,unit,name,zjm) values(#{status},#{defaultprice},#{userid.id},#{createdate},#{code},#{mode},#{producttypeid.id},#{peso},#{defaultpoint},#{fexp},#{unit},#{name},#{zjm})")
	public void insert(Product t);

	@Delete("delete from product where id = ${id}")
	public void delete(int id);

	@Update("update product set status=#{status},defaultprice=#{defaultprice},userid=#{userid.id},createdate=#{createdate},code=#{code},mode=#{mode},producttypeid=#{producttypeid.id},peso=#{peso},defaultpoint=#{defaultpoint},fexp=#{fexp},unit=#{unit},name=#{name},zjm=#{zjm}  where id = #{id}")
	public void update(Product t);

	@Select("select count(1) from product ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from product where id in (#{ids}) ")
	public List<Product> getByMathod_in(String ids);

	@Update("update product set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);
}