package entity.tj;

import entity.Shop;
import entity.User;

public class Tj_User implements Tj {
    private Integer userid;
    private String username;
    private Double je1;
    private Double point1;
    private Integer count1;
    private Double je2;
    private Double point2;
    private Integer count2;
    private Double je3;
    private Integer count3;
    private Double je4;
    private Integer count4;
    private Double je5;
    private Double point5;
    private Integer count5;
    private Double xj5;
    private Double je6;
    private Double point6;
    private Integer count6;
    private Double xj6;
    private Integer count7;
    private Integer count8;
    private Integer count9;

    private Integer sex;
    private String ccode;
    //0->操作员 1->职员 2->离职 3->临时工
    private Integer status;
    private String tel;
    //创建人
    private Integer userid1;
    //创建日期
    private String createdate;
    private String addr;
    private String code;
    //默认123
    private String pass;
    //id
    private Integer id;
    private String fexp;
    private String enterdate;//就职时间
    private Integer shopid;
    private String exitdate;
    private String name;
    private String birthdate;
    private String telmov;
    private String fright;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getJe1() {
        return je1;
    }

    public void setJe1(Double je1) {
        this.je1 = je1;
    }

    public Double getPoint1() {
        return point1;
    }

    public void setPoint1(Double point1) {
        this.point1 = point1;
    }

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public Double getJe2() {
        return je2;
    }

    public void setJe2(Double je2) {
        this.je2 = je2;
    }

    public Double getPoint2() {
        return point2;
    }

    public void setPoint2(Double point2) {
        this.point2 = point2;
    }

    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
    }

    public Double getJe3() {
        return je3;
    }

    public void setJe3(Double je3) {
        this.je3 = je3;
    }

    public Integer getCount3() {
        return count3;
    }

    public void setCount3(Integer count3) {
        this.count3 = count3;
    }

    public Double getJe4() {
        return je4;
    }

    public void setJe4(Double je4) {
        this.je4 = je4;
    }

    public Integer getCount4() {
        return count4;
    }

    public void setCount4(Integer count4) {
        this.count4 = count4;
    }

    public Double getJe5() {
        return je5;
    }

    public void setJe5(Double je5) {
        this.je5 = je5;
    }

    public Double getPoint5() {
        return point5;
    }

    public void setPoint5(Double point5) {
        this.point5 = point5;
    }

    public Integer getCount5() {
        return count5;
    }

    public void setCount5(Integer count5) {
        this.count5 = count5;
    }

    public Double getXj5() {
        return xj5;
    }

    public void setXj5(Double xj5) {
        this.xj5 = xj5;
    }

    public Double getJe6() {
        return je6;
    }

    public void setJe6(Double je6) {
        this.je6 = je6;
    }

    public Double getPoint6() {
        return point6;
    }

    public void setPoint6(Double point6) {
        this.point6 = point6;
    }

    public Integer getCount6() {
        return count6;
    }

    public void setCount6(Integer count6) {
        this.count6 = count6;
    }

    public Double getXj6() {
        return xj6;
    }

    public void setXj6(Double xj6) {
        this.xj6 = xj6;
    }

    public Integer getCount7() {
        return count7;
    }

    public void setCount7(Integer count7) {
        this.count7 = count7;
    }

    public Integer getCount8() {
        return count8;
    }

    public void setCount8(Integer count8) {
        this.count8 = count8;
    }

    public Integer getCount9() {
        return count9;
    }

    public void setCount9(Integer count9) {
        this.count9 = count9;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getUserid1() {
        return userid1;
    }

    public void setUserid1(Integer userid1) {
        this.userid1 = userid1;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFexp() {
        return fexp;
    }

    public void setFexp(String fexp) {
        this.fexp = fexp;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getExitdate() {
        return exitdate;
    }

    public void setExitdate(String exitdate) {
        this.exitdate = exitdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelmov() {
        return telmov;
    }

    public void setTelmov(String telmov) {
        this.telmov = telmov;
    }

    public String getFright() {
        return fright;
    }

    public void setFright(String fright) {
        this.fright = fright;
    }

    public Tj_User() {

    }
}
