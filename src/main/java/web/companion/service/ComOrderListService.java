package web.companion.service;

import java.util.List;

import web.companion.pojo.ComOrder;

public interface ComOrderListService{
	
		ComOrder shortOrder(ComOrder comOrder);
		
		List<ComOrder> shortAllOrder();
	
	
}
