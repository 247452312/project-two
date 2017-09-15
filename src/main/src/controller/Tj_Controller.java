package controller;

import entity.tj.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Tj_ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("tj")
public class Tj_Controller {
    @Resource(name = "Tj_ServiceImpl")
    Tj_ServiceImpl service;

    @RequestMapping("selectAll")
    public @ResponseBody
    List<Tj_All> selectAll(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join){
        return service.callAll(sdate,ddate,trem,compare,text,join);
    }

    @RequestMapping("selectProduct")
    public @ResponseBody List<Tj_Product> selectProduct(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join){
        return service.callproduct(sdate,ddate,trem,compare,text,join);
    }

    @RequestMapping("selectShop")
    public @ResponseBody List<Tj_Shop> selectShop(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join){
        return service.callShop(sdate,ddate,trem,compare,text,join);
    }

    @RequestMapping("selectUser")
    public @ResponseBody List<Tj_User> selectUser(@RequestParam(defaultValue = "") String sdate,@RequestParam(defaultValue = "")  String ddate, int[] trem, int[] compare, String[] text, int[] join){
        return service.callUser(sdate,ddate,trem,compare,text,join);
    }

    @RequestMapping("selectVip")
    public @ResponseBody List<Tj_Vip> selectVip(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join){
        return service.callVip(sdate,ddate,trem,compare,text,join);
    }

    @RequestMapping("selectVipMx")
    public @ResponseBody List<Tj_VipMx> selectVipMx(int id){
        return service.callVipMx(id);
    }

}
