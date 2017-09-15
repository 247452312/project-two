package controller;

import entity.Orderdetail;
import entity.Ordermain;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Orderdetail_ServiceImpl;
import serviceimpl.Ordermain_ServiceImpl;
import utils.Info;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("Ordermain")
public class Ordermain_Controller extends Basic_Controller<Ordermain> {
    @Resource(name = "Ordermain_ServiceImpl")
    Ordermain_ServiceImpl service;

    @Resource(name = "Orderdetail_ServiceImpl")
    Orderdetail_ServiceImpl oservice;

    @RequestMapping("insertType1")
    public @ResponseBody
    String insertType1(int type,Ordermain om, HttpSession session) {
        om.setOrdertype(type);
        om.setCreatedate(Info.getNow());
        om.setUserid((User) session.getAttribute("user"));
        service.insert(om);
        return "{\"status\":1}";
    }

    @RequestMapping("insertType2")
    public @ResponseBody String insertType2(int type,Ordermain om , List<Orderdetail> list, HttpSession session){
        om.setOrdertype(type);
        om.setCreatedate(Info.getNow());
        om.setUserid((User) session.getAttribute("user"));
        Double amount = 0.0;
        Ordermain o = service.getNew();
        for (Orderdetail orderdetail : list) {
            amount+=orderdetail.getAmount();
            orderdetail.setOrderid(o);
            oservice.insert(orderdetail);
        }
        om.setAmount(amount);
        service.insert(om);
        return "{\"status\":1}";
    }

}
