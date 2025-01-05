package web.companion.service.Impl;

import java.util.List;

import javax.naming.NamingException;

import web.companion.dao.ComOrderDao;
import web.companion.dao.impl.ComOrderDaoImpl;
import web.companion.pojo.ComOrder;
import web.companion.service.ComOrderListService;

public class ComOrderListServiceImpl implements ComOrderListService{
	private ComOrderDao comOrderDao;
	
	public ComOrderListServiceImpl() throws NamingException {
		comOrderDao = new ComOrderDaoImpl();
	}
	
	@Override
	public ComOrder shortMyOrder(Integer id) throws Exception {
		return comOrderDao.selectPosterMeBy(id);
	}

	@Override
	public ComOrder shortOtherOrder(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComOrder> shortAllOrder() throws Exception {
		return comOrderDao.seleteAll();
	}
}
