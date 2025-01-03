package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import core.pojo.Result;
import web.member.pojo.Member;
import web.member.service.MemberService;
import web.member.service.Impl.MemberServiceImpl;

@WebServlet("/rest/member/login")
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
			resp.getWriter().write(gson.toJson(result));
		}else {
			try {
				MemberService service = new MemberServiceImpl();
				String message = service.login(member);
				
				if(message == null) {
					if(req.getSession(false) != null) {
						req.changeSessionId();
					}
					HttpSession session = req.getSession();
					session.setAttribute("member", member); //把member放入session，容器是"member"
//					resp.getWriter().write(gson.toJson(member));
					
					result.setStatu(true);
					result.setMessage("登入成功");
				} else {
					result.setStatu(false);
					result.setMessage("使用者名稱或密碼錯誤");
//					String json = gson.toJson(result);
//					resp.getWriter().write(json);
				}
				
			} catch (NamingException e) {
				e.printStackTrace();
				result.setStatu(false);
	            result.setMessage("系統錯誤，請稍後再試");
//	            resp.getWriter().write(gson.toJson(result));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String json = gson.toJson(result);
		resp.getWriter().write(json);
	}

}
