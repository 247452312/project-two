package serviceimpl;

import mapper.Basic_Mapper;
import utils.JsonData1;
import utils.ListAndSearchInfo;
import utils.SeachInfo;
import utils.StatusUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Basic_ServiceImpl<T> {

    @SuppressWarnings("unchecked")
    private Basic_Mapper<T> getMapper() {
        try {
            Field f = getClass().getDeclaredField("mapper");
            f.setAccessible(true);
            return (Basic_Mapper<T>) f.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<T> getAll(SeachInfo sea) {
        return getMapper().getAll(sea);
    }

    public T getById(int id) {
        return getMapper().getById(id);
    }

    public List<T> getByAttr(JsonData1 j) {
        return getMapper().getByAttr(j);
    }

    public void insert(T t) {
        getMapper().insert(t);
    }

    public void delete(int id) {
        getMapper().delete(id);
    }

    public void update(T t) {
        getMapper().update(t);
    }

    public long getSize(SeachInfo sea) {
        return getMapper().getSize(sea);
    }

    public List<T> getByMathod_in(String ids) {
        return getMapper().getByMathod_in(ids);
    }

    public void updateAttr(JsonData1 j) {
        getMapper().updateAttr(j);
    }

    public ListAndSearchInfo selectByAll(SeachInfo sea, int[] trem, int[] compare, String[] text, int[] join) {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        String tableName = cls.getSimpleName();
        StringBuilder sb = new StringBuilder("where ");
        sea.setTable(tableName);
        if (trem != null) {
            for (int i = 0; i < trem.length; i++) {
                if (text[i] == null) continue;
                sea.setCol(StatusUtils.VIPSelectMap.get(trem[i]));
                //in中文标点改英文标点
                if (compare[i] == 1) {
                    text[i].replaceAll("，", ",");
                    continue;
                }
                sea.setMath(StatusUtils.compareMap.get(compare[i]));
                sea.setValue(text[i]);
                sb.append(sea.getNoWhere());
                if (join[i] == 0) {
                    sb.append(" and ");
                } else {
                    sb.append(" or  ");
                }
            }
            for (int i = 0; i < compare.length; i++) {
                if (compare[i] == 1) {
                    String[] s = text[i].split(",");
                    for (String s1 : s) {
                        s1 = s1.replaceAll("\"", "");
                        sb.append(" " + trem[i] + " like %" + s1 + "% or");
                    }
                    sb.substring(0, sb.length() - 2);
                    if (join[i] == 0) {
                        sb.append(" and ");
                    } else {
                        sb.append(" or  ");
                    }
                }
            }

        String where = null;
        if (trem.length == 0) where = "";
        else where = sb.toString().substring(0, sb.length() - 4);
        sea.setWhere(where);
        sea.setRowcount(getMapper().getSize(sea));
        List<T> list = getMapper().getAll(sea);
        return new ListAndSearchInfo(sea, list);
        }else{
            ListAndSearchInfo<T> las = new ListAndSearchInfo();
            las.setSea(sea);
            List<T> list = getMapper().getAll(sea);
            las.setList(list);
            return new ListAndSearchInfo(sea,getMapper().getAll(sea));
        }
    }

    public T getNew() {
        return getMapper().getNew();
    }
}
