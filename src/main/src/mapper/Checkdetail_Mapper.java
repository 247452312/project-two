package mapper;

import entity.Checkdetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Checkdetail_Mapper")
public interface Checkdetail_Mapper extends Basic_Mapper<Checkdetail> {

	@Select("select * from `checkdetail` ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="checkid",property="checkid",one=@One(select="mapper.Checkmain_Mapper.getById")),
	})
	public List<Checkdetail> getAll(SeachInfo sea);

	@Select("select * from `checkdetail` where ${attrName}=#{o}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="checkid",property="checkid",one=@One(select="mapper.Checkmain_Mapper.getById")),
	})
	public List<Checkdetail> getByAttr(JsonData1 j);

	@Select("select * from `checkdetail` where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="fexp",property="fexp"),
		@Result(column="count",property="count"),
		@Result(column="productid",property="productid",one=@One(select="mapper.Product_Mapper.getById")),
		@Result(column="checkid",property="checkid",one=@One(select="mapper.Checkmain_Mapper.getById")),
	})
	public Checkdetail getById(SeachInfo sea);

	@Insert("insert into checkdetail(fexp,count,productid,checkid) values(#{fexp},#{count},#{productid.id},#{checkid.id})")
	public void insert(Checkdetail t);

	@Delete("delete from checkdetail where id = ${id}")
	public void delete(int id);

	@Update("update checkdetail set fexp=#{fexp},count=#{count},productid=#{productid.id},checkid=#{checkid.id}  where id = #{id}")
	public void update(Checkdetail t);

	@Select("select count(1) from checkdetail ${where}")
	public long getSize(SeachInfo sea);

	@Select("select * from checkdetail where id in (#{ids}) ")
	public List<Checkdetail> getByMathod_in(String ids);


	@Update("update checkdetail set ${attrName}=#{o} where id = #{id}")
	public void updateAttr(JsonData1 j);
}