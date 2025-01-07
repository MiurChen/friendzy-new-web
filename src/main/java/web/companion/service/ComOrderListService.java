package web.companion.service;

import java.util.List;

import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;

public interface ComOrderListService{
	
		ComOrder shortMyOrder(Integer meberNo , Integer person , Integer orderId) throws Exception;
			
		List<ComOrder> shortAllOrder(Integer meberNo) throws Exception;
	
		ComOrder statusUpdate(ComOrder orderSt) throws Exception;
		
		String cancelApply(Integer serviceId)throws Exception;

	
}
