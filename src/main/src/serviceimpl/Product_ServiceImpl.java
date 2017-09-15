package serviceimpl;


import entity.Product;
import mapper.Product_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Product_Service;
import utils.*;

import javax.annotation.Resource;
import java.util.List;

@Service("Product_ServiceImpl")
public class Product_ServiceImpl extends Basic_ServiceImpl<Product> implements Product_Service{
	@Resource(name="Product_Mapper")
	Product_Mapper mapper;



    @Override
    public void insert(Product product) {
        product.setZjm(ZJM.String2Alpha(product.getName()));
        product.setCreatedate(Info.getNow());
        super.insert(product);
    }

    @Override
    public void update(Product product) {
        Product p = mapper.getById(product.getId());
        product.setZjm(ZJM.String2Alpha(product.getName()));
        product.setCreatedate(p.getCreatedate());
        product.setUserid(p.getUserid());
        super.update(product);
    }

    public void updateStatus(int id) {
        Product p = mapper.getById(id);
        if(p.getStatus()==1){
            mapper.updateAttr(new JsonData1("status",id,0));
        }else{
            mapper.updateAttr(new JsonData1("status",id,1));

        }
    }

    public void selectByAll(int[] trem, int[] compare, String[] text, int[] join, ModelMap m) {
        SeachInfo sea = new SeachInfo();
        StringBuilder sb = new StringBuilder("where ");
        sea.setTable("Product");
        for (int i = 0; i < trem.length; i++) {
            sea.setCol(StatusUtils.ProductSelectMap.get(trem[i]));
            sea.setMath(StatusUtils.compareMap.get(compare[i]));
            sea.setValue(text[i]);
            sb.append(sea.getNoWhere());
            if (join[i] == 0) {
                sb.append(" and ");
            } else {
                sb.append(" or  ");
            }
        }
        String where = sb.toString().substring(0, sb.length() - 4);
        sea.setWhere(where);
        sea.setPageno(1);
        sea.setRowcount(mapper.getSize(sea));
        List<Product> list = mapper.getAll(sea);
        m.put("list", list);
        m.put("info", sea);
    }
}
