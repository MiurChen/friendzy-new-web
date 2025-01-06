package web.chat.dao;

import java.util.List;

import core.dao.dao;
import web.chat.pojo.Message;

public abstract class MessageDao implements dao<Message>{

	@Override
	public abstract int insert(Message item) throws Exception;

	@Override
	public abstract Message seleteBy(String id) throws Exception;

	@Override
	public abstract List<Message> seleteAll() throws Exception;

}
