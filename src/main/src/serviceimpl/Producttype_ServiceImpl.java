package serviceimpl;


import entity.Producttype;
import mapper.Producttype_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Producttype_Service;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("Producttype_ServiceImpl")
public class Producttype_ServiceImpl extends Basic_ServiceImpl<Producttype> implements Producttype_Service{
	@Resource(name="Producttype_Mapper")
    Producttype_Mapper mapper;

    public void selectByAll(int[] trem, int[] compare, String[] text, int[] join, ModelMap m) {
        SeachInfo sea = new SeachInfo();
        StringBuilder sb = new StringBuilder("where ");
        sea.setTable("producttype");
        for (int i = 0;i<trem.length;i++) {
            sea.setCol(StatusUtils.producttypeSelectMap.get(trem[i]));
            sea.setMath(StatusUtils.compareMap.get(compare[i]));
            sea.setValue(text[i]);
            sb.append(sea.getNoWhere());
            if (join[i] == 0) {
                sb.append(" and ");
            } else {
                sb.append(" or  ");
            }
        }
        String where = sb.toString().substring(0,sb.length()-4);
        sea.setWhere(where);
        sea.setPageno(1);
        sea.setRowcount(mapper.getSize(sea));
        List<Producttype> list = mapper.getAll(sea);
        m.put("list",list);
        m.put("info",sea);
    }
}
