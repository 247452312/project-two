package mapper;

import entity.Shop;
import entity.Store;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Shop_Mapper")
public interface Shop_Mapper extends Basic_Mapper<Shop> {

    @Select("select shop.*,user.`name` username from `shop` inner join user on user.id=shop.userid ${where} ${sort} ${limit}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "name", property = "name"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "telmov", property = "telmov"),
            @Result(column = "code", property = "code"),
    })
    public List<Shop> getAll(SeachInfo sea);

    @Select("select shop.*,user.`name` username from `shop` inner join user on user.id=shop.userid where ${attrName}=#{o}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "name", property = "name"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "telmov", property = "telmov"),
            @Result(column = "code", property = "code"),
    })
    public List<Shop> getByAttr(JsonData1 j);

    @Select("select shop.*,user.`name` username from `shop` inner join user on user.id=shop.userid where shop.id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "username",property = "username"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "name", property = "name"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "telmov", property = "telmov"),
            @Result(column = "code", property = "code"),
    })
    public Shop getById(SeachInfo sea);

    @Insert("insert into shop(fexp,tel,name,userid,createdate,addr,telmov,code) values(#{fexp},#{tel},#{name},#{userid},#{createdate},#{addr},#{telmov},#{code})")
    public void insert(Shop t);

    @Delete("delete from shop where id = ${id}")
    public void delete(int id);

    @Update("update shop set fexp=#{fexp},tel=#{tel},name=#{name},userid=#{userid},createdate=#{createdate},addr=#{addr},telmov=#{telmov},code=#{code}  where id = #{id}")
    public void update(Shop t);

    @Select("select count(1) from shop ${where}")
    public long getSize(SeachInfo sea);

    @Select("select * from shop where id in (#{ids}) ")
    public List<Shop> getByMathod_in(String ids);

    @Update("update shop set ${attrName}=#{o} where id = #{id}")
    public void updateAttr(JsonData1 j);

    @Select("select * from Shop order by Shop.id desc limit 1")
    public Shop getNew();
}