package web.customer.controller;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import web.customer.Service.PostService;
import web.customer.Service.PostServiceImpl;
import web.customer.bean.Service;

@Path("/customer/selectedPostList")
public class SelectedPostLostController {
	private PostService postService;
	
	public SelectedPostLostController() throws NamingException {
		postService = new PostServiceImpl();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Service selectedPostList(Service service) throws Exception {
		return postService.selectedPostList(service);
		 
	}
}
