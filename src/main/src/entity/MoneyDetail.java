package entity;

public class MoneyDetail {
    private String code;
    private int type;
    private String data;
    private Shop shopid;
    private double amount;
    private double plus;
    private double reduce;
    private double balance;

    private double plus1;
    private double reduce1;
    private double balance1;
    private String cjr;

    public MoneyDetail() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Shop getShopid() {
        return shopid;
    }

    public void setShopid(Shop shopid) {
        this.shopid = shopid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPlus() {
        return plus;
    }

    public void setPlus(double plus) {
        this.plus = plus;
    }

    public double getReduce() {
        return reduce;
    }

    public void setReduce(double reduce) {
        this.reduce = reduce;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPlus1() {
        return plus1;
    }

    public void setPlus1(double plus1) {
        this.plus1 = plus1;
    }

    public double getReduce1() {
        return reduce1;
    }

    public void setReduce1(double reduce1) {
        this.reduce1 = reduce1;
    }

    public double getBalance1() {
        return balance1;
    }

    public void setBalance1(double balance1) {
        this.balance1 = balance1;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }
}
