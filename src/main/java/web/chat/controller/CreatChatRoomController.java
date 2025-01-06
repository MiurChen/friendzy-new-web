package web.chat.controller;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import core.pojo.Result;
import web.chat.service.ChatroomService;
import web.chat.service.ChatroomServiceImpl;
import web.member.pojo.Member;


//FIXME:判斷是否存在聊天室
@Path("/chatroom")
public class CreatChatRoomController {
	private ChatroomService chatroomService;
	
	@Context
	HttpServletRequest request;
	
	public CreatChatRoomController() throws NamingException {
		chatroomService = new ChatroomServiceImpl();
	}
	
	@POST
	@Path("/create/{otherUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creatChatRoom(@PathParam ("otherUserId") Integer otherUserId) {
		Result result = new Result();
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			result.setStatu(false);
			result.setMessage("尚未登入");
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(new Gson().toJson(result))
					.build();
		}
		
		Member currentMember = (Member) session.getAttribute("member");
		if(currentMember == null) {
			result.setStatu(false);
			result.setMessage("登入狀態已失效，請重新登入");
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(new Gson().toJson(result))
					.build();
		}
		try {
			chatroomService.createChatRoom(currentMember.getMember_no(), otherUserId);
			result.setStatu(true);
			result.setMessage("創建聊天室成功");
			return Response.status(Response.Status.CREATED)
					.entity(new Gson().toJson(result))
					.build();
		} catch (Exception e) {
			result.setStatu(false);
			if (e.getMessage().contains("Chat room already exists")) {
                result.setMessage("聊天室已存在");
                return Response.status(Response.Status.CONFLICT)
                        .entity(new Gson().toJson(result))
                        .build();
            } else {
                result.setMessage("創建聊天室時發生錯誤: " + e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new Gson().toJson(result))
                        .build();
            }
		}
	}

}
