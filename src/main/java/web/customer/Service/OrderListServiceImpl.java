package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.Dao.OrderListDao;
import web.customer.Dao.Imp.OrderListDaoImpl;
import web.customer.bean.OrderList;
import web.customer.bean.Service;

public class OrderListServiceImpl implements OrderListService {
	private OrderListDao orderlistdao;

	public OrderListServiceImpl() throws NamingException {
		orderlistdao = new OrderListDaoImpl();
	}



	@Override
	public List<OrderList> showAllList() throws Exception {
		return orderlistdao.seleteAll();
	}
//
//	@Override
//	public OrderList addOrderList(OrderList orderList) throws Exception {
//		if (orderList.getOrder_title() == null) {
//			return null;
//		}
//		if (orderList.getOrder_price() == null) {
//			return null;
//		}
//		if (orderList.getOrder_person() == null) {
//			return null;
//		}
//		Integer insertCount = orderlistdao.insert(orderList);
//		if (insertCount > 0) {
//			return orderList; // 插入成功後返回資料
//		} else {
//			throw new Exception("Failed to insert order into database.");
//		}
//
//	}

	@Override
	public Service addPost(Service service) throws Exception {
		if (service.getService() == null || service.getService_charge() == null || service.getPoster_status() == null
				|| service.getService_poster() == null 
				|| service.getStart_time() == null
				|| service.getFinished_time() == null 
				) {
			return null;
		}
		Integer insertCount = orderlistdao.insertService(service);
		if (insertCount > 0) {
			return service; // 插入成功後返回資料
		} else {
			throw new Exception("Failed to insert order into database.");
		}
	}

	@Override
	public OrderList addOrderList(OrderList orderList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public OrderList selectedOrder(OrderList orderList) throws Exception {
		Integer order_id = orderList.getOrder_id();
		OrderList result = orderlistdao.seleteBy(order_id);
		return result;
	}

}
