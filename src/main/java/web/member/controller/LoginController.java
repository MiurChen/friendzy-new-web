package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;

import com.google.gson.Gson;

import core.pojo.Result;
import web.member.pojo.Member;
import web.member.service.MemberService;
import web.member.service.Impl.MemberServiceImpl;

@WebServlet("/member/login")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Member member = gson.fromJson(req.getReader(), Member.class);
		Result result = new Result(); //1.實例化一個Result物件
		if(member == null) {
		//2.設定屬性值(false、"會員資料不完整")
			result.setStatu(false);
			result.setMessage("會員資料不完整");

		}else {
			try {
				MemberService service = new MemberServiceImpl();
				try {
					String message = service.login(member);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(member != null) {
					if(req.getSession(false) != null) {
						req.changeSessionId();
					}
					HttpSession session = req.getSession();
					session.setAttribute("member", member); //把member放入session，容器是"member"
					resp.getWriter().write(gson.toJson(member));
				} else {
					result.setStatu(false);
					result.setMessage("使用者名稱或密碼錯誤");
					String json = gson.toJson(result);
					resp.getWriter().write(json);
				}
				
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

}
