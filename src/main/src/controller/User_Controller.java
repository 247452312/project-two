package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.User_ServiceImpl;
import utils.JsonData;
import utils.JsonData1;
import utils.ListAndSearchInfo;
import utils.MD5Util;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param username 用户名
     * @param userpass 密码
     * @param code     验证码
     * @param box      是否记住密码
     * @param session  session
     * @return 0->客户端出错(未加载code) 1->成功 2->验证吗错误 3->用户名或密码不存在
     */
    @RequestMapping("login")
    public @ResponseBody
    JsonData login(String username, String userpass, String code, int box, HttpServletResponse res, HttpSession session) {
        String str = session.getAttribute("code").toString();
        if (str == null || str.equals("")) return new JsonData(0, "客户端错误");
        if (!str.equals(code)) return new JsonData(2, "验证码错误");
        User u = service.login(username, userpass);
        if (u == null) return new JsonData(3, "用户名或密码不存在");
        session.setAttribute("user", u);
        if (box == 1) {
            Cookie c1 = new Cookie("username", username);
            Cookie c2 = new Cookie("userpass", userpass);
            res.addCookie(c1);
            res.addCookie(c2);
        }
        return new JsonData(1);

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
    JsonData updatePass(String oldPass, String newPass, String newPassAgain, HttpSession session,HttpServletRequest req) {
        User u = (User) session.getAttribute("user");
        if (u == null) return new JsonData(0,"没有登录");
        if (!u.getPass().equals(MD5Util.getMD5(oldPass))) return new JsonData(4,"旧密码不正确");
        if (newPass.equals(oldPass)) return new JsonData(3,"新密码与旧密码相同");
        if (!newPass.equals(newPassAgain)) return new JsonData(2,"新密码两次不一致");
        service.updateAttr(new JsonData1("pass", u.getId(), MD5Util.getMD5(newPass)));
        for (Cookie cookie : req.getCookies()) {
            if(cookie.getName().equals("userpass")){cookie.setValue(newPass);}
        }
        return new JsonData(1);
    }
}
