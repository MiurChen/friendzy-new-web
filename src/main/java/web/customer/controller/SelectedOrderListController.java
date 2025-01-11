package web.customer.controller;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.customer.Service.OrderListService;
import web.customer.Service.OrderListServiceImpl;
import web.customer.bean.OrderList;

@Path("/customer/selectedOrderList")
public class SelectedOrderListController {
	private OrderListService orderListService;
	
	public SelectedOrderListController() throws NamingException {
		orderListService = new OrderListServiceImpl();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderList selectedOrderList(OrderList orderList) throws Exception {
		System.out.println("orderList: " + orderList);
		OrderList orders =  orderListService.selectedOrder(orderList);
		System.out.println("orders: " + orders);
		return orderListService.selectedOrder(orderList);
		
		
	}
}
