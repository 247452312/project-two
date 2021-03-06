package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.User_ServiceImpl;
import utils.JsonData;
import utils.JsonData1;
import utils.MD5Util;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Controller
@RequestMapping("User")
public class User_Controller extends Basic_Controller<User> {
    @Resource(name = "User_ServiceImpl")
    User_ServiceImpl service;


    @RequestMapping("updateStatus")
    public @ResponseBody
    JsonData updateStatus(int id, int status) {
        service.updateAttr(new JsonData1("status", id, status));
        return new JsonData(1);
    }

    /**
     * 登陆
     *
     * @param name    用户名
     * @param pass    密码
     * @param code    验证码
     * @param box     是否记住密码
     * @param session session
     * @return 0->客户端出错(未加载code) 1->成功 2->验证吗错误 3->用户名或密码不存在
     */
    @RequestMapping("login")
    public @ResponseBody
    JsonData login(String name, String pass, String code, int box, HttpServletResponse res, HttpSession session) {
        String str = session.getAttribute("code").toString();
        if (str == null || str.equals("")) return new JsonData(-1, "客户端错误");
        if (!str.equals(code)) return new JsonData(-1, "验证码错误");
        User u = service.login(name, pass);
        if (u == null) return new JsonData(-1, "用户名或密码不存在");
        session.setAttribute("user", u);
        if (box == 1) {
            Cookie c1 = new Cookie("username", name);
            c1.setMaxAge(60 * 60 * 24 * 14);
            c1.setPath("/");
            res.addCookie(c1);
        }
        return new JsonData(1);

    }

    @RequestMapping("cancel")
    public String cancellogin(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login.jsp";

    }

    /**
     * 返回值为0:没有登录,返回值为1:修改成功.返回值为2:新密码两次不一致,返回值为3:新密码与旧密码相同,返回值为4,旧密码不正确
     *
     * @param session session
     * @return status
     */
    @RequestMapping("updatePass")
    public @ResponseBody
    JsonData updatePass(HttpSession session, HttpServletRequest req) {
        String oldPass = req.getParameter("oldPass"),
                newPass = req.getParameter("newPass"),
                newPassAgain = req.getParameter("newPassAgain");
        User u = (User) session.getAttribute("user");
        if (u == null) return new JsonData(0, "没有登录");
        if (!u.getPass().equals(MD5Util.MD5(oldPass))) return new JsonData(4, "旧密码不正确");
        if (newPass.equals(oldPass)) return new JsonData(3, "新密码与旧密码相同");
        if (!newPass.equals(newPassAgain)) return new JsonData(2, "新密码两次不一致");
        service.updateAttr(new JsonData1("pass", u.getId(), MD5Util.MD5(newPass)));
        u.setPass(MD5Util.MD5(newPass));
        session.setAttribute("user", u);
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("userpass")) {
                cookie.setValue(newPass);
            }
        }
        return new JsonData(1);
    }

    @RequestMapping("resetPass")
    public @ResponseBody
    JsonData resetPass(int id) {
        service.updateAttr(new JsonData1("pass", id, MD5Util.MD5("123")));
        return new JsonData(1);
    }


    @RequestMapping("ajax1")
    public @ResponseBody
    JsonData ajax(@RequestParam("cmd") String cmd, User user, HttpSession session) {
        if (user.getUserid() == null) user.setUserid(new User(0));
        if (cmd.equals("update")) {
            service.update(user);
        } else if (cmd.equals("add")) {
            service.insert(user);
        }
        return new JsonData(1, service.getNew().toString());
    }
}
