package web.customer.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.customer.Dao.FavoriteListDao;
import web.customer.Dao.Imp.FavoriteListDaoImpl;
import web.customer.bean.FavortieList;

@Path("/customer/favoriteList")
public class FavorietListController {
	private FavoriteListDao favoriteListDao;
	
	public FavorietListController() throws NamingException {
		favoriteListDao = new FavoriteListDaoImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FavortieList> showFavortieList() throws Exception{
		return favoriteListDao.seleteAll();
	}

}
