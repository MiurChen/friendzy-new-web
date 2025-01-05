package web.companion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.companion.dao.ComOrderDao;
import web.companion.pojo.ComOrder;
import web.customer.bean.OrderList;

public class ComOrderDaoImpl extends ComOrderDao {
	private DataSource ds;
	
	public ComOrderDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}
	
	@Override
	public int insert(ComOrder item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ComOrder item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ComOrder seleteBy(String order_id) throws Exception {
		
		return null;
	}

	@Override
	public List<ComOrder> seleteAll() throws Exception {
		List<ComOrder> orderLists = new ArrayList<ComOrder>();
		String sql = "select o.order_id as 'order_id',"
				+ "s.service as'service',"
				+ "o.order_person as 'order_person',"
				+ "m.member_name as'Person_name',"
				+ "o.order_status as 'order_status',"
				+ "s.service_status as'service_status',"
				+ "s.start_time as'start_time'"
				+ "from order_list o join service s join member_info m on s.service_id = o.order_id and o.order_person = m.member_no;";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ComOrder orderList = new ComOrder();
					//需要 訂單編號、標題、訂購人、訂購人ID、訂單狀態、開始時間
					orderList.setOrderId(rs.getInt("order_id"));
					orderList.setService(rs.getString("service"));
					orderList.setOrderPerson(rs.getInt("order_person"));
					orderList.setOrderPersonName(rs.getString("Person_name"));
					orderList.setOrderStatus(rs.getInt("order_status"));
					orderList.setServiceStatus(rs.getInt("service_status"));
					orderList.setStartTime(rs.getTimestamp("start_time"));
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
	public ComOrder selectPosterMeBy(Integer id) throws Exception {
		String sql = "select o.order_id as 'order_id',"
				+ "s.service_id as'service_id',"
				//m1為購買人person m2為刊登者Poster 
				+ "o.order_Poster as'their_id',"
				+ "m2.member_name as'their_name',"
				//對方↑
				+ "o.order_poster as'order_poster',"
				+ "m2.member_name as'poster_name',"
				//刊登人Poster↑
				+ "o.order_person as'order_person',"
				+ "m1.member_name as'person_name',"
				//購買人person↑
				+ "s.service as'service',"
				+ "s.start_time as'start_time',"
				+ "s.finished_time as'finished_time',"
				+ "o.order_status as'order_status',"
				+ "s.service_status as'service_status',"
				+ "o.customer_rate as'customer_rate',"
				+ "o.customer_rate_content as'customer_rate_content',"
				+ "o.companion_rate as 'companion_rate',"
				+ "o.companion_rate_content as'companion_rate_content'"
				+ "from order_list o join service s join member_info m1 join member_info m2 on"
				+ " s.service_id = o.order_id and o.order_person = m1.member_no and o.order_poster = m2.member_no "
				+ " where o.order_id = ?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					//對方頭像(有時間再用)、標題、刊登人、刊登人編號、
					//開始時間、結束時間、訂單狀態、顧客評分、顧客評價、陪伴者評分、陪伴者評價
					ComOrder order = new ComOrder();
					order.setOrderId(rs.getInt("order_id"));//訂單編號
					order.setServiceId(rs.getInt("service_id"));//服務編號
					order.setTheirId(rs.getInt("their_id"));//對方名字、購買人名字
					order.setTheirName(rs.getString("their_name"));//對方ID、購買人ID
					order.setOrderPosterName(rs.getString("poster_name")); // 刊登人
					order.setOrderPoster(rs.getInt("order_poster")); // 刊登人編號
					order.setOrderPerson(rs.getInt("order_person"));//訂購人ID
					order.setOrderPersonName(rs.getString("person_name"));//訂購人名字
					order.setService(rs.getString("service")); // 標題
					order.setStartTime(rs.getTimestamp("start_time")); // 開始時間
					order.setEndTime(rs.getTimestamp("finished_time")); // 結束時間
					order.setOrderStatus(rs.getInt("order_status")); // 訂單狀態
					order.setServiceStatus(rs.getInt("service_status"));
					order.setCusRate(rs.getString("customer_rate")); // 顧客評分
					order.setCusRateContent(rs.getString("customer_rate_content")); // 顧客評價
					order.setComRate(rs.getString("companion_rate")); // 陪伴者評分
					order.setComRateContent(rs.getString("companion_rate_content")); // 陪伴者評價
					return order;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public ComOrder selectPosterOtherBy(Integer id) throws Exception {
		String sql = "select o.order_id as 'order_id',"
				+ "s.service_id as'service_id',"
				//m1為購買人person m2為刊登者Poster 
				+ "o.order_person as'their_id',"
				+ "m1.member_name as'their_name',"
				//對方↑
				+ "o.order_poster as'order_poster',"
				+ "m2.member_name as'poster_name',"
				//刊登人Poster↑
				+ "o.order_person as'order_person',"
				+ "m1.member_name as'person_name',"
				//購買人person↑
				+ "s.service as'service',"
				+ "s.start_time as'start_time',"
				+ "s.finished_time as'finished_time',"
				+ "o.order_status as'order_status',"
				+ "s.service_status as'service_status',"
				+ "o.customer_rate as'customer_rate',"
				+ "o.customer_rate_content as'customer_rate_content',"
				+ "o.companion_rate as 'companion_rate',"
				+ "o.companion_rate_content as'companion_rate_content'"
				+ "from order_list o join service s join member_info m1 join member_info m2 on"
				+ " s.service_id = o.order_id and o.order_person = m1.member_no and o.order_poster = m2.member_no "
				+ " where o.order_id = ?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					//對方頭像(有時間再用)、標題、刊登人、刊登人編號、
					//開始時間、結束時間、訂單狀態、顧客評分、顧客評價、陪伴者評分、陪伴者評價
					ComOrder order = new ComOrder();
					order.setOrderId(rs.getInt("order_id"));//訂單編號
					order.setServiceId(rs.getInt("service_id"));//服務編號
					order.setTheirId(rs.getInt("their_id"));//對方名字、刊登人名字
					order.setTheirName(rs.getString("their_name"));//對方ID、刊登人ID
					order.setOrderPosterName(rs.getString("poster_name")); // 刊登人
					order.setOrderPoster(rs.getInt("order_poster")); // 刊登人編號
					order.setOrderPerson(rs.getInt("order_person"));//訂購人ID
					order.setOrderPersonName(rs.getString("person_name"));//訂購人名字
					order.setService(rs.getString("service")); // 標題
					order.setStartTime(rs.getTimestamp("start_time")); // 開始時間
					order.setEndTime(rs.getTimestamp("finished_time")); // 結束時間
					order.setOrderStatus(rs.getInt("order_status")); // 訂單狀態
					order.setServiceStatus(rs.getInt("service_status"));
					order.setCusRate(rs.getString("customer_rate")); // 顧客評分
					order.setCusRateContent(rs.getString("customer_rate_content")); // 顧客評價
					order.setComRate(rs.getString("companion_rate")); // 陪伴者評分
					order.setComRateContent(rs.getString("companion_rate_content")); // 陪伴者評價
					return order;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
}
