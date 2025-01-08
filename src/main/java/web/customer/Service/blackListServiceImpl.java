package web.customer.Service;

import java.util.List;

import javax.naming.NamingException;

import web.customer.Dao.blackListDao;
import web.customer.Dao.Imp.blackListDaoImpl;
import web.customer.bean.BlackList;

public class blackListServiceImpl implements blackListservice{
	private blackListDao blackListDao;
	
	public blackListServiceImpl() throws NamingException {
		blackListDao = new blackListDaoImpl();
	}
	@Override
	public List<BlackList> showAllBlackList(BlackList blackList) throws Exception {
		return blackListDao.seleteAll();
	}
	@Override
	public BlackList insertByID(BlackList blackList) throws Exception {
		
		return null;
	}
	@Override
	public Integer deleteByID(BlackList blackList) throws Exception {
		Integer result = blackListDao.deleteBy(blackList);
	    return result;
	}

}
