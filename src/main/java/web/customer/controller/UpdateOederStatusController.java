package web.customer.controller;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import web.customer.Service.OrderListService;
import web.customer.Service.OrderListServiceImpl;
import web.customer.bean.OrderList;
import web.customer.bean.Service;

@Path("customer/updateOrderStatus")
public class UpdateOederStatusController {
	private OrderListService orderListService;
	
	public UpdateOederStatusController() throws NamingException {
		orderListService = new OrderListServiceImpl();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer updateOrderStatus(Service service) throws Exception {
		 System.out.println("Received service ID: " + service.getService_id());
		    Integer result = orderListService.updateStatus(service);
		    System.out.println("Update result: " + result);
		    return result;
	}
}
