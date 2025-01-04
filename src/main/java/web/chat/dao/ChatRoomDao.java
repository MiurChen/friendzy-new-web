package web.chat.dao;

import java.util.List;

import core.dao.dao;
import web.chat.pojo.ChatRoom;


public abstract class ChatRoomDao implements dao<ChatRoom>{

	@Override
	public abstract int insert(ChatRoom item) throws Exception;

	@Override
	public abstract ChatRoom seleteBy(String id) throws Exception;

	@Override
	public abstract List<ChatRoom> seleteAll() throws Exception;
}
