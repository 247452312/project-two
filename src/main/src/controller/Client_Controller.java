package controller;

import entity.Client;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Client_ServiceImpl;
import utils.JsonData1;
import utils.ListAndSearchInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("Client")
public class Client_Controller extends Basic_Controller<Client> {
    @Resource(name = "Client_ServiceImpl")
    Client_ServiceImpl service;


    public @ResponseBody
    ListAndSearchInfo edit(String cmd, Client s, ModelMap m, HttpSession session) {
        if (cmd.equals("add"))
            s.setUserid((User) session.getAttribute("user"));
        return super.edit(cmd, s, m, session);
    }

    @RequestMapping("test")
    public @ResponseBody
    JsonData1 test() {
        return new JsonData1();
    }


    @RequestMapping("getClient")
    public @ResponseBody
    Client getClient(int id) {
        return service.getById(id);
    }
}
