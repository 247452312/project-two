package controller;

import entity.Orderdetail;
import entity.Ordermain;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Orderdetail_ServiceImpl;
import serviceimpl.Ordermain_ServiceImpl;
import utils.Info;
import utils.JsonData;
import utils.JsonData1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Ordermain")
public class Ordermain_Controller extends Basic_Controller<Ordermain> {
    @Resource(name = "Ordermain_ServiceImpl")
    Ordermain_ServiceImpl service;

    @Resource(name = "Orderdetail_ServiceImpl")
    Orderdetail_ServiceImpl oservice;

    @RequestMapping("insert1")
    public @ResponseBody
    JsonData insertType1(Ordermain om, HttpSession session) {
        om.setCreatedate(Info.getNow());
        om.setUserid((User) session.getAttribute("user"));
        service.insert(om);
        return new JsonData(1, "" + service.getNew().getId());
    }

    @RequestMapping("insert2")
    public @ResponseBody
    JsonData insertType2(Orderdetail orderdetail) {
        oservice.insert(orderdetail);
        service.updateAttr(new JsonData1("amount", orderdetail.getOrderid().getId(), "amount+" + orderdetail.getAmount()));
        return new JsonData(1);
    }

    @RequestMapping("update1")
    public @ResponseBody
    JsonData update1(Ordermain om) {
        om.setAmount(0.0);
        service.update(om);
        return new JsonData(1, "" + om.getId());
    }

    @RequestMapping("update2")
    public @ResponseBody
    JsonData update2(Orderdetail od) {
        oservice.update(od);
        service.updateAttr(new JsonData1("amount", od.getOrderid().getId(), "amount+" + od.getAmount()));
        return new JsonData(1);
    }


}
