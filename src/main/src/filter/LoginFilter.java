package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	String login = "//login.jsp,/User/login,/code,";
	String mark = ".css,.jpg,.js,.png,.woff,.ico,";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		for (String s : mark.split(",")) {
			if((url+",").contains(s+",")) {
				chain.doFilter(request, response);
				System.out.println(url+" 悄悄地路过拦截器");
				return;
			}
		}
		if(login.indexOf(url+",")!=-1){
			chain.doFilter(request, response);
			System.out.println(url+" 悄悄地路过拦截器");
			return;
		}else {
			HttpSession session = req.getSession();
			User u = (User) session.getAttribute("user");
			if(u!=null){
				chain.doFilter(request,response);
				System.out.println(url+" 悄悄地路过拦截器");
				return;
			}
			System.out.println("\n"+url+"没登录就想进入,被拦截\n");
			res.setHeader("Cache-Control", "no-store");
			res.setDateHeader("Expires", 0);
			res.setHeader("Prama", "no-cache");
			PrintWriter out = res.getWriter();
			out.println("<html><script type=\"text/javascript\">window.open ('/login.jsp','_top')</script>");
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
