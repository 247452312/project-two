package mapper;

import entity.Producttype;
import entity.Shop;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Producttype_Mapper")
public interface Producttype_Mapper extends Basic_Mapper<Producttype> {

	@Select("select * from `producttype` ${where} ${sort} ${limit}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="name",property="name"),
	})
	public List<Producttype> getAll(SeachInfo sea);

	@Select("select * from `producttype` where ${attrName}=#{o}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="name",property="name"),
	})
	public List<Producttype> getByAttr(JsonData1 j);

	@Select("select * from `producttype` where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="name",property="name"),
	})
	public Producttype getById(SeachInfo sea);

	@Insert("insert into producttype(fexp,name) values(#{fexp},#{name})")
	public void insert(Producttype t);

	@Delete("delete from producttype where id = ${id}")
	public void delete(int id);

	@Update("update producttype set fexp=#{fexp},name=#{name}  where id = #{id}")
	public void update(Producttype t);

	@Select("select count(1) from producttype ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from producttype where id in (#{ids}) ")
	public List<Producttype> getByMathod_in(String ids);

	@Update("update producttype set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);

	@Select("select * from Producttype order by Producttype.id desc limit 1")
	public Producttype getNew();
}