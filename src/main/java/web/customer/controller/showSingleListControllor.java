package web.customer.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.customer.Dao.OrderListDao;
import web.customer.Dao.Imp.OrderListDaoImpl;
import web.customer.bean.OrderList;

@WebServlet("/customer/showlist")
public class showSingleListControllor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		OrderListDao orderListDaoiDao;
		try {
			orderListDaoiDao = new OrderListDaoImpl();
			OrderList orderList = orderListDaoiDao.seleteBy(5);
			String a = gson.toJson(orderList);
			resp.getWriter().write(a);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		orderList.getOrder_id();
//		orderList.getOrder_person();
//		orderList.getOrder_status();
//		orderList.getOrder_price();
//		orderList.getOrder_poster();
		
//		resp.getWriter().write(orderList.getOrder_id());
//		resp.getWriter().write(orderList.getOrder_person());
//		resp.getWriter().write(orderList.getOrder_status());
//		resp.getWriter().write(orderList.getOrder_price().toString());
//		resp.getWriter().write(orderList.getOrder_poster());
	}
		
}
