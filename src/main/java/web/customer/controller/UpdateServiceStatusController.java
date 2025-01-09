package web.customer.controller;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.customer.Dao.OrderListDao;
import web.customer.Service.OrderListService;
import web.customer.Service.OrderListServiceImpl;
import web.customer.Service.PostService;
import web.customer.Service.PostServiceImpl;
import web.customer.bean.OrderList;
import web.customer.bean.Service;

@Path("/customer/updateStatus")
public class UpdateServiceStatusController {
	private PostService postService;
	
	public UpdateServiceStatusController() throws NamingException {
		postService = new PostServiceImpl();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Integer updateOrder(Service service) throws Exception {
		System.out.println("successful");
		 Integer result = postService.updateStatus(service);
		 return result;
	} 
}
