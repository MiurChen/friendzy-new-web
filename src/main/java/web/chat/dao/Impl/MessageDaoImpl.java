package web.chat.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
	public int insert(Message message) throws Exception {
		String sql = "INSERT INTO messages (room_no, sender_id, message, sent_time, receiver_id, sender_token, receiver_token) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection();
			PreparedStatement pstms = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		){
			pstms.setInt(1, message.getRoom_no());
			pstms.setInt(2, message.getSender_id());
			pstms.setString(3, message.getMessage());
			pstms.setTimestamp(4, message.getSent_time());
			pstms.setInt(5, message.getReceiver_id());
			pstms.setString(6, message.getSender_token());
			pstms.setString(7, message.getReceiver_token());
			
			int affectedRow = pstms.executeUpdate();
			
			if(affectedRow > 0) {
				try(ResultSet generatedKeys = pstms.getGeneratedKeys()) {
					if(generatedKeys.next()) {
						message.setMessage_no(generatedKeys.getInt(1));
						return message.getMessage_no();
					}
				} 
			}
			return 0;
		} catch (Exception e) {
			 e.printStackTrace();
	            throw new Exception("Error inserting message.");
		}
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
			throw new Exception("Error fetching all messages.");
		}
	}

	@Override
	public List<Message> selectMessagesByRoom(int roomNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
