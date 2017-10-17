package mapper;

import entity.Orderdetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Orderdetail_Mapper")
public interface Orderdetail_Mapper extends Basic_Mapper<Orderdetail> {

	@Select("select * from `orderdetail` ${where} ${sort} ${limit}")
	@Results({
		@Result(column="amount",property="amount"),
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="point",property="point"),
		@Result(column="price",property="price"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="orderid",property="orderid",one=@One(select="mapper.Ordermain_Mapper.getById")),
		@Result(column="cbprice",property="cbprice"),
	})
	public List<Orderdetail> getAll(SeachInfo sea);

	@Select("select * from `orderdetail` where ${attrName}=#{o}")
	@Results({
		@Result(column="amount",property="amount"),
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="point",property="point"),
		@Result(column="price",property="price"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="orderid",property="orderid",one=@One(select="mapper.Ordermain_Mapper.getById")),
		@Result(column="cbprice",property="cbprice"),
	})
	public List<Orderdetail> getByAttr(JsonData1 j);

	@Select("select * from `orderdetail` where id = #{id}")
	@Results({
		@Result(column="amount",property="amount"),
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="point",property="point"),
		@Result(column="price",property="price"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="orderid",property="orderid",one=@One(select="mapper.Ordermain_Mapper.getById")),
		@Result(column="cbprice",property="cbprice"),
	})
	public Orderdetail getById(SeachInfo sea);

	@Insert("insert into orderdetail(amount,fexp,point,price,count,productid,orderid,cbprice) values(#{amount},#{fexp},#{point},#{price},#{count},#{productid.id},#{orderid.id},#{cbprice})")
	public void insert(Orderdetail t);

	@Delete("delete from orderdetail where id = ${id}")
	public void delete(int id);

	@Update("update orderdetail set " +
			"amount=#{amount}," +
			"fexp=#{fexp}," +
			"point=#{point}," +
			"price=#{price}," +
			"count=#{count}," +
			"productid=#{productid.id}," +
			"orderid=#{orderid.id}," +
			"cbprice=#{cbprice}  " +
			"where id = #{id}")
	public void update(Orderdetail t);

	@Select("select count(1) from orderdetail #{where}")
	public long getSize(SeachInfo sea);

	@Select("select * from orderdetail where id in (#{ids}) ")
	public List<Orderdetail> getByMathod_in(String ids);

	@Update("update orderdetail set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);

}