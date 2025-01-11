package web.member.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.dao.MemberDao;
import web.member.pojo.Member;



public class MemberDaoImpl extends MemberDao {
	private DataSource ds;

	public MemberDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}

	@Override
	public int insert(Member member) {
		String sql = "INSERT INTO member_info (email, mpassword, member_name, member_nick_name, member_pic, phone, "
				+ "introduction, companion_comment, companion_score, custmer_comment, custmer_score, "
				+ "registration_time, member_status, member_token) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getMpassword());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getMember_nick_name());
			pstmt.setString(5, member.getMember_pic());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getIntroduction());
			pstmt.setInt(8, member.getCompanion_comment());
			pstmt.setInt(9, member.getCompanion_score());
			pstmt.setInt(10, member.getCustmer_comment());
			pstmt.setInt(11, member.getCustmer_score());
			pstmt.setTimestamp(12, member.getRegistration_time());
			pstmt.setBoolean(13, member.getMember_status());
			pstmt.setString(14, member.getMember_token());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Member item) {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String id) {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member seleteBy(String email) {
		String sql = "SELECT * FROM member_info where email= ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstms = conn.prepareStatement(sql);) {
			pstms.setString(1, email);
			try (ResultSet rs = pstms.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setMember_no(rs.getInt("member_no"));
					member.setEmail(rs.getString("email"));
					member.setMpassword(rs.getString("mpassword"));
					member.setMember_name(rs.getString("member_name"));
					member.setMember_nick_name(rs.getString("member_nick_name"));
					member.setPhone(rs.getString("phone"));
					member.setIntroduction(rs.getString("introduction"));
					member.setCompanion_comment(rs.getInt("companion_comment"));
					member.setCompanion_score(rs.getInt("companion_score"));
					member.setCustmer_comment(rs.getInt("custmer_comment"));
					member.setCustmer_score(rs.getInt("custmer_score"));
					member.setRegistration_time(rs.getTimestamp("registration_time"));
					member.setMember_status(rs.getBoolean("member_status"));
					member.setMember_token(rs.getString("member_token"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Member> seleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassword(Member member) {
		System.out.println("updatePassword: "+member.getEmail());
		String sql = "UPDATE member_info SET mpassword = ? WHERE email = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
			){
				pstmt.setString(1, member.getMpassword());
				pstmt.setString(2, member.getEmail());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			return -1;
	}

	@Override
	public int updateNickname(Member member) {
		String sql = "UPDATE member_info SET Member_nick_name = ? WHERE email = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
			){
				pstmt.setString(1, member.getMember_nick_name());
				pstmt.setString(2, member.getEmail());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return -1;
	}

	@Override
	public int updatePhone(Member member) {
		String sql = "UPDATE member_info SET Phone = ? WHERE email = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
			){
				pstmt.setString(1, member.getPhone());
				pstmt.setString(2, member.getEmail());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return -1;
	}
	
	@Override
	public int updateIntroduction(Member member) {
		String sql = "UPDATE member_info SET Introduction = ? WHERE email = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
			){
				pstmt.setString(1, member.getIntroduction());
				pstmt.setString(2, member.getEmail());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return -1;
	}
	
}