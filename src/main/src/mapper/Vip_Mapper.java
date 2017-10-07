package mapper;

import entity.Vip;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import utils.JsonData1;
import utils.SeachInfo;

import java.util.List;


@Repository("Vip_Mapper")
public interface Vip_Mapper extends Basic_Mapper<Vip> {

    @Select("select * from `vip` ${where} ${sort} ${limit}")
    @Results({
            @Result(column = "sex", property = "sex"),
            @Result(column = "ccode", property = "ccode"),
            @Result(column = "status", property = "status"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "code", property = "code"),
            @Result(column = "jsr", property = "jsr"),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "name", property = "name"),
            @Result(column = "birthdate", property = "birthdate"),
            @Result(column = "viptypeid", property = "viptypeid", one = @One(select = "mapper.Viptype_Mapper.getById")),
            @Result(column = "telmov", property = "telmov"),
    })
    public List<Vip> getAll(SeachInfo sea);

    @Select("select * from `vip` where ${attrName}=#{o}")
    @Results({
            @Result(column = "sex", property = "sex"),
            @Result(column = "ccode", property = "ccode"),
            @Result(column = "status", property = "status"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "code", property = "code"),
            @Result(column = "jsr", property = "jsr"),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "name", property = "name"),
            @Result(column = "birthdate", property = "birthdate"),
            @Result(column = "viptypeid", property = "viptypeid", one = @One(select = "mapper.Viptype_Mapper.getById")),
            @Result(column = "telmov", property = "telmov"),
    })
    public List<Vip> getByAttr(JsonData1 j);

    @Select("select * from `vip` where id = #{id}")
    @Results({
            @Result(column = "sex", property = "sex"),
            @Result(column = "ccode", property = "ccode"),
            @Result(column = "status", property = "status"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "userid", property = "userid", one = @One(select = "mapper.User_Mapper.getById")),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "code", property = "code"),
            @Result(column = "jsr", property = "jsr"),
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "fexp", property = "fexp"),
            @Result(column = "point", property = "point"),
            @Result(column = "shopid", property = "shopid", one = @One(select = "mapper.Shop_Mapper.getById")),
            @Result(column = "name", property = "name"),
            @Result(column = "birthdate", property = "birthdate"),
            @Result(column = "viptypeid", property = "viptypeid", one = @One(select = "mapper.Viptype_Mapper.getById")),
            @Result(column = "telmov", property = "telmov"),
    })
    public Vip getById(SeachInfo sea);

    @Insert("insert into vip(sex,ccode,status,tel,userid,createdate,addr,code,jsr,amount,fexp,point,shopid,name,birthdate,viptypeid,telmov) values(#{sex},#{ccode},#{status},#{tel},#{userid.id},#{createdate},#{addr},#{code},#{jsr},#{amount},#{fexp},#{point},#{shopid.id},#{name},#{birthdate},#{viptypeid.id},#{telmov})")
    public void insert(Vip t);

    @Delete("delete from vip where id = #{id}")
    public void delete(int id);

    @Update("update vip set sex=#{sex},ccode=#{ccode},status=#{status},tel=#{tel},userid=#{userid.id},createdate=#{createdate},addr=#{addr},code=#{code},jsr=#{jsr},amount=#{amount},fexp=#{fexp},point=#{point},shopid=#{shopid.id},name=#{name},birthdate=#{birthdate},viptypeid=#{viptypeid.id},telmov=#{telmov}  where id = #{id}")
    public void update(Vip t);

    @Select("select count(1) from vip ${where}")
    public long getSize(SeachInfo sea);

    @Select("select * from vip where id in (#{ids}) ")
    public List<Vip> getByMathod_in(String ids);

    @Update("update Vip set ${attrName}=#{o} where id = #{id}")
    public void updateAttr(JsonData1 j);


    @Select("select * from Vip order by Vip.id desc limit 1")
    public Vip getNew();

}