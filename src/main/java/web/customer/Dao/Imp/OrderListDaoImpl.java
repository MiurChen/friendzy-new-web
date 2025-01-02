package web.customer.Dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import web.customer.Dao.OrderListDao;
import web.customer.bean.OrderList;
import web.member.pojo.Member;

public class OrderListDaoImpl extends OrderListDao {
	private DataSource ds;

	public OrderListDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}

	@Override
	public OrderList seleteBy(Integer order_id) throws Exception {
		String sql = "select * from order_list where order_id = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, order_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OrderList orderList = new OrderList();
					orderList.setOrder_id(rs.getInt("order_id"));
					orderList.setService_idno(rs.getInt("service_idno"));
					orderList.setOrder_person(rs.getInt("order_person"));
					orderList.setCustomer_rating(rs.getInt("customer_rate"));
					orderList.setCustomer_rate_content(rs.getString("customer_rate_content"));
					orderList.setCompanion_rate(rs.getInt("companion_rate"));
					orderList.setCompanion_rate_content(rs.getString("companion_rate_content"));
					orderList.setOrder_price(rs.getDouble("order_price"));
					orderList.setOrder_status(rs.getInt("order_status"));
					orderList.setOrder_poster(rs.getInt("order_poster"));
					return orderList;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderList> seleteAll() throws Exception {
		List<OrderList> orderLists = new ArrayList<OrderList>();
		String sql = "select * from  order_list o join member_info m on o.order_person = m.member_no";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					OrderList orderList = new OrderList();
					orderList.setMember_name(rs.getString("member_name"));
					orderList.setOrder_id(rs.getInt("order_id"));
					orderList.setService_idno(rs.getInt("service_idno"));
					orderList.setOrder_person(rs.getInt("order_person"));
					orderList.setCustomer_rating(rs.getInt("customer_rate"));
					orderList.setCustomer_rate_content(rs.getString("customer_rate_content"));
					orderList.setCompanion_rate(rs.getInt("companion_rate"));
					orderList.setCompanion_rate_content(rs.getString("companion_rate_content"));
					orderList.setOrder_price(rs.getDouble("order_price"));
					orderList.setOrder_status(rs.getInt("order_status"));
					orderList.setOrder_poster(rs.getInt("order_poster"));
					orderLists.add(orderList);
				}
				return orderLists;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insert(OrderList orderList) throws Exception {
		String sql = "insert into order_list(order_id, order_perosn, order_price) values(?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderList.getOrder_id());
			pstmt.setInt(2, orderList.getOrder_person());
			pstmt.setDouble(3, orderList.getOrder_price());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
