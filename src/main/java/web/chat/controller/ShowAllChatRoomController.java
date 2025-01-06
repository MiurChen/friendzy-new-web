package web.chat.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import web.chat.pojo.ChatRoom;
import web.chat.service.ChatroomService;
import web.chat.service.ChatroomServiceImpl;
import web.member.pojo.Member;

@Path("/chatrooms")
public class ShowAllChatRoomController {
	private ChatroomService chatroomService;
	
	@Context
	private HttpServletRequest request;
	
	public ShowAllChatRoomController() throws NamingException {
		chatroomService = new ChatroomServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChatRooms() {
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			System.out.println("session is Missing");
	        return Response.status(Response.Status.BAD_REQUEST)
	                .entity("Session is missing.")
	                .build();
	    }
		System.out.println(session.getId());
		System.out.println(session.getCreationTime());
		Member member = (Member) session.getAttribute("member");
		System.out.println(member.getEmail());
		System.out.println(member.getMember_name());
		System.out.println(member.getMember_no());
		
		if(member != null) {
			try {
				List<ChatRoom> chatRooms = chatroomService.getChatRoomsByEmail(member.getEmail());
				String jsonResponse = new Gson().toJson(chatRooms);
				return Response.ok(jsonResponse).build(); //返回200，帶入聊天室資料
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while fetching chat rooms.").build();
			}
		}else {
			System.out.println("User is not logged");
			return Response.status(Response.Status.UNAUTHORIZED).entity("User is not logged in.").build();
		}
	}
}
