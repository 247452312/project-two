package serviceimpl;

import entity.tj.*;
import mapper.Tj_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import service.Tj_Service;
import utils.SeachInfo;
import utils.StatusUtils;
import utils.nameInput;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("Tj_ServiceImpl")
public class Tj_ServiceImpl extends Basic_ServiceImpl<Tj> implements Tj_Service {
    @Resource(name = "Tj_Mapper")
    Tj_Mapper mapper;

    @Override
    public List<Tj_All> callAll(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, String where) {
        String where1 = "";
        if (where.equals(""))
            where1 = getWhere("ordermain", trem, compare, text, join);
        else where1 = where;

        return mapper.callall(where1, sdate, ddate);
    }

    @Override
    public List<Tj_Product> callproduct(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, String where) {
        String where1 = "";
        if (where.equals(""))
            where1 = getWhere("product", trem, compare, text, join);
        else where1 = where;

        return mapper.callproduct(where1, sdate, ddate);
    }

    @Override
    public List<Tj_Shop> callShop(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, String where) {
        String where1 = "";
        if (where.equals(""))
            where1 = getWhere("shop", trem, compare, text, join);
        else where1 = where;
        return mapper.callshop(where1, sdate, ddate);
    }

    @Override
    public List<Tj_User> callUser(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, String where) {
        String where1 = "";
        if (where.equals(""))
            where1 = getWhere("user", trem, compare, text, join);
        else where1 = where;
        return mapper.calluser(where1, sdate, ddate);
    }

    @Override
    public List<Tj_Vip> callVip(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, String where) {
        String where1 = "";
        if (where.equals(""))
            where1 = getWhere("vip", trem, compare, text, join);
        else where1 = where;
        return mapper.callvip(where1, sdate, ddate);
    }

    @Override
    public List<Tj_VipMx> callVipMx(Integer id) {
        return mapper.callVipMx(id);
    }

    public String getWhere(String table, int[] trem, int[] compare, String[] text, int[] join) {
        if (trem == null) return "1=1";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trem.length; i++) {
            if(text[i].contains("\"")||text[i].contains("'")||text[i].contains("or")||text[i].contains("and"))return null;
            if (text[i] == null||text[i].equals("")) continue;
            //in中文标点改英文标点
            if (compare[i] == 1) {
                text[i].replaceAll("；", ";");
                String[] s = text[i].split(";");
                for (String s1 : s) {
                    s1 = s1.replaceAll("\"", "");
                    sb.append(" " + getMap(table).get(trem[i]).getSql() + " like '%" + s1 + "%' or");
                }
                if(s.length!=0)sb = new StringBuilder(sb.substring(0, sb.length() - 2));
                if (join[i] == 0) {
                    sb.append(" and ");
                } else {
                    sb.append(" or  ");
                }
                continue;
            } if(compare[i] != 0){
                sb.append(" " + getMap(table).get(trem[i]).getSql()+" "+getMap("compare").get(compare[i]).getSql()+" '"+text[i]+"'");
            }else{
                sb.append(" " + getMap(table).get(trem[i]).getSql()+" "+getMap("compare").get(compare[i]).getSql()+" '%"+text[i]+"%'");
            }
            if (join[i] == 0) {
                sb.append(" and ");
            } else {
                sb.append(" or  ");
            }
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 4));
        return sb.toString();
    }

    public Map<Integer, nameInput> getMap(String string) {
        string = string.toLowerCase();
        if (string.equals("vip")) return StatusUtils.VIPSelectMap;
        if (string.equals("compare")) return StatusUtils.compareMap;
        if (string.equals("user")) return StatusUtils.UserSelectMap;
        if (string.equals("viptype")) return StatusUtils.ViptypeSelectMap;
        if (string.equals("producttype")) return StatusUtils.producttypeSelectMap;
        if (string.equals("shop")) return StatusUtils.ShopSelectMap;
        if (string.equals("product")) return StatusUtils.ProductSelectMap;
        if (string.equals("client")) return StatusUtils.ClientSelectMap;
        if(string.equals("ordermain"))return StatusUtils.OrdermainSelectMap;
        return null;

    }
}
