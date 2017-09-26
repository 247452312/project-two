package service;

import entity.tj.*;

import java.util.List;

public interface Tj_Service {
    public List<Tj_All> callAll(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join,String where);

    public List<Tj_Product> callproduct(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join,String where);

    public List<Tj_Shop> callShop(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join,String where);

    public List<Tj_User> callUser(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join,String where);

    public List<Tj_Vip> callVip(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join,String where);

    public List<Tj_VipMx> callVipMx(int id);
}
