package web.customer.Dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.customer.Dao.ServiceDao;
import web.customer.bean.OrderList;
import web.customer.bean.Service;

public class ServiceDaoImpl extends ServiceDao {
	private DataSource ds;

	public ServiceDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}

	@Override
	public List<Service> seleteAll() throws Exception {
		String sql = "select * from service";
		List<Service> serviceList = new ArrayList<Service>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					Service service = new Service();
					Timestamp startTime = rs.getTimestamp("start_time");
					Timestamp finishedTime = rs.getTimestamp("finished_time");
					service.setService_id(rs.getInt("service_id"));
					service.setService_poster(rs.getInt("service_poster"));
					service.setService(rs.getString("service"));
					service.setService_detail(rs.getString("servicr_detail"));
					service.setStart_time(startTime.toInstant().toEpochMilli());
					service.setFinished_time(finishedTime.toInstant().toEpochMilli());
					service.setService_charge(rs.getDouble("sverice_charge"));
					service.setService_status(rs.getInt("service_status"));
					service.setService_location(rs.getString("service_location"));
					service.setPoster_status(rs.getInt("poster_status"));
					serviceList.add(service);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return serviceList;
	}

	@Override
	public Service seleteByServiceID(Integer service_id) throws Exception {
		String sql = "select * from service s join member_info m on s.service_poster = m.member_no where service_id  = ?";
//		Service service = new Service();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, service_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Service service = new Service();
					Timestamp startTime = rs.getTimestamp("start_time");
					Timestamp finishedTime = rs.getTimestamp("finished_time");
					service.setService_id(rs.getInt("service_id"));
					service.setMember_name(rs.getString("member_name"));
					service.setService(rs.getString("service"));
					service.setService_detail(rs.getString("servicr_detail"));
					service.setStart_time(startTime.toInstant().toEpochMilli());
					service.setFinished_time(finishedTime.toInstant().toEpochMilli());
					service.setService_charge(rs.getDouble("sverice_charge"));
					service.setService_location(rs.getString("service_location"));
					service.setService_poster(rs.getInt("service_poster"));
					service.setService_status(rs.getInt("service_status"));
					service.setService_pic(rs.getString("service_pic"));
					service.setPoster_status(rs.getInt("poster_status"));
					return service;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public int update(Service service) throws Exception {
		String sql = "update service set service_status = 2 where service_id =?";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);	
				) {
			pstmt.setInt(1, service.getService_id());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
