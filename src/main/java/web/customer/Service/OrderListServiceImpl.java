package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.Dao.OrderListDao;
import web.customer.Dao.Imp.OrderListDaoImpl;
import web.customer.bean.OrderList;

public class OrderListServiceImpl implements OrderListService {
	private OrderListDao orderlistdao;

	public OrderListServiceImpl() throws NamingException {
		orderlistdao = new OrderListDaoImpl();
	}

	@Override
	public OrderList showSingleList(OrderList orderList) throws Exception {
		Integer order_id = orderList.getOrder_id();
//		Integer service_idno = orderList.getService_idno();
//		Integer order_person = orderList.getOrder_person();
//		Double order_price = orderList.getOrder_price();
//		Integer order_status = orderList.getOrder_status();
//		Integer order_poster = orderList.getOrder_poster();

		orderlistdao.seleteBy(order_id);
		return null;
	}

	@Override
	public List<OrderList> showAllList() throws Exception {
		return orderlistdao.seleteAll();
	}

}
