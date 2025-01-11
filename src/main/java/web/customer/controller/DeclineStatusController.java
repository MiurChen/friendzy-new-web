package web.customer.controller;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import web.customer.Service.PostService;
import web.customer.Service.PostServiceImpl;
import web.customer.bean.Service;

@Path("/customer/updateDeclineStatus")
public class DeclineStatusController {
	private PostService postService;

	public DeclineStatusController() throws NamingException {
		postService = new PostServiceImpl();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public Integer updateOrder(Service service) throws Exception {
		System.out.println("successful");
		Integer result = postService.updateDeclineStatus(service);
		return result;
	}

}
