package entity;

import utils.StatusUtils;

public class Product implements Comparable<Product> {

    private Integer status;
    private Double defaultprice;
    private User userid;
    private String createdate;
    private String code;
    private String mode;
    private Producttype producttypeid;
    private Integer id;
    private String peso;
    private Double defaultpoint;
    private String fexp;
    private String unit;
    private String name;
    private String zjm;

    private String statusString;

    public String getStatusString() {
        return statusString;
    }

    public Product() {
    }

    public Product(Integer status, Double defaultprice, User userid, String createdate, String code, String mode, Producttype producttypeid, Integer id, String peso, Double defaultpoint, String fexp, String unit, String name, String zjm) {

        this.status = status;
        this.defaultprice = defaultprice;
        this.userid = userid;
        this.createdate = createdate;
        this.code = code;
        this.mode = mode;
        this.producttypeid = producttypeid;
        this.id = id;
        this.peso = peso;
        this.defaultpoint = defaultpoint;
        this.fexp = fexp;
        this.unit = unit;
        this.name = name;
        this.zjm = zjm;
    }

    public Product(Integer status, Double defaultprice, User userid, String createdate, String code, String mode, Producttype producttypeid, String peso, Double defaultpoint, String fexp, String unit, String name, String zjm) {

        this.status = status;
        this.defaultprice = defaultprice;
        this.userid = userid;
        this.createdate = createdate;
        this.code = code;
        this.mode = mode;
        this.producttypeid = producttypeid;
        this.peso = peso;
        this.defaultpoint = defaultpoint;
        this.fexp = fexp;
        this.unit = unit;
        this.name = name;
        this.zjm = zjm;
    }

    public Product(int id) {
        this.id = id;
    }


    public Integer getStatus() {
        return status;
    }

    public Double getDefaultprice() {
        return defaultprice;
    }

    public User getUserid() {
        return userid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public String getCode() {
        return code;
    }

    public String getMode() {
        return mode;
    }

    public Producttype getProducttypeid() {
        return producttypeid;
    }

    public Integer getId() {
        return id;
    }

    public String getPeso() {
        return peso;
    }

    public Double getDefaultpoint() {
        return defaultpoint;
    }

    public String getFexp() {
        return fexp;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public String getZjm() {
        return zjm;
    }

    public void setStatus(Integer status) {
        this.status = status;
        this.statusString= StatusUtils.ProductStatus[status];
    }

    public void setDefaultprice(Double defaultprice) {
        this.defaultprice = defaultprice;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setProducttypeid(Producttype producttypeid) {
        this.producttypeid = producttypeid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setDefaultpoint(Double defaultpoint) {
        this.defaultpoint = defaultpoint;
    }

    public void setFexp(String fexp) {
        this.fexp = fexp;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }

    @Override
    public int compareTo(Product o) {
        if (o.getId() == null || this.id == o.getId()) return 0;
        else if (this.id > o.getId()) return 1;
        else if (this.id < o.getId()) return -1;
        else return 0;
    }
}
