package utils;


import entity.Producttype;
import entity.Shop;
import entity.User;
import entity.Viptype;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import serviceimpl.Producttype_ServiceImpl;
import serviceimpl.Shop_ServiceImpl;
import serviceimpl.User_ServiceImpl;
import serviceimpl.Viptype_ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusUtils {
    private StatusUtils() {
    }

    private static StatusUtils su;

    //获得实例
    public static StatusUtils getSingle() {
        if (su == null) su = new StatusUtils();
        su.reGetSqls();
        return su;
    }

    //获得service
    static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    static Viptype_ServiceImpl viptypeService = (Viptype_ServiceImpl) ac.getBean("Viptype_ServiceImpl");
    static User_ServiceImpl userService = (User_ServiceImpl) ac.getBean("User_ServiceImpl");
    static Shop_ServiceImpl shopService = (Shop_ServiceImpl) ac.getBean("Shop_ServiceImpl");
    static Producttype_ServiceImpl producttypeService = (Producttype_ServiceImpl) ac.getBean("Producttype_ServiceImpl");


    //通用静态变量
    public static String[] sex = new String[]{"男", "女"};
    public static String[] VIPStatus = new String[]{"可用", "禁用"};
    public static String[] ProductStatus = new String[]{"可用", "不可用"};
    public static String[] UserStatus = new String[]{"操作员", "职员", "离职", "临时工"};

    public static Map<Integer, nameInput> compareMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> VIPSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> UserSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> ViptypeSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> producttypeSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> ShopSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> ProductSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> ClientSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> StoreSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> CheckmainSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer, nameInput> OrdermainSelectMap = new HashMap<Integer, nameInput>();

    //选项下拉列表
    private Map<Integer, nameInput> storeInput;
    private Map<Integer, nameInput> compareInput;
    private Map<Integer, nameInput> vipInput;
    private Map<Integer, nameInput> userInput;
    private Map<Integer, nameInput> viptypeInput;
    private Map<Integer, nameInput> producttypeInput;
    private Map<Integer, nameInput> shopInput;
    private Map<Integer, nameInput> productInput;
    private Map<Integer, nameInput> clientInput;
    private Map<Integer, nameInput> CheckmainInput;
    private Map<Integer, nameInput> OrdermainInput;

    //静态常用固定量
    static {
        //VIP
        VIPSelectMap.put(0, new nameInput("code", "会员编码", null));
        VIPSelectMap.put(1, new nameInput("name", "会员姓名", null));
        VIPSelectMap.put(2, new nameInput("sex", "会员性别", null));
        VIPSelectMap.put(3, new nameInput("status", "会员状态", null));
        VIPSelectMap.put(4, new nameInput("viptypeid", "会员类型", null));
        VIPSelectMap.put(5, new nameInput("addr", "家庭住址", null));
        VIPSelectMap.put(6, new nameInput("tel", "固定电话", null));
        VIPSelectMap.put(7, new nameInput("telmov", "移动电话", null));
        VIPSelectMap.put(8, new nameInput("ccode", "身份证号", null));
        VIPSelectMap.put(9, new nameInput("userid", "创建人", null));
        VIPSelectMap.put(10, new nameInput("jsr", "介绍人", null));
        VIPSelectMap.put(11, new nameInput("shopid", "办理分店", null));
        //User
        UserSelectMap.put(0, new nameInput("code", "操作员编号", null));
        UserSelectMap.put(1, new nameInput("name", "操作员姓名", null));
        UserSelectMap.put(2, new nameInput("sex", "操作员性别", null));
        UserSelectMap.put(3, new nameInput("addr", "操作员地址", null));
        UserSelectMap.put(4, new nameInput("tel", "操作员电话", null));
        UserSelectMap.put(5, new nameInput("telmov", "操作员手机", null));
        UserSelectMap.put(6, new nameInput("birthdate", "操作员出生日期", null));
        UserSelectMap.put(7, new nameInput("ccode", "操作员证件号", null));
        UserSelectMap.put(8, new nameInput("status", "操作员状态", null));
        UserSelectMap.put(9, new nameInput("createdate", "操作员创建日期", null));
        UserSelectMap.put(10, new nameInput("enterdate", "操作员入职日期", null));
        UserSelectMap.put(11, new nameInput("exitdate", "操作员离职日期", null));
        UserSelectMap.put(12, new nameInput("fexp", "操作员备注", null));
        UserSelectMap.put(13, new nameInput("shopid", "办理分店", null));
        //Viptype
        ViptypeSelectMap.put(0, new nameInput("name", "名字", null));
        ViptypeSelectMap.put(1, new nameInput("fexp", "备注", null));
        //producttype
        ProductSelectMap.put(0, new nameInput("code", "商品编号", null));
        ProductSelectMap.put(1, new nameInput("name", "商品名称", null));
        ProductSelectMap.put(2, new nameInput("mode", "商品型号", null));
        ProductSelectMap.put(3, new nameInput("unit", "商品单位", null));
        ProductSelectMap.put(4, new nameInput("status", "商品状态", null));
        ProductSelectMap.put(5, new nameInput("producttypeid", "商品类型名称", null));

        //Shop
        ShopSelectMap.put(0, new nameInput("code", "编号", null));
        ShopSelectMap.put(1, new nameInput("name", "名称", null));
        ShopSelectMap.put(2, new nameInput("addr", "地址", null));
        ShopSelectMap.put(3, new nameInput("tel", "电话", null));
        ShopSelectMap.put(4, new nameInput("telmov", "手机", null));
        ShopSelectMap.put(5, new nameInput("fexp", "备注", null));
        ShopSelectMap.put(6, new nameInput("createdate", "创建日期", null));
        //Product
        producttypeSelectMap.put(0, new nameInput("name", "名称", null));
        producttypeSelectMap.put(1, new nameInput("fexp", "备注", null));

        //Client
        ClientSelectMap.put(0, new nameInput("code", "编号", null));
        ClientSelectMap.put(1, new nameInput("name", "名字", null));
        ClientSelectMap.put(2, new nameInput("addr", "地址", null));
        ClientSelectMap.put(3, new nameInput("tel", "电话", null));
        ClientSelectMap.put(4, new nameInput("telmov", "手机", null));
        ClientSelectMap.put(5, new nameInput("lxr", "联系人", null));
        ClientSelectMap.put(6, new nameInput("name", "创建日期", null));
        //Store
        StoreSelectMap.put(0, new nameInput("shopid", "分店名称", null));
        //Checkmain
        CheckmainSelectMap.put(0, new nameInput("code", "盘点编号", null));
        CheckmainSelectMap.put(0, new nameInput("code", "盘点名称", null));
        CheckmainSelectMap.put(0, new nameInput("code", "盘点日期", null));
        CheckmainSelectMap.put(0, new nameInput("code", "分店名字", null));
        CheckmainSelectMap.put(0, new nameInput("code", "盘点状态", null));
        CheckmainSelectMap.put(0, new nameInput("code", "盘点备注", null));
        //Ordermain
        OrdermainSelectMap.put(0, new nameInput("code", "单据编号", null));
        OrdermainSelectMap.put(1, new nameInput("ordertype", "单据类型", null));
        OrdermainSelectMap.put(2, new nameInput("orderdate", "单据日期", null));
        OrdermainSelectMap.put(3, new nameInput("createdate", "录入日期", null));
        OrdermainSelectMap.put(4, new nameInput("amount", "总金额", null));
        OrdermainSelectMap.put(5, new nameInput("vipamount", "会员卡支付", null));
        OrdermainSelectMap.put(6, new nameInput("point", "总积分", null));
        OrdermainSelectMap.put(7, new nameInput("clientid", "供应商", null));
        OrdermainSelectMap.put(8, new nameInput("vipid", "会员名字", null));
        OrdermainSelectMap.put(9, new nameInput("shopid", "分店", null));
        OrdermainSelectMap.put(11, new nameInput("destshopid", "目的分店", null));
        OrdermainSelectMap.put(12, new nameInput("status", "单据状态", null));
        OrdermainSelectMap.put(13, new nameInput("fexp", "单据备注", null));
        //符号比较
        compareMap.put(0, new nameInput("like", "模糊查询", null));
        compareMap.put(1, new nameInput("in", "多项查询(以" + '"' + ";" + '"' + "隔开)", null));
        compareMap.put(2, new nameInput("=", "＝", null));
        compareMap.put(3, new nameInput("!=", "≠", null));
        compareMap.put(4, new nameInput("<", "＜", null));
        compareMap.put(5, new nameInput(">", "＞", null));
        compareMap.put(6, new nameInput("<=", "≤", null));
        compareMap.put(7, new nameInput(">=", "≥", null));
    }

    //动态块，每次传输getStatus时使用
    {
        //成员变量
        compareInput = compareMap;
        vipInput = VIPSelectMap;
        userInput = UserSelectMap;
        viptypeInput = ViptypeSelectMap;
        producttypeInput = producttypeSelectMap;
        shopInput = ShopSelectMap;
        productInput = ProductSelectMap;
        clientInput = ClientSelectMap;
        storeInput = StoreSelectMap;
        CheckmainInput = CheckmainSelectMap;
        OrdermainInput = OrdermainSelectMap;
        //////////////////////////////////////////////////////////////////////////////////
        //VIP表加入性别
        Map<Integer, String> sexMap = new HashMap<Integer, String>();
        for (int i = 0; i < sex.length; i++) sexMap.put(i, sex[i]);
        vipInput.get(2).setInput(sexMap);
        //VIP表加入状态
        Map<Integer, String> vipStatusMap = new HashMap<Integer, String>();
        for (int i = 0; i < VIPStatus.length; i++) vipStatusMap.put(i, VIPStatus[i]);
        vipInput.get(3).setInput(vipStatusMap);
        //////////////////////////////////////////////////////////////////////////////////
        //货物表加入状态
        Map<Integer, String> proStatusMap = new HashMap<Integer, String>();
        for (int i = 0; i < ProductStatus.length; i++) proStatusMap.put(i, ProductStatus[i]);
        productInput.get(4).setInput(proStatusMap);

        //////////////////////////////////////////////////////////////////////////////////
        // 操作员表加入状态
        Map<Integer, String> userStatusMap = new HashMap<Integer, String>();
        for (int i = 0; i < UserStatus.length; i++) userStatusMap.put(i, UserStatus[i]);
        userInput.get(8).setInput(userStatusMap);
        //操作员表加入性别
        userInput.get(2).setInput(sexMap);
        //////////////////////////////////////////////////////////////////////////////////
    }

    //重新抽取数据库内容
    public void reGetSqls() {
        //////////////////////////////////////////////////////////////////////////////////
        //VIP表加入类型
        Map<Integer, String> viptypeMap = new HashMap<Integer, String>();
        List<Viptype> vplist = viptypeService.getAll(new SeachInfo(false));
        for (Viptype vp : vplist) viptypeMap.put(vp.getId(), vp.getName());
        vipInput.get(4).setInput(viptypeMap);
        //VIP表加入创建人
        Map<Integer, String> vipUserMap = new HashMap<Integer, String>();
        List<User> vipUslist = userService.getAll(new SeachInfo(false));
        for (User us : vipUslist) vipUserMap.put(us.getId(), us.getName());
        vipInput.get(9).setInput(vipUserMap);
        //VIP表加入商店
        Map<Integer, String> vipShopMap = new HashMap<Integer, String>();
        List<Shop> vipShoplist = shopService.getAll(new SeachInfo(false));
        for (Shop sp : vipShoplist) vipShopMap.put(sp.getId(), sp.getName());
        vipInput.get(11).setInput(vipShopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //库存加入分店
        Map<Integer, String> scoreShopMap = new HashMap<Integer, String>();
        List<Shop> scoreShoplist = shopService.getAll(new SeachInfo(false));
        for (Shop sp : scoreShoplist) scoreShopMap.put(sp.getId(), sp.getName());
        vipInput.get(0).setInput(scoreShopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //商品加入商品类型
        Map<Integer, String> proProtypeMap = new HashMap<Integer, String>();
        List<Producttype> proProtypelist = producttypeService.getAll(new SeachInfo(false));
        for (Producttype sp : proProtypelist) proProtypeMap.put(sp.getId(), sp.getName());
        productInput.get(5).setInput(proProtypeMap);
        //////////////////////////////////////////////////////////////////////////////////
        //操作员加入分店
        userInput.get(13).setInput(scoreShopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //库存加入分店
        storeInput.get(0).setInput(scoreShopMap);
        //////////////////////////////////////////////////////////////////////////////////
    }

    public Map<Integer, nameInput> getVipInput() {
        return vipInput;
    }

    public Map<Integer, nameInput> getUserInput() {
        return userInput;
    }

    public Map<Integer, nameInput> getViptypeInput() {
        return viptypeInput;
    }

    public Map<Integer, nameInput> getProducttypeInput() {
        return producttypeInput;
    }

    public Map<Integer, nameInput> getShopInput() {
        return shopInput;
    }

    public Map<Integer, nameInput> getProductInput() {
        return productInput;
    }

    public Map<Integer, nameInput> getClientInput() {
        return clientInput;
    }

    public Map<Integer, nameInput> getCompareInput() {
        return compareInput;
    }

    public Map<Integer, nameInput> getStoreInput() {
        return storeInput;
    }

    public Map<Integer, nameInput> getCheckmainInput() {
        return CheckmainInput;
    }

    public Map<Integer, nameInput> getOrdermainInput() {
        return OrdermainInput;
    }
}
