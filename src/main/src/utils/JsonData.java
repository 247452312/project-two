package utils;

public class JsonData {
    private Integer status;
    private String info;
    private Object o;

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public JsonData(Integer status, String info) {
        this.status = status;
        this.info = info;
    }

    public JsonData(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public JsonData() {

    }
}
