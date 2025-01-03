package web.customer.Service;

import java.util.List;

import web.customer.bean.FavortieList;

public interface FavoriteListService {
	List<FavortieList> showFavortieList(FavortieList favortieList) throws Exception;
	//FavoriteList deleteFavoriteList(same) throw E; 
	
}
