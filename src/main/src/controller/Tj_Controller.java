package controller;

import entity.tj.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Tj_ServiceImpl;
import utils.SeachInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Tj")
public class Tj_Controller extends Basic_Controller<Tj> {
    @Resource(name = "Tj_ServiceImpl")
    Tj_ServiceImpl service;

    @RequestMapping("selectAll")
    public @ResponseBody
    List<Tj_All> selectAll(@RequestParam(value = "sdate", defaultValue = "") String sdate,
                           @RequestParam(value = "ddate", defaultValue = "") String ddate,
                           @RequestParam(value = "trem", defaultValue = "") int[] trem,
                           @RequestParam(value = "compare", defaultValue = "") int[] compare,
                           @RequestParam(value = "text", defaultValue = "") String[] text,
                           @RequestParam(value = "join", defaultValue = "") int[] join,
                           @RequestParam(value = "where", defaultValue = "") String where) {
        return service.callAll(sdate, ddate, trem, compare, text, join, where);
    }

    @RequestMapping("selectProduct")
    public @ResponseBody
    List<Tj_Product> selectProduct(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, @RequestParam(value = "where", defaultValue = "") String where) {
        return service.callproduct(sdate, ddate, trem, compare, text, join, where);
    }

    @RequestMapping("selectShop")
    public @ResponseBody
    List<Tj_Shop> selectShop(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, @RequestParam(value = "where", defaultValue = "") String where) {
        return service.callShop(sdate, ddate, trem, compare, text, join, where);
    }

    @RequestMapping("selectUser")
    public @ResponseBody
    List<Tj_User> selectUser(String sdate, String ddate, int[] trem, int[] compare, String[] text, int[] join, @RequestParam(value = "where", defaultValue = "") String where) {
        List<Tj_User> list = service.callUser(sdate, ddate, trem, compare, text, join, where);
        return list;
    }

    @RequestMapping("selectVip")
    public @ResponseBody
    List<Tj_Vip> selectVip(@RequestParam(value = "sdate", defaultValue = "") String sdate,
                           @RequestParam(value = "ddate", defaultValue = "") String ddate,
                           @RequestParam(value = "trem", defaultValue = "") int[] trem,
                           @RequestParam(value = "compare", defaultValue = "") int[] compare,
                           @RequestParam(value = "text", defaultValue = "") String[] text,
                           @RequestParam(value = "join", defaultValue = "") int[] join,
                           @RequestParam(value = "where", defaultValue = "") String where) {
        return service.callVip(sdate, ddate, trem, compare, text, join, where);
    }

    @RequestMapping("selectVipMx")
    public @ResponseBody
    List<Tj_VipMx> selectVipMx(Integer id) {
        return service.callVipMx(id);
    }

}
