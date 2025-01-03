package web.customer.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.customer.Dao.blackListDao;
import web.customer.Dao.Imp.blackListDaoImpl;
import web.customer.bean.BlackList;

@Path("/customer/blackList")
public class blackListController {
	private blackListDao blackListDao;
	
	public blackListController() throws NamingException {
		blackListDao = new blackListDaoImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlackList> showBlackList() throws Exception{
		return blackListDao.seleteAll();
	}
}
