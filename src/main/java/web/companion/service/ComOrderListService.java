package web.companion.service;

import java.util.List;

import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;

public interface ComOrderListService{
	
		ComOrder shortMyOrder(Integer id) throws Exception;
	
		ComOrder shortOtherOrder(Integer id) throws Exception;
		
		List<ComOrder> shortAllOrder() throws Exception;
	
		ComOrder update(ComOrder orderSt) throws Exception;
		
		String cancelApply(ComApplicant applicant)throws Exception;

	
}
