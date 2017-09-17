package mapper;

import entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("User_Mapper")
public interface User_Mapper extends Basic_Mapper<User> {

	@Select("select * from `user` ${where} ${sort} ${limit}")
	@Results({
		@Result(column="sex",property="sex"),
		@Result(column="ccode",property="ccode"),
		@Result(column="status",property="status"),
		@Result(column="tel",property="tel"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="addr",property="addr"),
		@Result(column="code",property="code"),
		@Result(column="pass",property="pass"),
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="enterdate",property="enterdate"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="exitdate",property="exitdate"),
		@Result(column="name",property="name"),
		@Result(column="birthdate",property="birthdate"),
		@Result(column="telmov",property="telmov"),
		@Result(column="fright",property="fright"),
	})
	public List<User> getAll(SeachInfo sea);

	@Select("select * from `user` where ${attrName}=#{o}")
	@Results({
		@Result(column="sex",property="sex"),
		@Result(column="ccode",property="ccode"),
		@Result(column="status",property="status"),
		@Result(column="tel",property="tel"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="addr",property="addr"),
		@Result(column="code",property="code"),
		@Result(column="pass",property="pass"),
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="enterdate",property="enterdate"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="exitdate",property="exitdate"),
		@Result(column="name",property="name"),
		@Result(column="birthdate",property="birthdate"),
		@Result(column="telmov",property="telmov"),
		@Result(column="fright",property="fright"),
	})
	public List<User> getByAttr(SeachInfo sea);

	@Select("select * from `user` where id = #{id}")
	@Results({
		@Result(column="sex",property="sex"),
		@Result(column="ccode",property="ccode"),
		@Result(column="status",property="status"),
		@Result(column="tel",property="tel"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="addr",property="addr"),
		@Result(column="code",property="code"),
		@Result(column="pass",property="pass"),
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="enterdate",property="enterdate"),
		@Result(column="shopid",property="shopid",one=@One(select="mapper.Shop_Mapper.getById")),
		@Result(column="exitdate",property="exitdate"),
		@Result(column="name",property="name"),
		@Result(column="birthdate",property="birthdate"),
		@Result(column="telmov",property="telmov"),
		@Result(column="fright",property="fright"),
	})
	public User getById(SeachInfo sea);

	@Insert("insert into user(sex,ccode,status,tel,userid,createdate,addr,code,pass,fexp,enterdate,shopid,exitdate,name,birthdate,telmov,fright) values(#{sex},#{ccode},#{status},#{tel},#{userid.id},#{createdate},#{addr},#{code},#{pass},#{fexp},#{enterdate},#{shopid.id},#{exitdate},#{name},#{birthdate},#{telmov},#{fright})")
	public void insert(User t);

	@Delete("delete from user where id = ${id}")
	public void delete(int id);

	@Update("update user set sex=#{sex},ccode=#{ccode},status=#{status},tel=#{tel},userid=#{userid.id},createdate=#{createdate},addr=#{addr},code=#{code},pass=#{pass},fexp=#{fexp},enterdate=#{enterdate},shopid=#{shopid.id},exitdate=#{exitdate},name=#{name},birthdate=#{birthdate},telmov=#{telmov},fright=#{fright}  where id = #{id}")
	public void update(User t);

	@Select("select count(1) from user ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from user where id in (#{ids}) ")
	public List<User> getByMathod_in(String ids);

	@Update("update user set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);

	@Select("select * from user where name = #{0} and pass = #{1}")
    User login(String user, String pass);
}