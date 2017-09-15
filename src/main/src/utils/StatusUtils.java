package utils;


import java.util.HashMap;
import java.util.Map;

public class StatusUtils {

    private String[] vipSelect = new String[]{"会员编码","会员姓名","会员性别","会员状态","会员类型","家庭住址","固定电话","移动电话","身份证号","创建人","介绍人","生日","办理分店"};
    private String[] productSelect = new String[]{"助记码","名字"};
    private String[] clientSelect = new String[]{"编号","名字"};
    private String[] userSelect = new String[]{"名字"};
    private String[] viptypeSelect = new String[]{"名字","备注"};
    private String[] shopSelect = new String[]{"编号","名字","地址"};
    private String[] compare = new String[]{"模糊查询","多项查询(以"+'"'+","+'"'+"隔开)","等于=","小于&gt;","大于&lt;","小于等于≤","大于等于≥"};

    public static String[] sex = new String[]{"女","男"};
    public static String[] VIPStatus = new String[]{"禁用","可用"};

    public static Map<Integer,String> VIPSelectMap = new HashMap<Integer, String>();
    public static Map<Integer,String> compareMap = new HashMap<Integer, String>();
    public static Map<Integer,String> UserSelectMap = new HashMap<Integer, String>();
    public static Map<Integer,String> ViptypeSelectMap = new HashMap<Integer, String>();
    public static Map<Integer,String> producttypeSelectMap = new HashMap<Integer, String>();
    public static Map<Integer,String> ShopSelectMap = new HashMap<Integer, String>();
    public static Map<Integer,String> ProductSelectMap = new HashMap<Integer, String>();
    public static Map<Integer,String> ClientSelectMap = new HashMap<Integer, String>();

    static {
        VIPSelectMap.put(0,"code");
        VIPSelectMap.put(1,"name");
        VIPSelectMap.put(2,"sex");
        VIPSelectMap.put(3,"status");
        VIPSelectMap.put(4,"viptypeid");
        VIPSelectMap.put(5,"addr");
        VIPSelectMap.put(6,"tel");
        VIPSelectMap.put(7,"telmov");
        VIPSelectMap.put(8,"ccode");
        VIPSelectMap.put(9,"userid");
        VIPSelectMap.put(10,"jsr");
        VIPSelectMap.put(11,"birthdate");
        VIPSelectMap.put(12,"shopid");


        UserSelectMap.put(0,"name");


        ViptypeSelectMap.put(0,"name");
        ViptypeSelectMap.put(1,"fexp");


        producttypeSelectMap.put(0,"name");
        producttypeSelectMap.put(1,"fexp");

        ShopSelectMap.put(0,"code");
        ShopSelectMap.put(1,"name");
        ShopSelectMap.put(2,"addr");

        ProductSelectMap.put(0,"zjm");
        ProductSelectMap.put(1,"name");

        ClientSelectMap.put(0,"code");
        ClientSelectMap.put(1,"name");





        compareMap.put(0,"like");
        compareMap.put(1,"in");
        compareMap.put(2,"=");
        compareMap.put(3,"!=");
        compareMap.put(4,"<");
        compareMap.put(5,">");
        compareMap.put(6,"<=");
        compareMap.put(7,">=");
    }

    public String[] getVipSelect() { return vipSelect; }

    public String[] getProductSelect() {
        return productSelect;
    }

    public String[] getClientSelect() {
        return clientSelect;
    }

    public String[] getUserSelect() {
        return userSelect;
    }

    public String[] getViptypeSelect() {
        return viptypeSelect;
    }

    public String[] getShopSelect() {
        return shopSelect;
    }

    public String[] getCompare() {
        return compare;
    }
}
