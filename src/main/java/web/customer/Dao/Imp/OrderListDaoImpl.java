package web.customer.Dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import web.customer.Dao.OrderListDao;
import web.customer.bean.OrderList;
import web.customer.bean.Service;
import web.member.pojo.Member;

public class OrderListDaoImpl extends OrderListDao {
	private DataSource ds;

	public OrderListDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}

	@Override
	public OrderList seleteBy(Integer order_id) throws Exception {
		String sql = "select * from order_list o join member_info m on o.order_poster = m.member_no  join service s on o.order_poster = s.service_poster where order_id = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, order_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OrderList orderList = new OrderList();
					Timestamp startTime = rs.getTimestamp("start_time");
					Timestamp finishedTime = rs.getTimestamp("finished_time");
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
					orderList.setMember_name(rs.getString("member_name"));
					orderList.setService_detail(rs.getString("servicr_detail"));
					orderList.setStart_time(startTime.toInstant().toEpochMilli());
					orderList.setFinished_time(finishedTime.toInstant().toEpochMilli());
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
					orderList.setOrder_title(rs.getString("order_title"));
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
	public int insertService(Service service) throws Exception {
		String insertOrderList = "INSERT INTO order_list(order_poster, order_price, order_title) VALUES (?, ?, ?)";
		String insertPost = "INSERT INTO service(service_poster, sverice_charge, service, servicr_detail, start_time, finished_time,  poster_status, service_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ds.getConnection()) {
			conn.setAutoCommit(false); // 關閉自動提交，啟用交易

			try (PreparedStatement postPstmt = conn.prepareStatement(insertPost, Statement.RETURN_GENERATED_KEYS);
					PreparedStatement orderPstmt = conn.prepareStatement(insertOrderList)) {
				// 插入 service 資料
				postPstmt.setInt(1, service.getService_poster());
				postPstmt.setDouble(2, service.getService_charge());
				postPstmt.setString(3, service.getService());
				postPstmt.setString(4, service.getService_detail());
				

				// 將 long 型態的時間戳轉換為 Timestamp
				Timestamp startTimestamp = new Timestamp(service.getStart_time());
				Timestamp finishTimestamp = new Timestamp(service.getFinished_time());

				// 設定 Timestamp 到 PreparedStatement
				postPstmt.setTimestamp(5, startTimestamp);
				postPstmt.setTimestamp(6, finishTimestamp);
				postPstmt.setInt(7, service.getPoster_status());
				postPstmt.setInt(8, service.getService_status());

				postPstmt.executeUpdate();

				// 獲取 service 表自動生成的主鍵
				try (ResultSet rs = postPstmt.getGeneratedKeys()) {
					if (rs.next()) {
						int orderId = rs.getInt(1); // 獲取主鍵

						// 創建 order_list 資料
						OrderList orderList = new OrderList();
						orderList.setOrder_id(orderId);
						orderList.setOrder_poster(service.getService_poster()); // 從 service 中獲取
						orderList.setOrder_price(service.getService_charge()); // 金額等於服務收費
						orderList.setOrder_title(service.getService()); // 自定義標題

						// 插入 order_list 資料
						orderPstmt.setInt(1, orderList.getOrder_poster());
						orderPstmt.setDouble(2, orderList.getOrder_price());
						orderPstmt.setString(3, orderList.getOrder_title());
						orderPstmt.executeUpdate();
					}
				}

				conn.commit(); // 提交交易
				return 1; // 表示成功
			} catch (Exception e) {
				conn.rollback(); // 發生異常，回滾交易
				e.printStackTrace();
				throw e;
			} finally {
				conn.setAutoCommit(true); // 恢復自動提交模式
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
//	@Override
//	public int update(OrderList orderList) throws Exception {
//		String sql = "update service set service_status = 2 where service_id =?";
//		try(
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);	
//				) {
//			pstmt.setInt(1, orderList.getOrder_id());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

}
