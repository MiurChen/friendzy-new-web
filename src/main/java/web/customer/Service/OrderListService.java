package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.bean.OrderList;
import web.customer.bean.Service;

public interface OrderListService {
	OrderList selectedOrder(OrderList orderList) throws Exception;
	
	List<OrderList> showAllList()throws Exception;
	
	OrderList addOrderList(OrderList orderList) throws Exception;
	
	Service addPost(Service service) throws Exception;
	
	Integer updateStatus(OrderList orderList) throws Exception;

}
