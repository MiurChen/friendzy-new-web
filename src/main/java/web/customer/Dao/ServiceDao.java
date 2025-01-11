package web.customer.Dao;

import java.util.List;

import core.dao.dao;
import web.customer.bean.Service;

public class ServiceDao implements dao<Service>{

	@Override
	public int insert(Service item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Service item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Service seleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Service> seleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Service seleteByServiceID(Integer service_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateDeclineStatus(Service service) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
