package utils;


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
    public static StatusUtils getSingle(){
        if(su==null)su = new StatusUtils();
        su.reGetSqls();
        return su;
    }
    //获得service
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    Viptype_ServiceImpl viptypeService = (Viptype_ServiceImpl)ac.getBean("Viptype_ServiceImpl");
    User_ServiceImpl userService= (User_ServiceImpl) ac.getBean("User_ServiceImpl");
    Shop_ServiceImpl shopService= (Shop_ServiceImpl) ac.getBean("Shop_ServiceImpl");
    Producttype_ServiceImpl producttypeService = (Producttype_ServiceImpl )ac.getBean("Producttype_ServiceImpl");



    //通用静态变量
    public static String[] sex = new String[]{"男","女"};
    public static String[] VIPStatus = new String[]{"可用","禁用"};

    public static Map<Integer,nameInput> compareMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> VIPSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> UserSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> ViptypeSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> producttypeSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> ShopSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> ProductSelectMap = new HashMap<Integer, nameInput>();
    public static Map<Integer,nameInput> ClientSelectMap = new HashMap<Integer, nameInput>();

    //选项下拉列表
    private Map<Integer,nameInput> compareInput;
    private Map<Integer,nameInput> vipInput;
    private Map<Integer,nameInput> userInput;
    private Map<Integer,nameInput> viptypeInput;
    private Map<Integer,nameInput> producttypeInput;
    private Map<Integer,nameInput> shopInput;
    private Map<Integer,nameInput> productInput;
    private Map<Integer,nameInput> clientInput;

    //静态常用固定量
    static {
        //VIP
        VIPSelectMap.put(0,new nameInput("code","会员编码",null));
        VIPSelectMap.put(1,new nameInput("name","会员姓名",null));
        VIPSelectMap.put(2,new nameInput("sex","会员性别",null));
        VIPSelectMap.put(3,new nameInput("status","会员状态",null));
        VIPSelectMap.put(4,new nameInput("viptypeid","会员类型",null));
        VIPSelectMap.put(5,new nameInput("addr","家庭住址",null));
        VIPSelectMap.put(6,new nameInput("tel","固定电话",null));
        VIPSelectMap.put(7,new nameInput("telmov","移动电话",null));
        VIPSelectMap.put(8,new nameInput("ccode","身份证号",null));
        VIPSelectMap.put(9,new nameInput("userid","创建人",null));
        VIPSelectMap.put(10,new nameInput("jsr","介绍人",null));
        VIPSelectMap.put(11,new nameInput("shopid","办理分店",null));
        //User
        UserSelectMap.put(0,new nameInput("name","名字",null));
        //Viptype
        ViptypeSelectMap.put(0,new nameInput("name","名字",null));
        ViptypeSelectMap.put(1,new nameInput("fexp","备注",null));
        //producttype
        producttypeSelectMap.put(0,new nameInput("name","名字",null));
        producttypeSelectMap.put(1,new nameInput("fexp","备注",null));
        //Shop
        ShopSelectMap.put(0,new nameInput("code","编号",null));
        ShopSelectMap.put(1,new nameInput("name","名称",null));
        ShopSelectMap.put(2,new nameInput("addr","地址",null));
        //Product
        ProductSelectMap.put(0,new nameInput("zjm","助记码",null));
        ProductSelectMap.put(1,new nameInput("name","名称",null));
        //Client
        ClientSelectMap.put(0,new nameInput("code","编号",null));
        ClientSelectMap.put(1,new nameInput("name","名字",null));
        //符号比较
        compareMap.put(0,new nameInput("like","模糊查询",null));
        compareMap.put(1,new nameInput("in","多项查询(以"+'"'+";"+'"'+"隔开)",null));
        compareMap.put(2,new nameInput("=","＝",null));
        compareMap.put(3,new nameInput("!=","≠",null));
        compareMap.put(4,new nameInput("<","＜",null));
        compareMap.put(5,new nameInput(">","＞",null));
        compareMap.put(6,new nameInput("<=","≤",null));
        compareMap.put(7,new nameInput(">=","≥",null));
    }
    //动态块，每次传输getStatus时使用
    {
        //成员变量
        compareInput=compareMap;
        vipInput=VIPSelectMap;
        userInput=UserSelectMap;
        viptypeInput=ViptypeSelectMap;
        producttypeInput=producttypeSelectMap;
        shopInput=ShopSelectMap;
        productInput=ProductSelectMap;
        clientInput=ClientSelectMap;

        //VIP表加入性别
        Map<Integer,String> sexMap = new HashMap<Integer, String>();
        for(int i=0;i<sex.length;i++) sexMap.put(i,sex[i]);
        vipInput.get(2).setInput(sexMap);
        //VIP表加入状态
        Map<Integer,String> statusMap = new HashMap<Integer, String>();
        for(int i=0;i<VIPStatus.length;i++) statusMap.put(i,VIPStatus[i]);
        vipInput.get(3).setInput(statusMap);
    }
    //重新抽取数据库内容
    public void reGetSqls(){
        //VIP表加入类型
        Map<Integer,String> viptypeMap = new HashMap<Integer, String>();
        List<Viptype> vplist=viptypeService.getAll(new SeachInfo(false));
        for(Viptype vp:vplist) viptypeMap.put(vp.getId(),vp.getName());
        vipInput.get(4).setInput(viptypeMap);
        //VIP表加入创建人
        Map<Integer,String> userMap = new HashMap<Integer, String>();
        List<User> uslist=userService.getAll(new SeachInfo(false));
        for(User us:uslist) userMap.put(us.getId(),us.getName());
        vipInput.get(9).setInput(userMap);
        //VIP表加入商店
        Map<Integer,String> shopMap = new HashMap<Integer, String>();
        List<Shop> shoplist=shopService.getAll(new SeachInfo(false));
        for(Shop sp:shoplist) shopMap.put(sp.getId(),sp.getName());
        vipInput.get(11).setInput(shopMap);
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
}
