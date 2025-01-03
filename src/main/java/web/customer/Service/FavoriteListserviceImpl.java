package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.Dao.FavoriteListDao;
import web.customer.Dao.Imp.FavoriteListDaoImpl;
import web.customer.bean.FavortieList;

public class FavoriteListserviceImpl implements FavoriteListService{
	private FavoriteListDao favoriteListDao;
	
	public FavoriteListserviceImpl() throws NamingException {
	favoriteListDao = new FavoriteListDaoImpl();
	}
	@Override
	public List<FavortieList> showFavortieList(FavortieList favortieList) throws Exception {
		return favoriteListDao.seleteAll();
	}
	
	
	

}
