package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.member.pojo.Member;

@WebServlet("/rest/member/find")
public class FindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Member member = (Member) req.getSession().getAttribute("member");
		//3.轉成JSON格式的字串
		Gson gson = new Gson();
		String json = gson.toJson(member);
		//4.寫出至前端
		resp.getWriter().write(json);
	}
	
}
