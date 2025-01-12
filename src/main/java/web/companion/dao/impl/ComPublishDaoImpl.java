package web.companion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.companion.dao.ComPublishDao;
import web.companion.pojo.ComArea;
import web.companion.pojo.ComPublish;
import web.companion.pojo.ComSkill;

public class ComPublishDaoImpl extends ComPublishDao{
	private DataSource ds;
	
	public ComPublishDaoImpl() throws NamingException{
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/friendzy");
	}
	
	//主頁清單取得我以外的刊登項目
	public List<ComPublish> selectPublishList(Integer memberNo) throws Exception{
		List<ComPublish> publishList = new ArrayList<ComPublish>();
		String sql = "select s.service_id as 'service_id',"
				+ "s.service as'service',"
				+ "s.service_poster as'poster',"
				+ "m.member_name as'poster_name',"
				+ "s.start_time as'start_time',"
				+ "concat( sa.area_city,' ', sa.area_district) as'area'"
				+ " from service s join member_info m join service_area sa"
				+ " on s.service_poster = m.member_no and sa.area_no = s.service_location"
				+ " where s.service_poster != ? and s.service_status = 1;";
		try (//service_status要改成0，現在用1只是0沒資料
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)		
		){
			pstmt.setInt(1, memberNo);
			try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					ComPublish lists = new ComPublish();
					//清單需要：訂單編號、服務單號、標題、刊登人Id、刊登人名字、開始時間、區域
//					lists.setOrderId(rs.getInt("order_id"));
					lists.setServiceId(rs.getInt("service_id"));
					lists.setService(rs.getString("service"));
					lists.setPoster(rs.getInt("poster"));
					lists.setPosterName(rs.getString("poster_name"));
					lists.setStartTime(rs.getTimestamp("start_time"));
					lists.setArea(rs.getString("area"));
					publishList.add(lists);
				}
				return publishList;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//主頁單項刊登項目詳細資訊
	public ComPublish selectPublishDetail(Integer memberNo, Integer serviceId) throws Exception{
		String sql = "select o.order_id as'order_id',"
				+ "s.service_id as 'service_id',"
				+ "s.service as'service',"
				+ "s.servicr_detail as'servicr_detail',"
				+ "s.service_poster as'poster',"
				+ "m.member_name as'poster_name',"
				+ "m.member_pic as'theirImg',"
				+ "s.start_time as'start_time',"
				+ "s.finished_time as'finished_time',"
				+ "s.sverice_charge as'sverice_charge',"
				+ "s.service_pic as'service_img',"
				+ "concat(sa.area_city,' ', sa.area_district) as'area'"
				+ " from service s join member_info m join service_area sa join order_list o"
				+ " on s.service_poster = m.member_no and sa.area_no = s.service_location and s.service_id = o.service_idno"
				+ " where s.service_id = ?;";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		){
			pstmt.setInt(1, serviceId);
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					//清單需要：訂單標號、服務單號、標題、刊登人Id、刊登人名字、開始時間、區域
					//詳細需要：內容、結束時間、金額、對方ID(刊登人)、對方名字(刊登人)、刊登人頭像、圖片
					ComPublish publish = new ComPublish();
					publish.setOrderId(rs.getInt("order_id"));
					publish.setServiceId(rs.getInt("service_id"));
					publish.setService(rs.getString("service"));
					publish.setServiceDetail(rs.getString("servicr_detail"));
					publish.setPoster(rs.getInt("poster"));
					publish.setPosterName(rs.getString("poster_name"));
					publish.setTheirImg(rs.getString("theirImg"));
					publish.setStartTime(rs.getTimestamp("start_time"));
					publish.setEndTime(rs.getTimestamp("finished_time"));
					publish.setCharge(rs.getDouble("sverice_charge"));
					publish.setServiceImg(rs.getString("service_img"));
					publish.setArea(rs.getString("area"));
//					System.out.println(publish);
					return publish;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//刊登取得地區
	public List<ComArea> selectArea() throws Exception{
		List<ComArea> lists = new ArrayList<ComArea>();
		String sql = "SELECT * FROM friendzy.service_area;";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		){
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ComArea list = new ComArea();
					list.setAreaNo(rs.getString("area_no"));
					list.setAreaCity(rs.getString("area_city"));
					list.setAreaDistricy(rs.getString("area_district"));
					lists.add(list);
				}
				return lists;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//刊登取得專長
	public List<ComSkill> selectSkill() throws Exception{
		List<ComSkill> lists = new ArrayList<ComSkill>();
		String sql = "SELECT * FROM areas_of_expertise;";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		){
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ComSkill list = new ComSkill();
					list.setExpertiseNo(rs.getString("expertise_no"));
					list.setExpertiseLabel(rs.getString("expertise_label"));
					lists.add(list);
				}
				return lists;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//刊登新增服務資料
	public ComPublish addService(ComPublish publish) throws Exception{
		String insertSql = " insert into service(service_poster , service , servicr_detail , start_time ,"
				+ " finished_time , sverice_charge , service_status , service_location , poster_status,service_pic)"
				+ " value(? , ? , ? , ? , ? , ? , ? , ? , ? , '');";
		String selectSql =" select service_id , sverice_charge , service_poster , service"
				+ " from service"
				+ " where service_id = (select max(service_id) as 'serviceId' from service);";
			try (
				Connection connection = ds.getConnection();
				PreparedStatement psI = connection.prepareStatement(insertSql);
				PreparedStatement psS = connection.prepareStatement(selectSql);
			){
				psI.setInt(1, publish.getMemberNo());
				psI.setString(2, publish.getService());
				psI.setString(3, publish.getServiceDetail());
				psI.setTimestamp(4, publish.getStartTime());
				psI.setTimestamp(5, publish.getEndTime());
				psI.setDouble(6, publish.getCharge());
				psI.setInt(7, publish.getServiceStatus());
				psI.setString(8, publish.getArea());
				psI.setInt(9, publish.getPosterStatus());
				System.out.println(psI.executeUpdate());
				try (ResultSet rs = psS.executeQuery()){
					if (rs.next()) {
						ComPublish serviceId = new ComPublish();
						serviceId.setServiceId(rs.getInt("service_Id"));
						serviceId.setService(rs.getString("service"));
						serviceId.setPoster(rs.getInt("service_poster"));
						serviceId.setCharge(rs.getDouble("sverice_charge"));
						System.out.println(serviceId);
						return serviceId;
					}
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return null;
	}
	
	//刊登新增訂單資料
	public int addOrder(ComPublish publish) throws Exception{
		String sql = "insert into order_list(service_idno , order_price , order_status , order_poster , order_title,"
				+ "companion_rate_content,customer_rate_content,customer_rate,companion_rate)"
				+ " value(? , ? , 0 , ? , ? , '' , '' , 0 , 0);";
			try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, publish.getServiceId());
				ps.setDouble(2, publish.getCharge());
				ps.setInt(3, publish.getPoster());
				ps.setString(4, publish.getService());
				return ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return -1;
	}
	
	
}
