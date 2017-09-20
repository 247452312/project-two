package mapper;

import entity.Client;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Client_Mapper")
public interface Client_Mapper extends Basic_Mapper<Client> {

	@Select("select * from `client` ${where} ${sort} ${limit}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="lxr",property="lxr"),
		@Result(column="tel",property="tel"),
		@Result(column="name",property="name"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="bank",property="bank"),
		@Result(column="addr",property="addr"),
		@Result(column="telmov",property="telmov"),
		@Result(column="code",property="code"),
	})
	public List<Client> getAll(SeachInfo sea);

	@Select("select * from `client` where ${attrName}=#{o}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="lxr",property="lxr"),
		@Result(column="tel",property="tel"),
		@Result(column="name",property="name"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="bank",property="bank"),
		@Result(column="addr",property="addr"),
		@Result(column="telmov",property="telmov"),
		@Result(column="code",property="code"),
	})
	public List<Client> getByAttr(JsonData1 j);

	@Select("select * from `client` where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="lxr",property="lxr"),
		@Result(column="tel",property="tel"),
		@Result(column="name",property="name"),
		@Result(column="userid",property="userid",one=@One(select="mapper.User_Mapper.getById")),
		@Result(column="createdate",property="createdate"),
		@Result(column="bank",property="bank"),
		@Result(column="addr",property="addr"),
		@Result(column="telmov",property="telmov"),
		@Result(column="code",property="code"),
	})
	public Client getById(SeachInfo sea);

	@Insert("insert into client(fexp,lxr,tel,name,userid,createdate,bank,addr,telmov,code) values(#{fexp},#{lxr},#{tel},#{name},#{userid.id},#{createdate},#{bank},#{addr},#{telmov},#{code})")
	public void insert(Client t);

	@Delete("delete from client where id = ${id}")
	public void delete(int id);

	@Update("update client set fexp=#{fexp},lxr=#{lxr},tel=#{tel},name=#{name},userid=#{userid.id},createdate=#{createdate},bank=#{bank},addr=#{addr},telmov=#{telmov},code=#{code}  where id = #{id}")
	public void update(Client t);

	@Select("select count(1) from client ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from client where id in (#{ids}) ")
	public List<Client> getByMathod_in(String ids);

	@Update("update client set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);
}