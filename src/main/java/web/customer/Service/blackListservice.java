package web.customer.Service;

import java.util.List;

import web.customer.bean.BlackList;

public interface blackListservice {
	List<BlackList> showAllBlackList(BlackList blackList) throws Exception;
	
	BlackList insertByID(BlackList blackList)throws Exception;
	
	Integer deleteByID(BlackList blackList) throws Exception;
}
