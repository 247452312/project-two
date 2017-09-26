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

    public JsonData insertType1(int type, Ordermain om, HttpSession session) {
        om.setOrdertype(type);
        om.setCreatedate(Info.getNow());
        om.setUserid((User) session.getAttribute("user"));
        service.insert(om);
        return new JsonData(1, "" + service.getNew().getId());
    }

    public JsonData insertType2(ArrayList<Orderdetail> list) {
        for (Orderdetail orderdetail : list) {
            oservice.insert(orderdetail);
        }
        return new JsonData(1);
    }

    @RequestMapping("insert")
    public @ResponseBody
    JsonData insert(OrdermainParam op, HttpSession session) {
        ArrayList<Orderdetail> list = op.getDetails();
        Double d = 0.0;
        for (Orderdetail orderdetail : list) {
            d += orderdetail.getAmount();
        }
        op.getMain().setAmount(d);
        JsonData jd = insertType1(op.getType(), op.getMain(), session);
        if (jd.getStatus() != 1) return jd;
        return insertType2(list);
    }


}

class OrdermainParam {
    private Integer type;
    private Ordermain main;
    private ArrayList<Orderdetail> details;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Ordermain getMain() {
        return main;
    }

    public void setMain(Ordermain main) {
        this.main = main;
    }

    public ArrayList<Orderdetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Orderdetail> details) {
        this.details = details;
    }

    public OrdermainParam() {


    }
}
