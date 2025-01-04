package web.chat.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.chat.dao.MessageDao;
import web.chat.pojo.Message;

public class MessageDaoImpl extends MessageDao{
	private DataSource ds;
	
	public MessageDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/example");
	}

	@Override //沒有這功能
	public int update(Message item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override //沒有這功能
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Message item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message seleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> seleteAll() throws Exception {
		String sql = "SELECT * FROM messages";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstms = conn.prepareStatement(sql);
			ResultSet rs = pstms.executeQuery();
		){
			List<Message> messageList= new ArrayList<>();
				while (rs.next()) {
					Message message = new Message();
					message.setMessage_no(rs.getInt("message_no"));
					message.setRoom_no(rs.getInt("room_no"));
					message.setSender_id(rs.getInt("sender_id"));
					message.setMessage(rs.getString("message"));
					message.setSent_time(rs.getTimestamp("sent_time"));
					message.setReceiver_id(rs.getInt("receiver_id"));
					messageList.add(message);
				}
			return messageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
