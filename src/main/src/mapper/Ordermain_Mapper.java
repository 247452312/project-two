package mapper;

import entity.Ordermain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Ordermain_Mapper")
public interface Ordermain_Mapper extends Basic_Mapper<Ordermain> {

    @Select("select * from `ordermain` ${where} ${sort} ${limit}")
    @Results({
            @Result(column = "status", property = "status"),
            @Result(column = "orderdate", property = "orderdate"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "ordertype", property = "ordertype"),
            @Result(column = "vipid", property = "vipid", one = @One(select = "mapper.Vip_Mapper.getById")),
            @Result(column = "destshopid", property = "destshopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "vipamount", property = "vipamount"),
            @Result(column = "clientid", property = "clientid", one = @One(select = "mapper.Client_Mapper.getById")),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "ordercode", property = "ordercode"),
    })
    public List<Ordermain> getAll(SeachInfo sea);

    @Select("select * from `ordermain` where ${attrName}=#{o}")
    @Results({
            @Result(column = "status", property = "status"),
            @Result(column = "orderdate", property = "orderdate"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "ordertype", property = "ordertype"),
            @Result(column = "vipid", property = "vipid", one = @One(select = "mapper.Vip_Mapper.getById")),
            @Result(column = "destshopid", property = "destshopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "vipamount", property = "vipamount"),
            @Result(column = "clientid", property = "clientid", one = @One(select = "mapper.Client_Mapper.getById")),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "ordercode", property = "ordercode"),
    })
    public List<Ordermain> getByAttr(JsonData1 j);

    @Select("select * from `ordermain` where id = #{id}")
    @Results({
            @Result(column = "status", property = "status"),
            @Result(column = "orderdate", property = "orderdate"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "ordertype", property = "ordertype"),
            @Result(column = "vipid", property = "vipid", one = @One(select = "mapper.Vip_Mapper.getById")),
            @Result(column = "destshopid", property = "destshopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "vipamount", property = "vipamount"),
            @Result(column = "clientid", property = "clientid", one = @One(select = "mapper.Client_Mapper.getById")),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "ordercode", property = "ordercode"),
    })
    public Ordermain getById(SeachInfo sea);

    @Insert("insert into ordermain(status,orderdate,createdate,userid,ordertype,vipid,destshopid,amount,vipamount,clientid,fexp,point,shopid,ordercode) values(#{status},#{orderdate},#{createdate},#{userid.id},#{ordertype},#{vipid.id},#{destshopid.id},#{amount},#{vipamount},#{clientid.id},#{fexp},#{point},#{shopid.id},#{ordercode})")
    public void insert(Ordermain t);

    @Delete("delete from ordermain where id = ${id}")
    public void delete(int id);

    @Update("update ordermain set status=#{status},orderdate=#{orderdate},createdate=#{createdate},userid=#{userid.id},ordertype=#{ordertype},vipid=#{vipid.id},destshopid=#{destshopid.id},amount=#{amount},vipamount=#{vipamount},clientid=#{clientid.id},fexp=#{fexp},point=#{point},shopid=#{shopid.id},ordercode=#{ordercode}  where id = #{id}")
    public void update(Ordermain t);

    @Select("select count(1) from ordermain ${where}")
    public long getSize(SeachInfo sea);

    @Select("select * from ordermain where id in (#{ids}) ")
    public List<Ordermain> getByMathod_in(String ids);

    @Update("update ordermain set ${attrName}=${o} where id = #{id}")
    public void updateAttr(JsonData1 j);

    @Select("select * from `ordermain` where id = (select MAX(id) from `ordermain`)")
    @Results({
            @Result(column = "status", property = "status"),
            @Result(column = "orderdate", property = "orderdate"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "ordertype", property = "ordertype"),
            @Result(column = "vipid", property = "vipid", one = @One(select = "mapper.Vip_Mapper.getById")),
            @Result(column = "destshopid", property = "destshopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "vipamount", property = "vipamount"),
            @Result(column = "clientid", property = "clientid", one = @One(select = "mapper.Client_Mapper.getById")),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "ordercode", property = "ordercode"),
    })
    public Ordermain getNew();
}