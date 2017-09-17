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
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("Shop_ServiceImpl")
public class Shop_ServiceImpl extends Basic_ServiceImpl<Shop> implements Shop_Service{
	@Resource(name="Shop_Mapper")
	Shop_Mapper mapper;

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


}
