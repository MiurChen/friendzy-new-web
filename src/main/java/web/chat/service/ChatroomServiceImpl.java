package web.chat.service;

import java.util.List;

import javax.naming.NamingException;

import web.chat.dao.ChatRoomDao;
import web.chat.dao.Impl.ChatRoomDaoImpl;
import web.chat.pojo.ChatRoom;

public class ChatroomServiceImpl implements ChatroomService{
	private ChatRoomDao chatRoomDao;
	
	public ChatroomServiceImpl() throws NamingException {
		chatRoomDao = new ChatRoomDaoImpl();
	}

	//查聊天室
	@Override
	public List<ChatRoom> getChatRoomsByEmail(String email) throws Exception {
		return chatRoomDao.selectRoomsByEmail(email);
	}

	//創聊天室
	@Override
	public void createChatRoom(Integer room_user_one, Integer room_user_two) throws Exception {
		boolean exists = chatRoomDao.doesChatRoomExist(room_user_one, room_user_two);
		
		if (exists) {
			throw new Exception("Chat room already exists."); 
		}else {
			ChatRoom chatRoom = new ChatRoom();
			chatRoom.setRoom_user_one(room_user_one);
			chatRoom.setRoom_user_two(room_user_two);
			chatRoomDao.insert(chatRoom);
		}
		
	}
	
	
	

}
