package mapper;

import entity.Checkmain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Checkmain_Mapper")
public interface Checkmain_Mapper extends Basic_Mapper<Checkmain> {

	@Select("select * from `checkmain` ${where} ${sort} ${limit}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="checkcode",property="checkcode"),
		@Result(column="checkdate",property="checkdate"),
		@Result(column="status",property="status"),
		@Result(column="checkname",property="checkname"),
	})
	public List<Checkmain> getAll(SeachInfo sea);

	@Select("select * from `checkmain` where ${attrName}=#{o}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="checkcode",property="checkcode"),
		@Result(column="checkdate",property="checkdate"),
		@Result(column="status",property="status"),
		@Result(column="checkname",property="checkname"),
	})
	public List<Checkmain> getByAttr(JsonData1 j);

	@Select("select * from `checkmain` where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="checkcode",property="checkcode"),
		@Result(column="checkdate",property="checkdate"),
		@Result(column="status",property="status"),
		@Result(column="checkname",property="checkname"),
	})
	public Checkmain getById(SeachInfo sea);

	@Insert("insert into checkmain(fexp,shopid,checkcode,checkdate,status,checkname) values(#{fexp},#{shopid.id},#{checkcode},#{checkdate},#{status},#{checkname})")
	public void insert(Checkmain t);

	@Delete("delete from checkmain where id = ${id}")
	public void delete(int id);

	@Update("update checkmain set fexp=#{fexp},shopid=#{shopid.id},checkcode=#{checkcode},checkdate=#{checkdate},status=#{status},checkname=#{checkname}  where id = #{id}")
	public void update(Checkmain t);

	@Select("select count(1) from checkmain ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from checkmain where id in (#{ids}) ")
	public List<Checkmain> getByMathod_in(String ids);

	@Update("update checkmain set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);

	@Select("select * from `checkmain` where id = (select MAX(id) from `checkmain`)")
	public Checkmain getNew();


	@Select("call dj_autocreate(#{id})")
	public Object autocreate(int id);
}