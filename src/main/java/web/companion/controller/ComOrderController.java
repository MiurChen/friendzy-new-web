package web.companion.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.companion.dao.ComOrderDao;
import web.companion.dao.impl.ComOrderDaoImpl;
import web.companion.pojo.ComOrder;

@Path("/companion/order")
public class ComOrderController{
	private ComOrderDao comOrderDao;
	
	public ComOrderController() throws NamingException{
		comOrderDao = new ComOrderDaoImpl();
	}
	@GET
	@Path("/showAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComOrder> showList() throws Exception{
		return comOrderDao.seleteAll();
	}
	
	@GET
	@Path("/showId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComOrder showOrder(@PathParam("id") Integer id) throws Exception {
		return comOrderDao.selectPosterMeBy(id);
//		return comOrderDao.selectPosterOtherBy(id);
	}
		
}
