package controller;

import entity.Checkdetail;
import entity.Checkmain;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Checkdetail_ServiceImpl;
import serviceimpl.Checkmain_ServiceImpl;
import serviceimpl.Ordermain_ServiceImpl;
import serviceimpl.Store_ServiceImpl;
import utils.Info;
import utils.JsonData1;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    String insert(Checkmain check, List<Checkdetail> list,HttpSession session) {
        if (check.getCheckcode() == null || check.getCheckdate() == null || check.getCheckname() == null || check.getFexp() == null || check.getShopid() == null || check.getStatus() == null || list.size() == 0)
            return "{\"status\":0}";
        check.setCheckdate(Info.getNow());
        if (check.getStatus() == null)
            check.setStatus(0);
        service.insert(check);
        Checkmain m = service.getNew();
        for (Checkdetail checkdetail : list) {
            checkdetail.setCheckid(m);

            cservice.insert(checkdetail);
        }
        return "{\"status\":1}";
    }

    @RequestMapping("aotoOrder")
    public @ResponseBody
    String aotoOrder(Checkmain check, List<Checkdetail> list, HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u == null) return "{\"status\":2}";
        service.insert(check);
        service.autocreate(check.getId());
        return "{\"status\":1}";
    }




    @RequestMapping("update")
    public @ResponseBody String update(Integer mainId){
        Checkmain main = service.getById(mainId);
        List<Checkdetail> list = cservice.getByAttr(new JsonData1("checkid",mainId));
        StringBuilder sb = new StringBuilder("{");
         sb.append(JSONObject.fromObject(main));
         sb.append(",");
         sb.append(JSONArray.fromObject(list));
         sb.append("}");
        return sb.toString();
    }
}
