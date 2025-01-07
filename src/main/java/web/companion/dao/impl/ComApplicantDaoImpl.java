package web.companion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public int applicantAccountUpdate(ComApplicant applicant) throws Exception{
		//應徵結果 0:未得標 1:已得標
		String sql = "update applicant set application_result  = 1 where service_id = ? and applicant_account  = ?;";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ps.setInt(1, applicant.getServiceId());
				ps.setInt(2, applicant.getApplicant_account());

				return ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
	
	//更改應徵table的應徵狀態為1
	@Override
	public int applyStatusUpdate(Integer serviceId) throws Exception{
		//應徵狀態 0:未應徵 1:已應徵
		String sql = "update applicant set apply_status = 1 and where service_id = ?;";
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
}
