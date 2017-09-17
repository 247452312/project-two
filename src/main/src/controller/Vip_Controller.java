package controller;

import entity.MoneyDetail;
import entity.Ordermain;
import entity.User;
import entity.Vip;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Ordermain_ServiceImpl;
import serviceimpl.Vip_ServiceImpl;
import utils.JsonData1;
import utils.ListAndSearchInfo;
import utils.SeachInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("Vip")
public class Vip_Controller extends Basic_Controller<Vip> {
    @Resource(name = "Vip_ServiceImpl")
    Vip_ServiceImpl service;

    @Resource(name = "Ordermain_ServiceImpl")
    Ordermain_ServiceImpl oservice;


    @RequestMapping("updateStatus")
    public @ResponseBody
    String updateStatus(int id) {
        service.updateStatus(id);
        return "{\"status\":1}";
    }

    @RequestMapping("getVip")
    public @ResponseBody
    List<Vip> getVip(String name, int shopId) {
        SeachInfo sea = new SeachInfo(false);
        sea.setWhere("where name like %" + name + "% and shopid = " + shopId + "");
        List<Vip> list = service.getAll(sea);
        return list;
    }

    @RequestMapping("moneyDetail")
    public @ResponseBody
    List<MoneyDetail> moneyDetail(int id) {
        List<Ordermain> list = oservice.getByAttr(new JsonData1("vipid", id));
        List<MoneyDetail> mlist = new ArrayList<MoneyDetail>();
        for (Ordermain o : list) {
            MoneyDetail md = new MoneyDetail();
            md.setCode(o.getOrdercode());
            md.setType(o.getOrdertype());
            md.setData(o.getCreatedate());
            md.setShopid(o.getShopid());
            md.setAmount(o.getAmount());
            //充钱的
            if (o.getOrdertype() == 1 || o.getOrdertype() == 6) {
                md.setPlus(o.getVipamount());
                md.setReduce(0);
                md.setBalance(1);
                md.setPlus1(1);
                md.setReduce1(0);
                md.setBalance1(1);
            }
            //花钱的
            else if (o.getOrdertype() == 2 || o.getOrdertype() == 5) {
                md.setPlus(0);
                md.setReduce(1);
                md.setBalance(1);

                md.setPlus1(0);
                md.setReduce1(1);
                md.setBalance1(1);
            }


            md.setCjr(o.getUserid().getName());

            mlist.add(md);
        }
        return mlist;
    }


}
