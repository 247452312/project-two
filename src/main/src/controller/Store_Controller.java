package controller;

import entity.Product;
import entity.Store;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Product_ServiceImpl;
import serviceimpl.Store_ServiceImpl;
import utils.JsonData;
import utils.JsonData1;
import utils.ListAndSearchInfo;
import utils.SeachInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Store")
public class Store_Controller extends Basic_Controller<Store> {
    @Resource(name = "Store_ServiceImpl")
    Store_ServiceImpl service;

    @Resource(name = "Product_ServiceImpl")
    Product_ServiceImpl pservice;


    @RequestMapping("ready")
    public @ResponseBody
    ListAndSearchInfo ready(SeachInfo sea,int shopid) {
        List<Store> list = service.getByAttr(new JsonData1("shopid", shopid));
        List<Product> plist = new ArrayList<Product>();
        for (Store store : list) {
            plist.add(pservice.getById(store.getProductid().getId()));
        }
        return new ListAndSearchInfo(sea, list);
    }

    @RequestMapping("updateStore")
    public @ResponseBody
    JsonData updateStore(int id, int count, int cbprice){
        if(count<0||cbprice<0)return new JsonData(0,"成本或单价不能小于0");
        service.updateAttr(new JsonData1("count",id,count));
        service.updateAttr(new JsonData1("cbprice",id,cbprice));
        return new JsonData(1);
    }

    @RequestMapping("add")
    public String ad(ModelMap m, HttpServletRequest req) {
        m.put("shopid",req.getParameter("shopid"));
        m.put("productid",req.getParameter("productid"));
        m.put("productname",req.getParameter("productname"));
        m.put("cmd", "add");
        return getTypeName() + "/add";
    }

    @RequestMapping("update")
    public String updat(int id, ModelMap m, HttpServletRequest req) {
        m.put("shopid",req.getParameter("shopid"));
        m.put("product",pservice.getById(Integer.valueOf(req.getParameter("productid"))));
        m.put("object", service.getById(id));
        m.put("cmd", "update");
        return getTypeName() + "/add";
    }

}
