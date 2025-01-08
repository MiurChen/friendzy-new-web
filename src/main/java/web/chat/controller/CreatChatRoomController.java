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
import core.pojo.chatResult;
import web.chat.pojo.ChatRoom;
import web.chat.service.ChatroomService;
import web.chat.service.ChatroomServiceImpl;
import web.member.pojo.Member;


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
		chatResult<ChatRoom> result = new chatResult<>();
		
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
			ChatRoom chatRoom = chatroomService.getOrCreateChatRoom(currentMember.getMember_no(), otherUserId);
			result.setStatu(true);
			result.setMessage("創建聊天室成功");
			result.setData(chatRoom);
			
			return Response.status(Response.Status.OK)
					.entity(new Gson().toJson(result))
					.build();
		} catch (Exception e) {
			result.setStatu(false);
			result.setMessage("操作失敗:" + e.getMessage());
		}
		return null;
	}

}
