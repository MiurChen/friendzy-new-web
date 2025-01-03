package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import core.pojo.Result;
import web.member.pojo.Member;
import web.member.service.MemberService;
import web.member.service.Impl.MemberServiceImpl;

@Path("/member/register")
@WebServlet("/rest/member/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Member member = gson.fromJson(req.getReader(), Member.class);
		Result result = new Result();

		if (member == null) {
			result.setStatu(false);
			result.setMessage("註冊資料不可為空");
		} else {
			member.setMember_status(true);
			member.setCompanion_comment(0);
			member.setCompanion_score(0);
			member.setCustmer_comment(0);
			member.setCustmer_score(0);
			member.setRegistration_time(new java.sql.Timestamp(System.currentTimeMillis()));

			try {
				MemberService service = new MemberServiceImpl();
				String message = service.register(member);
				result.setStatu(message == null);
				result.setMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
				result.setStatu(false);
				result.setMessage("註冊過程中出現錯誤，請稍後再試");
			}
		}
		String json = gson.toJson(result);
		resp.getWriter().write(json);
	}

}
