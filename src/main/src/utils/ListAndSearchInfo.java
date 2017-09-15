package utils;

import java.util.List;

public class ListAndSearchInfo<T>{
    public SeachInfo sea;
    public List<T> list;

    public ListAndSearchInfo() {
    }

    public ListAndSearchInfo(SeachInfo sea, List<T> list) {
        this.sea = sea;
        this.list = list;
    }

    public SeachInfo getSea() {
        return sea;
    }

    public void setSea(SeachInfo sea) {
        this.sea = sea;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}