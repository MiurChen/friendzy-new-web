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
	public ChatRoom getOrCreateChatRoom(Integer room_user_one, Integer room_user_two) throws Exception {
		
		//查詢是否存在該聊天室
		ChatRoom existingRoom = chatRoomDao.findExistingRoom(room_user_one, room_user_two);
		if(existingRoom != null) {
			return existingRoom;
		}
		
		//不存在就設定
		ChatRoom newRoom = new ChatRoom();
		newRoom.setRoom_user_one(room_user_one);
		newRoom.setRoom_user_two(room_user_two);
		
		int result = chatRoomDao.insert(newRoom); //設定後創建聊天室
		if(result > 0) {
			return chatRoomDao.findExistingRoom(room_user_one, room_user_two); //把創建的聊天室訊息帶回
		}
		throw new Exception("創建聊天室失敗");
	}
}
