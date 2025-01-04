package web.chat.service;

import java.util.List;

import web.chat.pojo.ChatRoom;

public interface ChatroomService {

	List<ChatRoom> getChatRoomsByEmail(String email) throws Exception;
	
	void createChatRoom(Integer currentUserId, Integer otherUserId) throws Exception;
}
