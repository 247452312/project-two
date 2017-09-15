package serviceimpl;


import entity.Shop;
import mapper.Shop_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Shop_Service;
import utils.Info;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("Shop_ServiceImpl")
public class Shop_ServiceImpl extends Basic_ServiceImpl<Shop> implements Shop_Service{
	@Resource(name="Shop_Mapper")
	Shop_Mapper mapper;

    @Override
    public void insert(Shop shop) {
        shop.setCreatedate(Info.getNow());
        super.insert(shop);
    }

    @Override
    public void update(Shop shop) {
        Shop s = mapper.getById(shop.getId());
        shop.setCreatedate(s.getCreatedate());
        shop.setUserid(s.getUserid());
        super.update(shop);
    }

    public void selectByAll(int[] trem, int[] compare, String[] text, int[] join, ModelMap m) {
        SeachInfo sea = new SeachInfo();
        StringBuilder sb = new StringBuilder("where ");
        sea.setTable("Shop");
        for (int i = 0;i<trem.length;i++) {
            sea.setCol(StatusUtils.ShopSelectMap.get(trem[i]));
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
        List<Shop> list = mapper.getAll(sea);
        m.put("list",list);
        m.put("info",sea);
    }
}
