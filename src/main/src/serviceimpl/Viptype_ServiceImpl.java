package serviceimpl;


import entity.Viptype;
import mapper.Viptype_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Viptype_Service;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("Viptype_ServiceImpl")
public class Viptype_ServiceImpl extends Basic_ServiceImpl<Viptype> implements Viptype_Service{
	@Resource(name="Viptype_Mapper")
	Viptype_Mapper mapper;

    public void selectByAll(int[] trem, int[] compare, String[] text, int[] join, ModelMap m) {
        SeachInfo sea = new SeachInfo();
        StringBuilder sb = new StringBuilder("where ");
        sea.setTable("Viptype");
        for (int i = 0;i<trem.length;i++) {
            sea.setCol(StatusUtils.ViptypeSelectMap.get(trem[i]));
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
        List<Viptype> list = mapper.getAll(sea);
        m.put("list",list);
        m.put("info",sea);
    }
}
