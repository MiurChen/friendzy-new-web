package web.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import web.customer.Dao.OrderListDao;
import web.customer.Dao.Imp.OrderListDaoImpl;
import web.customer.Service.PostService;
import web.customer.Service.PostServiceImpl;
import web.customer.bean.OrderList;
import web.customer.bean.Service;


@Path("/customer/showPostList")
public class showPostListControllor  {
  private PostService postService;
  
  public showPostListControllor() throws NamingException {
	  postService = new PostServiceImpl();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Service> showPostList() throws Exception{
	  return postService.showPostList();
  }
		
}
