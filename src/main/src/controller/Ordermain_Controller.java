package controller;

import entity.Orderdetail;
import entity.Ordermain;
import entity.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Orderdetail_ServiceImpl;
import serviceimpl.Ordermain_ServiceImpl;
import utils.Info;
import utils.JsonData;
import utils.JsonData1;
import utils.ListAndSearchInfo;

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
        service.updateAttr(new JsonData1("amount", orderdetail.getOrderid().getId(), orderdetail.getAmount()));
        return new JsonData(1, "" + service.getNew().getId());
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
        service.updateAttr(new JsonData1("amount", od.getOrderid().getId(), od.getAmount()));
        return new JsonData(1);
    }

    @RequestMapping("selectProg")
    public @ResponseBody
    ListAndSearchInfo selectProg(int ordertype, int vipid, int shopid) {
        List<Ordermain> mainList = selectByAttrLS(new JsonData1("ordertype", ordertype)).getList();
        List<Ordermain> mainVipList = new ArrayList<Ordermain>();
        for (Ordermain om : mainList) {
            if (om.getVipid().getId() == vipid && om.getShopid().getId() == shopid)
                mainVipList.add(om);
        }
        //List<Ordermain> mainList = service.getByAttr(new JsonData1("ordertype", ordertype));
        List<Orderdetail> details = new ArrayList<Orderdetail>();
        for (Ordermain ordermain : mainVipList) {
            details.addAll(oservice.getByAttr(new JsonData1("orderid", ordermain.getId())));
        }
        return new ListAndSearchInfo(null, details);
    }

    @RequestMapping("selectProject")
    public @ResponseBody
    ListAndSearchInfo selectProject(int vipid, int shopid) {
        ListAndSearchInfo<Orderdetail> lasi10 = selectProg(10, vipid, shopid);
        ListAndSearchInfo<Orderdetail> lasi11 = selectProg(11, vipid, shopid);
        for (Orderdetail orderdetail : lasi10.list) {
            for (Orderdetail orderdetail1 : lasi11.list) {
                if (orderdetail.getProductid().getId() == orderdetail1.getProductid().getId()) {
                    if (orderdetail.getCount() == orderdetail1.getCount()) {
                        lasi10.list.remove(orderdetail);
                    } else {
                        orderdetail.setCount(orderdetail.getCount() - orderdetail1.getCount());
                    }
                }
            }
        }
        return lasi10;
    }
}
