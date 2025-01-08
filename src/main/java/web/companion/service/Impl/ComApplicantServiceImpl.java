package web.companion.service.Impl;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.PathParam;

import web.companion.dao.ComApplicantDao;
import web.companion.dao.ComOrderDao;
import web.companion.dao.impl.ComApplicantDaoImpl;
import web.companion.dao.impl.ComOrderDaoImpl;
import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;
import web.companion.service.ComApplicantService;
import web.companion.service.ComOrderListService;

public class ComApplicantServiceImpl implements ComApplicantService{
//	private ComOrderDao comOrderDao;
	private ComApplicantDao comApplicantDao;
	
	public ComApplicantServiceImpl() throws NamingException {
//		comOrderDao = new ComOrderDaoImpl();
		comApplicantDao = new ComApplicantDaoImpl();
	}

	@Override
	public List<ComApplicant> showAllApplocant(Integer memberNo) throws Exception {
		return comApplicantDao.showAllApplicant(memberNo);
	}

	@Override
	public ComApplicant showApplocantById(Integer meberNo , Integer account  , Integer serviceId) throws Exception {
		if (account  == meberNo) {//我為應徵者
			return comApplicantDao.selectAccountMyById(account, serviceId);
		}else {//對方為應徵者
			return comApplicantDao.selectAccountOtherById(account, serviceId);
		}
	}
	

	
}
