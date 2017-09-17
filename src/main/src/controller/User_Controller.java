package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.User_ServiceImpl;
import utils.JsonData1;
import utils.ListAndSearchInfo;
import utils.MD5Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("User")
public class User_Controller extends Basic_Controller<User> {
    @Resource(name = "User_ServiceImpl")
    User_ServiceImpl service;


    @RequestMapping("updateStatus")
    public @ResponseBody
    String updateStatus(int id, int status) {
        service.updateAttr(new JsonData1("status", id, status));
        return "{\"status\":1}";
    }

    /**
     * 登陆
     *
     * @param user    用户名
     * @param pass    密码
     * @param code    验证码
     * @param session session
     * @return 0->客户端出错(未加载code) 1->成功 2->验证吗错误 3->用户名或密码不存在
     */
    @RequestMapping("login")
    public @ResponseBody
    String login(String user, String pass, String code, HttpSession session) {

        User u = service.getById(1);
        session.setAttribute("user",u);
//        String str = session.getAttribute("code").toString();
//        if (str == null || str.equals("")) return "{\"status\":0}";
//        if (!str.equals(code)) return "{\"status\":2}";
//        User u = service.login(user, pass);
//        if (u == null) return "{\"status\":3}";
//        session.setAttribute("user", u);
        return "{\"status\":1}";

    }

    /**
     * 返回值为0:没有登录,返回值为1:修改成功.返回值为2:新密码两次不一致,返回值为3:新密码与旧密码相同,返回值为4,旧密码不正确
     *
     * @param oldPass      旧密码
     * @param newPass      新密码
     * @param newPassAgain 新密码第二次
     * @param session      session
     * @return status
     */
    @RequestMapping("updatePass")
    public @ResponseBody
    String updatePass(String oldPass, String newPass, String newPassAgain, HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u == null) return "{\"status\":0}";
        if (!u.getPass().equals(MD5Util.getMD5(oldPass))) return "{\"status\":4}";
        if (newPass.equals(oldPass)) return "{\"status\":3}";
        if (!newPass.equals(newPassAgain)) return "{\"status\":2}";
        service.updateAttr(new JsonData1("pass", u.getId(), MD5Util.getMD5(newPass)));
        return "{\"status\":1}";
    }
}
