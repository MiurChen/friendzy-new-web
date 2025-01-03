package web.customer.Dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.customer.Dao.FavoriteListDao;
import web.customer.bean.FavortieList;

public class FavoriteListDaoImpl extends FavoriteListDao {
	private DataSource ds;

	public FavoriteListDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}

	@Override
	public List<FavortieList> seleteAll() throws Exception {
		String sql = "SELECT * FROM favorites f join member_info m where f.be_hunted = m.member_no;";
		List<FavortieList> favortieLists = new ArrayList<FavortieList>();
		

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					FavortieList favortieList = new FavortieList();
					favortieList.setHunter(rs.getInt("hunter"));
					favortieList.setBe_hunted_name(rs.getString("member_name"));
					favortieList.setBe_hunted(rs.getInt("be_hunted"));
					favortieLists.add(favortieList);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return favortieLists;
	}
}
