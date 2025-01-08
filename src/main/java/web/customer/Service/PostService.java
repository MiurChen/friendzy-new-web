package web.customer.Service;

import java.util.List;

import web.customer.bean.OrderList;
import web.customer.bean.Service;

public interface PostService {
	List<Service> showPostList() throws Exception;
	
	Service selectedPostList(Service service) throws Exception;
	
	Integer updateStatus(Service service) throws Exception;
}
