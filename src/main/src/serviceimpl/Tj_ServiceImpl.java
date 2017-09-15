package serviceimpl;

import entity.tj.*;
import mapper.Tj_Mapper;
import org.springframework.stereotype.Service;
import service.Tj_Service;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("Tj_ServiceImpl")
public class Tj_ServiceImpl implements Tj_Service {
    @Resource(name = "Tj_Mapper")
    Tj_Mapper mapper;

    @Override
    public List<Tj_All> callAll(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join) {
        String where = getWhere("",null,null,null,null);
        return mapper.callall(where, sdate, ddate);
    }

    @Override
    public List<Tj_Product> callproduct(String sdate,String ddate,int[] trem, int[] compare, String[] text, int[] join) {
        String where = getWhere("product",trem,compare,text,join);
        return mapper.callproduct(where, sdate, ddate);
    }

    @Override
    public List<Tj_Shop> callShop(String sdate,String ddate,int[] trem, int[] compare, String[] text, int[] join) {
        String where = getWhere("shop",trem,compare,text,join);
        return mapper.callshop(where, sdate, ddate);
    }

    @Override
    public List<Tj_User> callUser(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join) {
        if(sdate==null)sdate="";
        if(ddate==null)ddate="";
        String where = getWhere("user",trem,compare,text,join);
        return mapper.calluser(where, sdate, ddate);
    }

    @Override
    public List<Tj_Vip> callVip(String sdate,String ddate,int[] trem, int[] compare, String[] text, int[] join) {
        String where = getWhere("vip",trem,compare,text,join);
        return mapper.callvip(where, sdate, ddate);
    }

    @Override
    public List<Tj_VipMx> callVipMx(int id) {
        return mapper.callVipMx(id);
    }

    public String getWhere(String table, int[] trem, int[] compare, String[] text, int[] join) {
        if(trem==null)return "1=1";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trem.length; i++) {
            if (compare[i] != 1) {
                sb.append(" " + getMap(table).get(trem[i]) + " " + getMap("compare").get(compare[i]) + " " + text[i]);

            } else {
                text[i] = text[i].replaceAll("\"", "");
                text[i] = text[i].replaceAll("，", ",");
                String[] ss = text[i].split(",");
                String s1 = getMap(table).get(trem[i]).toString();
                for (String s : ss) {
                    sb.append(" " + s1 + " like %" + s + "% or ");
                }
                sb.substring(0, sb.length() - 3);
            }
            if (join[i] == 0)
                sb.append(" and");
            else
                sb.append(" or ");
        }
        sb.substring(0,sb.length()-3);
        return sb.toString();
    }

    public Map getMap(String string) {
        string = string.toLowerCase();
        if (string.equals("vip")) return StatusUtils.VIPSelectMap;
        if (string.equals("compare")) return StatusUtils.compareMap;
        if (string.equals("user")) return StatusUtils.UserSelectMap;
        if (string.equals("viptype")) return StatusUtils.ViptypeSelectMap;
        if (string.equals("producttype")) return StatusUtils.producttypeSelectMap;
        if (string.equals("shop")) return StatusUtils.ShopSelectMap;
        if (string.equals("product")) return StatusUtils.ProductSelectMap;
        if (string.equals("client")) return StatusUtils.ClientSelectMap;
        return null;

    }
}
