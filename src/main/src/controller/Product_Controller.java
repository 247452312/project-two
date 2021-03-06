package controller;

import entity.Product;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Product_ServiceImpl;
import utils.JsonData;
import utils.ListAndSearchInfo;
import utils.SeachInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TreeSet;

@Controller
@RequestMapping("Product")
public class Product_Controller extends Basic_Controller<Product> {
    @Resource(name = "Product_ServiceImpl")
    Product_ServiceImpl service;


    @RequestMapping("updateStatus")
    @ResponseBody
    JsonData updateStatus(int id) {
        service.updateStatus(id);
        return new JsonData(1);
    }


    @RequestMapping("getProduct")
    public @ResponseBody
    TreeSet<Product> getProduct(String name) {
        TreeSet<Product> tree = new TreeSet<Product>();
        SeachInfo sea = new SeachInfo();
        sea.setPagecount(1);
        sea.setTable("product");
        sea.setCol("name");
        sea.setValue(name);

        List<Product> p = service.getAll(sea);
        sea.setCol("zjm");
        sea.setValue(name.toUpperCase());
        List<Product> p1 = service.getAll(sea);
        tree.addAll(p);
        tree.addAll(p1);
        return tree;

    }
}

