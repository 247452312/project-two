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

    @RequestMapping("insert")
    public @ResponseBody
    JsonData insert(Checkmain check, ArrayList<Checkdetail> list, HttpSession session) {
        System.out.println("Checkcode:"+check.getCheckcode());
        System.out.println("Checkdate:"+check.getCheckdate());
        System.out.println("Checkname:"+check.getCheckname());
        System.out.println("Shopid:"+check.getShopid());
        System.out.println("Status:"+check.getStatus());
        System.out.println("list:"+list.size());
        if (check.getCheckcode() == null || check.getCheckdate() == null
        || check.getCheckname() == null || check.getShopid() == null
        || check.getStatus() == null || list.size() == 0)
            return new JsonData(-1, "数据不全");
        check.setCheckdate(Info.getNow());
        if (check.getStatus() == null)
            check.setStatus(0);
        service.insert(check);
        Checkmain m = service.getNew();
        for (Checkdetail checkdetail : list) {
            checkdetail.setCheckid(m);
            cservice.insert(checkdetail);
        }
        return new JsonData(1);//返回成功信息和新的id
    }

    @RequestMapping("aotoOrder")
    public @ResponseBody
    JsonData aotoOrder(Checkmain check, ArrayList<Checkdetail> list, HttpSession session) {
        if (check.getId() != null) {
            service.autocreate(check.getId());
            return new JsonData(1);
        } else {
            JsonData temp = insert(check, list, session);
            if (temp.getStatus() == 1) {
                int id = service.getNew().getId();
                service.autocreate(id);
            }
            return temp;
        }
    }

    public String updat(int id, ModelMap m) {
        m.put("object", service.getById(id));
        m.put("cmd", "update");
        return getTypeName() + "/add";
    }

    @RequestMapping("update")
    public @ResponseBody
    JsonData update(Checkmain check, ArrayList<Checkdetail> list, HttpSession session) {
        if (check.getCheckcode() == null || check.getCheckdate() == null
        || check.getCheckname() == null || check.getShopid() == null
        || check.getStatus() == null || list.size() == 0)
            return new JsonData(-1, "数据不全");
        check.setCheckdate(Info.getNow());
        if (check.getStatus() == null)
            check.setStatus(0);
        service.update(check);
        for (Checkdetail checkdetail : list) {
            checkdetail.setCheckid(check);
            if(cservice.getById(checkdetail.getId())==null) cservice.insert(checkdetail);
            else cservice.update(checkdetail);
        }
        return new JsonData(1);
    }
}
