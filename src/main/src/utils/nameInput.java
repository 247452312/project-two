package utils;

import java.util.HashMap;
import java.util.Map;

public class nameInput {
    String sql;
    String option;
    HashMap<Integer,String> input;

    public nameInput(String sql, String option, HashMap<Integer,String> input) {
        this.sql = sql;
        this.option = option;
        this.input = input;
    }

    public nameInput() {
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public HashMap<Integer,String> getInput() {
        return input;
    }

    public void setInput(HashMap<Integer,String> input) {
        this.input = input;
    }
}
