package web.customer.Dao.Imp;

import java.awt.dnd.DragSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.customer.Dao.blackListDao;
import web.customer.bean.BlackList;

public class blackListDaoImpl extends blackListDao{
	private DataSource ds;
	
	public blackListDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}
	
	@Override
	public List<BlackList> seleteAll() throws Exception {
		String sql = "select * from blacklist";
		List<BlackList> blackLists = new ArrayList<BlackList>();
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					BlackList blackList = new BlackList();
					blackList.setUser_id(rs.getInt("user_id"));
					blackList.setBlacklist_id(rs.getInt("blacklist_id"));
					blackList.setBlacklist_reason(rs.getString("blacklist_reason"));
					blackLists.add(blackList);
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blackLists;
	} 
	
	
//	@Override
//	public int deleteBy(String id) throws Exception {
//		String sql = "delete blacklist where user_id = ? and blacklist_id = ?";
//		BlackList blackList = new BlackList();
//		
//		try (
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);	
//				){
//			pstmt.setInt(1, blackList.getUser_id());
//			pstmt.setInt(2, blackList.getBlacklist_id());
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			
//		}
//		
//		return -1;
//	}
	
	
	@Override
	public int insert(BlackList item) throws Exception {
		String sql = "insert into blacklist(user_id, blacklist_id, blackist_reason) values(?, ?, ?)";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);	
				) {
			pstmt.setInt(1, item.getUser_id());
			pstmt.setInt(2, item.getBlacklist_id());
			pstmt.setString(3,item.getBlacklist_reason());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}

