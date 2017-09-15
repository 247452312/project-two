package mapper;

import entity.Store;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Store_Mapper")
public interface Store_Mapper extends Basic_Mapper<Store> {

	@Select("select * from `store` ${where} ${sort} ${limit}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="defprice",property="defprice"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="cbprice",property="cbprice"),
	})
	public List<Store> getAll(SeachInfo sea);

	@Select("select * from `store` where ${attrName}=#{o}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="defprice",property="defprice"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="cbprice",property="cbprice"),
	})
	public List<Store> getByAttr(SeachInfo sea);

	@Select("select * from `store` where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="defprice",property="defprice"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="cbprice",property="cbprice"),
	})
	public Store getById(SeachInfo sea);

	@Insert("insert into store(shopid,defprice,count,productid,cbprice) values(#{shopid.id},#{defprice},#{count},#{productid.id},#{cbprice})")
	public void insert(Store t);

	@Delete("delete from store where id = ${id}")
	public void delete(int id);

	@Update("update store set shopid=#{shopid.id},defprice=#{defprice},count=#{count},productid=#{productid.id},cbprice=#{cbprice}  where id = #{id}")
	public void update(Store t);

	@Select("select count(1) from store ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from store where id in (#{ids}) ")
	public List<Store> getByMathod_in(String ids);

	@Update("update store set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);
}