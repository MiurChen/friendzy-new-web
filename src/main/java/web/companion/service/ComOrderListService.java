package web.companion.service;

import java.util.List;

import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;

public interface ComOrderListService{
	
		ComOrder showMyOrder(Integer meberNo , Integer poster , Integer orderId) throws Exception;
			
		List<ComOrder> showAllOrder(Integer meberNo) throws Exception;
	
		ComOrder statusUpdate(ComOrder orderSt) throws Exception;
		
		String cancelApply(Integer serviceId)throws Exception;

		int updateRate(ComOrder comOrder) throws Exception;

	
}
