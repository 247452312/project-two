package controller;

import entity.Checkdetail;
import entity.Checkmain;
import entity.User;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Checkdetail_ServiceImpl;
import serviceimpl.Checkmain_ServiceImpl;
import serviceimpl.Ordermain_ServiceImpl;
import serviceimpl.Store_ServiceImpl;
import utils.Info;
import utils.JsonData;
import utils.JsonData1;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "Checkmain")
public class Checkmain_Controller extends Basic_Controller<Checkmain> {
    @Resource(name = "Checkmain_ServiceImpl")
    Checkmain_ServiceImpl service;

    @Resource(name = "Checkdetail_ServiceImpl")
    Checkdetail_ServiceImpl cservice;

    @Resource(name = "Store_ServiceImpl")
    Store_ServiceImpl sservice;

    @Resource(name = "Ordermain_ServiceImpl")
    Ordermain_ServiceImpl oservice;

    @RequestMapping("insertMain")
    public @ResponseBody
    JsonData insertMain(Checkmain check) {
        if (check.getCheckcode() == null || check.getCheckdate() == null
                || check.getCheckname() == null || check.getShopid() == null
                || check.getStatus() == null)
            return new JsonData(-1, "数据不全");
        check.setCheckdate(Info.getNow());
        check.setStatus(0);
        service.insert(check);
        Checkmain m = service.getNew();
        return new JsonData(1, m.getId() + "");
    }

    @RequestMapping("autoOrder")
    public @ResponseBody JsonData autoOrder(int id){
        service.autocreate(id);
        return new JsonData(1);
    }


    @RequestMapping("update1")
    public @ResponseBody
    JsonData update1(Checkmain check) {
        if (check.getCheckcode() == null || check.getCheckdate() == null
        || check.getCheckname() == null || check.getShopid() == null)
            return new JsonData(-1, "数据不全");
        check.setCheckdate(Info.getNow());
        if (check.getStatus() == null)
            check.setStatus(0);
        service.update(check);
        return new JsonData(1,""+service.getNew().getId());
    }
    @RequestMapping("update2")
    public @ResponseBody
    JsonData update2(Checkdetail detail) {
        if(detail.getId()!=null){
            cservice.update(detail);
        }else{
            cservice.insert(detail);
        }
        return new JsonData(1);
    }

}
