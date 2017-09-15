package servlet;

import utils.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class code extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet { 
    static final long serialVersionUID = 1L; 
   
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
           
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        HttpSession session = request.getSession(true); 
        session.removeAttribute("code");
        session.setAttribute("code", verifyCode.toLowerCase()); 
        session.setMaxInactiveInterval(60*60*2);
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
   
    } 
}