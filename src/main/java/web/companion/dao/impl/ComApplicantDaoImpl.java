package web.companion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.companion.dao.ComApplicantDao;
import web.companion.dao.ComOrderDao;
import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;
import web.customer.bean.OrderList;

public class ComApplicantDaoImpl extends ComApplicantDao {
	private DataSource ds;
	
	public ComApplicantDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}
	
	@Override
	public int insert(ComApplicant item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ComApplicant item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ComApplicant seleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComApplicant> seleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	//更改特定應徵者的的結果為1
	@Override
	public int acceptStatusUpdate(ComApplicant applicant) throws Exception{
		//應徵結果 0:未得標 1:已得標
		String sql = "update applicant set application_result  = 1 where service_id = ? and applicant_account  = ?;"
				+ "update service set service_status = 1 where service_id = ?;";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, applicant.getServiceId());
				ps.setInt(2, applicant.getAccountId());
				ps.setInt(3, applicant.getServiceId());
				return ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
	
	//更改應徵table的應徵狀態為1
	@Override
	public int updateAllStatus(Integer serviceId) throws Exception{
		//應徵狀態 0:未應徵 1:已應徵
		String sql = "update applicant set apply_status = 1 where service_id = ?;";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, serviceId);
				return ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
	//拒絕預約
	@Override
	public int rejectStatus(ComApplicant applicant) throws Exception{
		//應徵狀態 0:未應徵 1:已應徵
		String sql = "update applicant set apply_status = 1 where service_id = ? and applicant_account  = ?;";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, applicant.getServiceId());
				ps.setInt(2, applicant.getAccountId());
				return ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
	//所有應徵項目
	@Override
	public List<ComApplicant> showAllApplicant(Integer memberId)throws Exception{
		List<ComApplicant> lists = new ArrayList<ComApplicant>();
		String sql = "select o.order_id as 'order_id',"
				+ "a.service_id as'service_id',"
				+ "s.service_poster as'order_poster',"
				+ "a.applicant_account as 'applicant_account',"
				+ "m2.member_name as 'account_name',"
				+ "a.apply_status as'apply_status',"
				+ "a.application_result as'application_result',"
				+ "s.service as'service',"
				+ "s.start_time as'start_time'"
				+ " from order_list o join service s join member_info m1"
				+ " join member_info m2 join service_area sa join applicant a"
				+ " on s.service_id = o.service_idno and s.service_location = sa.area_no and s.service_id = a.service_id"
				+ " and s.service_poster = m1.member_no and a.applicant_account = m2.member_no"
				+ " where m1.member_no = ? or m2.member_no = ?;";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, memberId);
				ps.setInt(2, memberId);
				try (ResultSet rs = ps.executeQuery()){
					while (rs.next()) {
						ComApplicant list = new ComApplicant();
						list.setOrderId(rs.getInt("order_id"));
						list.setServiceId(rs.getInt("service_id"));
						list.setOrderPoster(rs.getInt("order_poster"));
						list.setAccountId(rs.getInt("applicant_account"));
						list.setAccountName(rs.getString("account_name"));
						list.setApplyStatus(rs.getInt("apply_status"));
						list.setApplicationResult(rs.getInt("application_result"));
						list.setService(rs.getString("service"));
						list.setStartTime(rs.getTimestamp("start_time"));
						lists.add(list);
						System.out.println(lists);
						System.out.println("/n");

					}
					return lists;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	//應徵項目詳細之訊，我為刊登者
	@Override
	public ComApplicant selectAccountMyById(Integer account ,Integer serviceId)throws Exception{
		String sql = "select o.order_id as'order_id',"
				+ "s.service_id as'service_id',"
				+ "o.order_status as'order_status',"
				+ "s.service_id as'theirId',"
				+ "m1.member_name as'theirName',"
				+ "m1.member_pic as'theirImg',"
				+ "s.service_poster as'order_poster',"
				+ "m1.member_name as'poster_name',"
				+ "s.service as'service',"
				+ "s.servicr_detail as'servicr_detail',"
				+ "s.start_time as'start_time',"
				+ "s.finished_time as'finished_time',"
				+ "o.order_price as'order_price',"
				+ "concat( sa.area_city,' ', sa.area_district) as'area',"
				+ "a.applicant_account as'applicant_account',"
				+ "m2.member_name as'account_name',"
				+ "a.apply_status as'apply_status',"
				+ "a.application_result as'application_result'"
				+ " from service s join applicant a join service_area sa join member_info m1 join member_info m2 join order_list o"
				+ " on s.service_id = a.service_id and s.service_location = sa.area_no and s.service_id = o.service_idno"
				+ " and s.service_poster = m1.member_no and a.applicant_account = m2.member_no"
				+ " where s.service_id = ? and a.applicant_account = ?;";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)
		){
			pstmt.setInt(1, serviceId);
			pstmt.setInt(2, account);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ComApplicant applicant = new ComApplicant();
					applicant.setOrderId(rs.getInt("order_id"));
					applicant.setServiceId(rs.getInt("service_id"));
					applicant.setOrderStatus(rs.getInt("order_status"));
					applicant.setTheirName(rs.getString("theirName"));
					applicant.setTheirId(rs.getInt("theirId"));
					applicant.setTheirImg(rs.getString("theirImg"));
					applicant.setOrderPosterName(rs.getString("poster_name"));
					applicant.setOrderPoster(rs.getInt("order_poster"));
					applicant.setService(rs.getString("service"));
					applicant.setServiceDetail(rs.getString("servicr_detail"));
					applicant.setStartTime(rs.getTimestamp("start_time"));
					applicant.setEndTime(rs.getTimestamp("finished_time"));
					applicant.setOrderPrice(rs.getDouble("order_price"));
					applicant.setArea(rs.getString("area"));
					applicant.setAccountId(rs.getInt("applicant_account"));
					applicant.setAccountName(rs.getString("account_name"));
					applicant.setApplyStatus(rs.getInt("apply_status"));
					applicant.setApplicationResult(rs.getInt("application_result"));
					System.out.println(applicant);
					return applicant;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//應徵項目詳細之訊，對方為刊登者
	@Override
	public ComApplicant selectAccountOtherById(Integer account ,Integer serviceId)throws Exception{
		String sql = "select o.order_id as'order_id',"
				+ "s.service_id as'service_id',"
				+ "o.order_status as'order_status',"
				+ "a.applicant_account as'theirId',"
				+ "m2.member_name as'theirName',"
				+ "m2.member_pic as'theirImg',"
				+ "s.service_poster as'order_poster',"
				+ "m1.member_name as'poster_name',"
				+ "s.service as'service',"
				+ "s.servicr_detail as'servicr_detail',"
				+ "s.start_time as'start_time',"
				+ "s.finished_time as'finished_time',"
				+ "o.order_price as'order_price',"
				+ "concat( sa.area_city,' ', sa.area_district) as'area',"
				+ "a.applicant_account as'applicant_account',"
				+ "m2.member_name as'account_name',"
				+ "a.apply_status as'apply_status',"
				+ "a.application_result as'application_result'"
				+ " from service s join applicant a join service_area sa join member_info m1 join member_info m2 join order_list o"
				+ " on s.service_id = a.service_id and s.service_location = sa.area_no and s.service_id = o.service_idno"
				+ " and s.service_poster = m1.member_no and a.applicant_account = m2.member_no"
				+ " where s.service_id = ? and a.applicant_account = ?;";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)
		){
			pstmt.setInt(1, serviceId);
			pstmt.setInt(2, account);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ComApplicant applicant = new ComApplicant();
					applicant.setOrderId(rs.getInt("order_id"));
					applicant.setServiceId(rs.getInt("service_id"));
					applicant.setOrderStatus(rs.getInt("order_status"));
					applicant.setTheirName(rs.getString("theirName"));
					applicant.setTheirId(rs.getInt("theirId"));
					applicant.setTheirImg(rs.getString("theirImg"));
					applicant.setOrderPosterName(rs.getString("poster_name"));
					applicant.setOrderPoster(rs.getInt("order_poster"));
					applicant.setService(rs.getString("service"));
					applicant.setServiceDetail(rs.getString("servicr_detail"));
					applicant.setStartTime(rs.getTimestamp("start_time"));
					applicant.setEndTime(rs.getTimestamp("finished_time"));
					applicant.setOrderPrice(rs.getDouble("order_price"));
					applicant.setArea(rs.getString("area"));
					applicant.setAccountId(rs.getInt("applicant_account"));
					applicant.setAccountName(rs.getString("account_name"));
					applicant.setApplyStatus(rs.getInt("apply_status"));
					applicant.setApplicationResult(rs.getInt("application_result"));
					System.out.println(applicant);
					return applicant;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//預約,新增應徵者資料
	public int addApplicant(Integer serviceId,Integer memberNo ) throws Exception{
		System.out.println("預約"+serviceId);
		String sql = " insert into applicant("
				+ " service_id,applicant_account,application_date,apply_status,application_result)"
				+ " value(? , ? , ? , 0 , 0);";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				LocalDateTime now = LocalDateTime.now();
				Timestamp timestamp = Timestamp.valueOf(now);
				ps.setInt(1, serviceId);
				ps.setInt(2, memberNo);
				ps.setTimestamp(3, timestamp);
				return ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
	//確認沒應徵過
	public int selectApplicantById(Integer serviceId,Integer memberNo ) throws Exception{
		System.out.println("預約過"+serviceId);
		String sql = "select * from applicant where service_id = ? and applicant_account = ?;";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, serviceId);
				ps.setInt(2, memberNo);
				return ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
}
