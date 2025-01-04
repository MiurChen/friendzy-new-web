package web.customer.controller;



import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import core.pojo.Result;
import web.customer.Service.OrderListService;
import web.customer.Service.OrderListServiceImpl;
import web.customer.bean.OrderList;
import web.customer.bean.Service;

@Path("/customer/insertPost")
public class insertOrderListController {
	private OrderListService orderListService;
	
	public insertOrderListController() throws NamingException {
	orderListService = new OrderListServiceImpl();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Service newPost(Service service) throws Exception{
		return orderListService.addPost(service);
	}
}
