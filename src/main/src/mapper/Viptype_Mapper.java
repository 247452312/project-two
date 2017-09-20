package mapper;

import entity.Viptype;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Viptype_Mapper")
public interface Viptype_Mapper extends Basic_Mapper<Viptype> {

	@Select("select * from `viptype` ${where} ${sort} ${limit}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="name",property="name"),
	})
	public List<Viptype> getAll(SeachInfo sea);

	@Select("select * from `viptype` where ${attrName}=#{o}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="name",property="name"),
	})
	public List<Viptype> getByAttr(JsonData1 j);

	@Select("select * from `viptype` where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="name",property="name"),
	})
	public Viptype getById(SeachInfo sea);

	@Insert("insert into viptype(fexp,name) values(#{fexp},#{name})")
	public void insert(Viptype t);

	@Delete("delete from viptype where id = ${id}")
	public void delete(int id);

	@Update("update viptype set fexp=#{fexp},name=#{name}  where id = #{id}")
	public void update(Viptype t);

	@Select("select count(1) from viptype ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from viptype where id in (#{ids}) ")
	public List<Viptype> getByMathod_in(String ids);

	@Update("update viptype set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);

}