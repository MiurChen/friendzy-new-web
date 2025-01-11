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
	    	 // 從請求中解析 JSON 並轉換為 Member 物件
	        Member member = gson.fromJson(req.getReader(), Member.class);

	        if (member == null || member.getEmail() == null) {
	            result.setStatu(false);
	            result.setMessage("會員資料不完整或缺少 Email");
	        } else {
	            MemberService service = new MemberServiceImpl();

	            // 使用 Email 查找會員資料
	            Member curMember = service.findByEmail(member.getEmail());

	            if (curMember == null) {
	                result.setStatu(false);
	                result.setMessage("用戶不存在");
	            } else {
	                System.out.println("查找到的會員編號: " + curMember.getMember_no());

	                // 更新欄位的邏輯
	                boolean isUpdated = false;

	                if (member.getMpassword() != null) {
	                    String message = service.savePassword(member);	      
	                    if (message == null) {
	                        curMember.setMpassword(member.getMpassword());
	                        System.out.println(member.getMpassword());
	                        isUpdated = true;
	                    } else {
	                        result.setStatu(false);
	                        result.setMessage(message);
	                        return;
	                    }
	                }

	                if (member.getMember_nick_name() != null) {
	                    String message = service.saveNickname(member);	                    
	                    if (message == null) {
	                        curMember.setMember_nick_name(member.getMember_nick_name());
	                        isUpdated = true;	                      
	                    } else {
	                        result.setStatu(false);
	                        result.setMessage(message);
	                        return;
	                    }
	                }

	                if (member.getPhone() != null) {
	                    String message = service.savePhone(member);
	                    if (message == null) {
	                        curMember.setPhone(member.getPhone());
	                        isUpdated = true;
	                    } else {
	                        result.setStatu(false);
	                        result.setMessage(message);
	                        return;
	                    }
	                }
	                
	                if (member.getIntroduction() != null) {
	                    String message = service.saveIntroduction(member);
	                    System.out.println(member.getIntroduction());
	                    if (message == null) {
	                        curMember.setIntroduction(member.getIntroduction());
	                        System.out.println(curMember.getIntroduction());
	                        isUpdated = true;
	                    } else {
	                        result.setStatu(false);
	                        result.setMessage(message);
	                        return;
	                    }
	                }

	                if (isUpdated) {
	                    result.setStatu(true);
	                    result.setMessage("更新成功");
	                } else {
	                    result.setStatu(false);
	                    result.setMessage("未提供更新的資料");
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
