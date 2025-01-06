package web.chat.dao;

import java.util.List;

import core.dao.dao;
import web.chat.pojo.ChatRoom;


public abstract class ChatRoomDao implements dao<ChatRoom>{

	@Override
	public abstract int insert(ChatRoom item) throws Exception;

	@Override
	public abstract ChatRoom seleteBy(String id) throws Exception;

	public List<ChatRoom> selectRoomsByEmail(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean doesChatRoomExist(Integer room_user_one, Integer room_user_two) throws Exception{
		return false;
	}
}
