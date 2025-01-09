package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.Dao.ServiceDao;
import web.customer.Dao.Imp.ServiceDaoImpl;
import web.customer.bean.Service;

public class PostServiceImpl implements PostService {
	private ServiceDao serviceDao;

	public PostServiceImpl() throws NamingException {
		serviceDao = new ServiceDaoImpl();
	}

	@Override
	public List<Service> showPostList() throws Exception {
		return serviceDao.seleteAll();
	}

	@Override
	public Service selectedPostList(Service service) throws Exception {
		 if (service.getService_id() == null) {
		        throw new IllegalArgumentException("service_id cannot be null");
		    }
		    Service result = serviceDao.seleteByServiceID(service.getService_id());
		    if (result == null) {
		        throw new Exception("Service not found for ID: " + service.getService_id());
		    }
		    return result;
		
	}

	@Override
	public Integer updateStatus(Service service) throws Exception {
		Integer result = serviceDao.update(service);
	    return result;
	}

	@Override
	public Integer updateDeclineStatus(Service service) throws Exception {
		Integer result = serviceDao.updateDeclineStatus(service);
	    return result;
	}

}
