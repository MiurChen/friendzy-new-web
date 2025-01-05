package web.companion.service;

import java.util.List;

import web.companion.pojo.ComOrder;

public interface ComOrderListService{
	
		ComOrder shortMyOrder(Integer id) throws Exception;
	
		ComOrder shortOtherOrder(Integer id) throws Exception;

		
		List<ComOrder> shortAllOrder() throws Exception;
	
	
}
