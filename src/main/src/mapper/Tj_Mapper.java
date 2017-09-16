package mapper;

import entity.tj.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("Tj_Mapper")
public interface Tj_Mapper {



    @Select("call tj_all(#{0},#{1},#{2})")
    public List<Tj_All> callall(String where, String sdate, String ddate);

    @Select("call tj_product(#{0},#{1},#{2})")
    public List<Tj_Product> callproduct(String where, String sdate, String ddate);

    @Select("call tj_shop(#{0},#{1},#{2})")
    public List<Tj_Shop> callshop(String where, String sdate, String ddate);

    @Select("call tj_user(#{0},#{1},#{2})")
    public List<Tj_User> calluser(String where, String sdate, String ddate);

    @Select("call tj_vip(#{0},#{1},#{2})")
    public List<Tj_Vip> callvip(String where, String sdate, String ddate);

    @Select("call tj_vipmx(#{id})")
    public List<Tj_VipMx> callVipMx(int id);

}
