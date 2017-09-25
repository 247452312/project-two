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
import utils.JsonData;
import utils.JsonData1;

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
    JsonData insertType1(int type, Ordermain om, HttpSession session) {
        om.setOrdertype(type);
        om.setCreatedate(Info.getNow());
        om.setUserid((User) session.getAttribute("user"));
        service.insert(om);
        return new JsonData(1);
    }

    @RequestMapping("insertType2-1")
    public @ResponseBody JsonData insertType2(int type,Ordermain om, HttpSession session){
        om.setOrdertype(type);
        om.setCreatedate(Info.getNow());
        om.setUserid((User) session.getAttribute("user"));
        om.setAmount(0.0);
        service.insert(om);
        return new JsonData(1,""+service.getNew().getId());
    }

    @RequestMapping("insertType2-2")
    public @ResponseBody JsonData insertType2(int id,List<Orderdetail> list, HttpSession session){
        Double amount = 0.0;
        for (Orderdetail orderdetail : list) {
            orderdetail.setOrderid(new Ordermain(id));
            oservice.insert(orderdetail);
            amount+=orderdetail.getAmount();
        }
        service.updateAttr(new JsonData1("amount",id,amount));
        return new JsonData(1);
    }


}
