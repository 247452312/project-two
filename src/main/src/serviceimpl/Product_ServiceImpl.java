package serviceimpl;


import entity.Product;
import entity.User;
import mapper.Product_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Product_Service;
import utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("Product_ServiceImpl")
public class Product_ServiceImpl extends Basic_ServiceImpl<Product> implements Product_Service{
	@Resource(name="Product_Mapper")
	Product_Mapper mapper;



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

}
