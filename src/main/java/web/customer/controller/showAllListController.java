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
import web.customer.Service.OrderListService;
import web.customer.Service.OrderListServiceImpl;
import web.customer.bean.OrderList;
import web.member.service.MemberService;


@Path("/customer/orderList")
public class showAllListController {
	private OrderListDao orderListDao;

	public showAllListController() throws NamingException {
		orderListDao = new OrderListDaoImpl();
	}

	@GET
	@Path("/showAllList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderList> showList() throws Exception {
		return orderListDao.seleteAll();
		 
	}
	

}
//@WebServlet("/customer/showAllList")
//public class showAllListController extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			OrderListService orderListService = new OrderListServiceImpl();
//			Gson gson = new Gson();
//			String json = gson.toJson(orderListService.showAllList());
//			resp.getWriter().write(json);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}

