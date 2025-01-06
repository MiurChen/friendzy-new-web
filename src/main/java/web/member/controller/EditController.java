package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.pojo.Result;
import web.member.pojo.Member;
import web.member.service.MemberService;
import web.member.service.Impl.MemberServiceImpl;

@WebServlet("/rest/member/edit")
public class EditController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
	    Result result = new Result();

	    try {
	        Member member = gson.fromJson(req.getReader(), Member.class);
	        if (member == null) {
	            result.setStatu(false);
	            result.setMessage("會員資料不完整");
	        } else {
	            Member curMember = (Member) req.getSession().getAttribute("member");
	            if (curMember == null) {
	                result.setStatu(false);
	                result.setMessage("用戶未登入");
	            } else {
	            	System.out.print(curMember.getMember_no());
	                member.setEmail(curMember.getEmail());
	                MemberService service = new MemberServiceImpl();

	                String message = null;
	                if (member.getMpassword() != null) {
	                    message = service.savePassword(member);
	                } else if (member.getMember_nick_name() != null) {
	                    message = service.saveNickname(member);
	                } else if (member.getPhone() != null) {
	                	message = service.savePhone(member);
	                }else {
	                    result.setStatu(false);
	                    result.setMessage("未提供更新的資料");
	                }

	                if (message == null) {
	                    if (member.getMpassword() != null) {
	                        curMember.setMpassword(member.getMpassword());
	                    }
	                    if (member.getMember_nick_name() != null) {
	                        curMember.setMember_nick_name(member.getMember_nick_name());
	                    }
	                    if (member.getPhone() != null) {
	                        curMember.setPhone(member.getPhone());
	                    }
	                    req.getSession().setAttribute("member", curMember);

	                    result.setStatu(true);
	                    result.setMessage("更新成功");
	                } else {
	                    result.setStatu(false);
	                    result.setMessage(message);
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    String json = gson.toJson(result);
	    resp.getWriter().write(json);
	}
	    
}
