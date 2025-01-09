package web.chat.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.chat.dao.ChatRoomDao;
import web.chat.pojo.ChatRoom;
import web.member.dao.MemberDao;
import web.member.dao.Impl.MemberDaoImpl;
import web.member.pojo.Member;

public class ChatRoomDaoImpl extends ChatRoomDao{
	private DataSource ds;

	public ChatRoomDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}
	
	@Override
	public int insert(ChatRoom chatroom) throws Exception {
		String sql = "INSERT INTO chat_room (room_user_one, room_user_two) VALUES (?, ?)";
		
		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, chatroom.getRoom_user_one());
			pstmt.setInt(2, chatroom.getRoom_user_two());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()
					){
					if(generatedKeys.next()
						){
					chatroom.setRoom_no(generatedKeys.getInt(1));
					}
				} 
			}
				return result;
			}catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error while inserting new chat room.");
		}
	}
	
	@Override
	public List<ChatRoom> selectRoomsByEmail(String email) throws Exception {
		MemberDao memberDao = new MemberDaoImpl();
		Member member = memberDao.seleteBy(email);
		
		if(member == null) {
			throw new Exception("No member found with the provided email.");
		}
		
		Integer memberNo = member.getMember_no();
		
		String sql = "SELECT c.room_no, c.room_user_one, c.room_user_two, "
	               + "CASE "
	               + "    WHEN c.room_user_one = ? THEN m2.member_name "
	               + "    WHEN c.room_user_two = ? THEN m1.member_name "
	               + "END AS other_user_name "
	               + "FROM chat_room c "
	               + "LEFT JOIN member_info m1 ON c.room_user_one = m1.member_no "
	               + "LEFT JOIN member_info m2 ON c.room_user_two = m2.member_no "
	               + "WHERE c.room_user_one = ? OR c.room_user_two = ?";
		
		try (Connection conn = ds.getConnection(); 
			PreparedStatement pstms = conn.prepareStatement(sql);
		){
			pstms.setInt(1, memberNo);
			pstms.setInt(2, memberNo);
			pstms.setInt(3, memberNo);
			pstms.setInt(4, memberNo);
			try (ResultSet rs = pstms.executeQuery()){
				List<ChatRoom> chatRooms = new ArrayList<>();
				while (rs.next()) {
					ChatRoom chatroom = new ChatRoom();
					chatroom.setRoom_no(rs.getInt("room_no"));
					chatroom.setRoom_user_one(rs.getInt("room_user_one"));
					chatroom.setRoom_user_two(rs.getInt("room_user_two"));
					chatroom.setOtherUserName(rs.getString("other_user_name"));	
					chatRooms.add(chatroom);
				}
				return chatRooms;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while fetching chat rooms.");
		}
	}

	public boolean doesChatRoomExist(Integer room_user_one, Integer room_user_two) throws Exception{
		String sql = "SELECT COUNT(*) FROM chat_room WHERE (room_user_one = ? AND room_user_two = ?) "
	               + "OR (room_user_one = ? AND room_user_two = ?)";
		try (Connection conn = ds.getConnection(); 
			PreparedStatement pstms = conn.prepareStatement(sql);
		){
			pstms.setInt(1, room_user_one);
			pstms.setInt(2, room_user_two);
			pstms.setInt(3, room_user_two);
			pstms.setInt(4, room_user_one);
			try (ResultSet rs = pstms.executeQuery()) {
				if (rs.next() && rs.getInt(1)>0) {
					return true;
				}else {
					return false;}
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error while checking if chat room exists." + e.getMessage(), e);
			}
    }

	@Override
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<ChatRoom> seleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatRoom seleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ChatRoom item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public ChatRoom findExistingRoom(Integer room_user_one, Integer room_user_two) throws Exception{
		String sql = "SELECT c.room_no, c.room_user_one, c.room_user_two, " +
                	"CASE " +
                	"WHEN c.room_user_one = ? THEN m2.member_name " +
                	"WHEN c.room_user_two = ? THEN m1.member_name " +
                	"END AS other_user_name " +
                	"FROM chat_room c " +
                	"LEFT JOIN member_info m1 ON c.room_user_one = m1.member_no " +
                	"LEFT JOIN member_info m2 ON c.room_user_two = m2.member_no " +
                	"WHERE (c.room_user_one = ? AND c.room_user_two = ?) " +
                	"OR (c.room_user_one = ? AND c.room_user_two = ?) " +
                	"LIMIT 1";
		try (Connection conn = ds.getConnection(); 
				PreparedStatement pstms = conn.prepareStatement(sql);
			){
				pstms.setInt(1, room_user_one);
				pstms.setInt(2, room_user_one);
				pstms.setInt(3, room_user_one);
				pstms.setInt(4, room_user_two);
				pstms.setInt(5, room_user_two);
				pstms.setInt(6, room_user_one);
				
				try (ResultSet rs = pstms.executeQuery()) {
					if (rs.next()) {
						ChatRoom chatRoom = new ChatRoom();
						chatRoom.setRoom_no(rs.getInt("room_no"));
						chatRoom.setRoom_user_one(rs.getInt("Room_user_one"));
						chatRoom.setRoom_user_two(rs.getInt("Room_user_two"));
						chatRoom.setOtherUserName(rs.getString("other_user_name"));
						return chatRoom;
					}
					return null;
					}
				}catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error while checking if chat room exists." + e.getMessage(), e);
				}
	}

}
