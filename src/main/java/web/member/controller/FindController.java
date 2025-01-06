package web.member.controller;

import java.io.IOException;

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

@WebServlet("/rest/member/find")
public class FindController extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        Gson gson = new Gson();
	        Result result = new Result();

	        try {
	            // 獲取當前 session
	            HttpSession session = req.getSession(false);
	            System.out.println("doGet");
	            if (session != null) {
	                // 從 session 中獲取部分會員資料
	                Member member = (Member) session.getAttribute("member");
		            System.out.println(member.getEmail());

	                if (member != null && member.getEmail() != null) {
	                    // 根據 email 從資料庫查詢完整會員資料
	                    MemberService service = new MemberServiceImpl();
	                    Member completeMember = service.findByEmail(member.getEmail());
	    	            System.out.println("completeMember: "+completeMember.getEmail());
	                    if (completeMember != null) {
	        	            System.out.println("completeMember != null");
	                        result.setData(completeMember); // 返回完整會員資料
	                    } else {
	        	            System.out.println("completeMember == null");
	                        result.setStatu(false);
	                        result.setMessage("查無該用戶資料");
	                    }
	                } else {
        	            System.out.println("用戶尚未登入");
	                    result.setStatu(false);
	                    result.setMessage("用戶尚未登入");
	                }
	            } else {
    	            System.out.println("無效的 session");
	                result.setStatu(false);
	                result.setMessage("無效的 session");
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	            result.setStatu(false);
	            result.setMessage("系統錯誤，請稍後再試");
	        }

	        // 返回 JSON 格式結果
	        String json = gson.toJson(result);
	        resp.setContentType("application/json");
	        resp.getWriter().write(json);
	    }
	
}
