package web.chat.service;

import java.util.List;

import web.chat.pojo.ChatRoom;

public interface ChatroomService {

	List<ChatRoom> getChatRoomsByEmail(String email) throws Exception;
	
	ChatRoom getOrCreateChatRoom (Integer room_user_one, Integer room_user_two) throws Exception;
}
