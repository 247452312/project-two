package controller;

import entity.Product;
import entity.Store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Product_ServiceImpl;
import serviceimpl.Store_ServiceImpl;
import utils.JsonData1;

import javax.annotation.Resource;
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
    List ready(int shopid) {
        List<Store> list = service.getByAttr(new JsonData1("shopid", shopid));
        List<Product> plist = new ArrayList<Product>();
        for (Store store : list) {
            plist.add(pservice.getById(store.getProductid().getId()));
        }
        return plist;
    }

    @RequestMapping("updateStore")
    public @ResponseBody String updateStore(int id,int count,int cbprice){
        if(count<0||cbprice<0)return "{\"status\":0}";
        service.updateAttr(new JsonData1("count",id,count));
        service.updateAttr(new JsonData1("cbprice",id,cbprice));
        return "{\"status\":1}";
    }



}
