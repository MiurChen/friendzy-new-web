package web.customer.Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import core.dao.dao;
import web.customer.bean.OrderList;
import web.customer.bean.Service;

public class OrderListDao implements dao<OrderList>{
	
	@Override
	public int insert(OrderList item) throws Exception {
		
		return 0;
	}

	public int update(Integer order_id) throws Exception {
		
		return 0;
	}

	@Override
	public int deleteBy(String id) throws Exception {
	
		return 0;
	}

	@Override
	public OrderList seleteBy(String id) throws Exception {
		
		return null;
	}

	@Override
	public List<OrderList> seleteAll() throws Exception {

		return null;
	}

	public OrderList seleteBy(Integer order_id) throws Exception {
		
		return null;
	}

	public int insertService(Service service) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Service service) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrderList item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
