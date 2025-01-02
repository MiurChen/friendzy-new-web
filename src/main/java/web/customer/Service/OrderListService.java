package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.bean.OrderList;

public interface OrderListService {
	OrderList showSingleList(OrderList orderList) throws Exception;
	List<OrderList> showAllList()throws Exception;

}
