package utils;


import entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import serviceimpl.*;

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
    static Client_ServiceImpl clientService = (Client_ServiceImpl) ac.getBean("Client_ServiceImpl");

    //通用静态变量
    public static String[] sex = new String[]{"男", "女"};
    public static String[] VIPStatus = new String[]{"可用", "禁用"};
    public static String[] ProductStatus = new String[]{"可用", "不可用"};
    public static String[] UserStatus = new String[]{"操作员", "职员", "离职", "临时工"};
    public static String[] CheckmainStatus = new String[]{"可修改", "已完成"};
    public static String[] OrdermainStatus = new String[]{"自动生成", "手动生成"};
    public static String[] OrdermainType = new String[]{"充值单","退款单","项目充值单","项目退款单",
            "采购单","采购退货单","销售单","销售退货单","移库单","库损单","库溢单"};

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
    public static Map<Integer, nameInput> CheckdetailSelectMap = new HashMap<Integer, nameInput>();
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
    private Map<Integer, nameInput> checkmainInput;
    private Map<Integer, nameInput> checkdetailInput;
    private Map<Integer, nameInput> ordermainInput;

    //单据类型
    private String[] orderTypeArray=OrdermainType;
    //权限列表
    private String[] powerArray={"操作员信息","供货商信息","会员信息","分店信息","商品信息","会员类型信息","商品类型信息"
            ,"库存初始","库存盘点","盘点记录","充值单","退换单","项目充值单","项目退款单","采购单","采购退款单"
            ,"销售单","销售退款单","移库单","库存单","库溢单","单据列表","会员明细余额统计表","会员统计表","操作员统计表"
            ,"分店统计表","商品统计表","综合统计表"/*,"数据导入"*/};
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
        ViptypeSelectMap.put(0, new nameInput("name", "类型名字", null));
        ViptypeSelectMap.put(1, new nameInput("fexp", "类型备注", null));
        //producttype
        ProductSelectMap.put(0, new nameInput("code", "商品编号", null));
        ProductSelectMap.put(1, new nameInput("name", "商品名称", null));
        ProductSelectMap.put(2, new nameInput("mode", "商品型号", null));
        ProductSelectMap.put(3, new nameInput("unit", "商品单位", null));
        ProductSelectMap.put(4, new nameInput("status", "商品状态", null));
        ProductSelectMap.put(5, new nameInput("producttypeid", "商品类型名称", null));

        //Shop
        ShopSelectMap.put(0, new nameInput("code", "分店编号", null));
        ShopSelectMap.put(1, new nameInput("name", "分店名称", null));
        ShopSelectMap.put(2, new nameInput("addr", "分店地址", null));
        ShopSelectMap.put(3, new nameInput("tel", "分店电话", null));
        ShopSelectMap.put(4, new nameInput("telmov", "分店手机", null));
        ShopSelectMap.put(5, new nameInput("fexp", "分店备注", null));
        ShopSelectMap.put(6, new nameInput("createdate", "创建日期", null));
        //Product
        producttypeSelectMap.put(0, new nameInput("name", "货类名称", null));
        producttypeSelectMap.put(1, new nameInput("fexp", "货类备注", null));

        //Client
        ClientSelectMap.put(0, new nameInput("code", "供货商编号", null));
        ClientSelectMap.put(1, new nameInput("name", "供货商名字", null));
        ClientSelectMap.put(2, new nameInput("addr", "供货商地址", null));
        ClientSelectMap.put(3, new nameInput("tel", "供货商电话", null));
        ClientSelectMap.put(4, new nameInput("telmov", "供货商手机", null));
        ClientSelectMap.put(5, new nameInput("lxr", "联系人", null));
        ClientSelectMap.put(6, new nameInput("name", "创建日期", null));
        //Store
        StoreSelectMap.put(0, new nameInput("shopid", "分店名称", null));
        //Checkmain
        CheckmainSelectMap.put(0, new nameInput("checkcode", "盘点编号", null));
        CheckmainSelectMap.put(1, new nameInput("checkname", "盘点名称", null));
        CheckmainSelectMap.put(2, new nameInput("checkdate", "盘点日期", null));
        CheckmainSelectMap.put(3, new nameInput("shopid", "分店名字", null));
        CheckmainSelectMap.put(4, new nameInput("status", "盘点状态", null));
        CheckmainSelectMap.put(5, new nameInput("fexp", "盘点备注", null));
        //Checkdetail
        CheckdetailSelectMap.put(0, new nameInput("checkid", "主表", null));
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
        OrdermainSelectMap.put(10, new nameInput("destshopid", "目的分店", null));
        OrdermainSelectMap.put(11, new nameInput("status", "单据状态", null));
        OrdermainSelectMap.put(12, new nameInput("fexp", "单据备注", null));
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
        checkmainInput = CheckmainSelectMap;
        ordermainInput = OrdermainSelectMap;
        checkdetailInput = CheckdetailSelectMap;
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
        //盘点主表加入状态
        Map<Integer, String> CheckmainStatusMap = new HashMap<Integer, String>();
        for (int i = 0; i < CheckmainStatus.length; i++) CheckmainStatusMap.put(i, CheckmainStatus[i]);
        checkmainInput.get(4).setInput(CheckmainStatusMap);
        //////////////////////////////////////////////////////////////////////////////////
        //单据加入状态
        Map<Integer, String> OrdermainStatusMap = new HashMap<Integer, String>();
        for (int i = 0; i < OrdermainStatus.length; i++) OrdermainStatusMap.put(i, OrdermainStatus[i]);
        ordermainInput.get(11).setInput(OrdermainStatusMap);
        //单据加入类型
        Map<Integer, String> OrdermainTypeMap = new HashMap<Integer, String>();
        for (int i = 0; i < OrdermainType.length; i++) OrdermainTypeMap.put(i, OrdermainType[i]);
        ordermainInput.get(1).setInput(OrdermainTypeMap);
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
        Map<Integer, String> userMap = new HashMap<Integer, String>();
        List<User> vipUslist = userService.getAll(new SeachInfo(false));
        for (User us : vipUslist) userMap.put(us.getId(), us.getName());
        vipInput.get(9).setInput(userMap);
        //VIP表加入商店
        Map<Integer, String> shopMap = new HashMap<Integer, String>();
        List<Shop> vipShoplist = shopService.getAll(new SeachInfo(false));
        for (Shop sp : vipShoplist) shopMap.put(sp.getId(), sp.getName());
        vipInput.get(11).setInput(shopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //库存加入分店
        storeInput.get(0).setInput(shopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //商品加入商品类型
        Map<Integer, String> protypeMap = new HashMap<Integer, String>();
        List<Producttype> proProtypelist = producttypeService.getAll(new SeachInfo(false));
        for (Producttype sp : proProtypelist) protypeMap.put(sp.getId(), sp.getName());
        productInput.get(5).setInput(protypeMap);
        //////////////////////////////////////////////////////////////////////////////////
        //操作员加入分店
        userInput.get(13).setInput(shopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //库存加入分店
        storeInput.get(0).setInput(shopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //盘点主表加入分店
        checkmainInput.get(3).setInput(shopMap);
        //////////////////////////////////////////////////////////////////////////////////
        //单据表加入供应商
        Map<Integer, String> clientMap = new HashMap<Integer, String>();
        List<Client> clientlist = clientService.getAll(new SeachInfo(false));
        for (Client cl : clientlist) clientMap.put(cl.getId(), cl.getName());
        ordermainInput.get(7).setInput(clientMap);
        //单据表加入分店
        ordermainInput.get(9).setInput(shopMap);
        //单据表加入目的分店
        ordermainInput.get(10).setInput(shopMap);
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
        return checkmainInput;
    }

    public Map<Integer, nameInput> getOrdermainInput() {
        return ordermainInput;
    }

    public Map<Integer, nameInput> getCheckdetailInput() {
        return checkdetailInput;
    }

    public String[] getOrderTypeArray() {
        return orderTypeArray;
    }

    public String[] getPowerArray() {
        return powerArray;
    }

}
