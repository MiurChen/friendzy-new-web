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
	//select所有應徵項目基本資訊
	@Override
	public List<ComApplicant> showAllApplocant(Integer memberNo) throws Exception {
		return comApplicantDao.showAllApplicant(memberNo);
	}
	//select指定應徵項目內容
	@Override
	public ComApplicant showApplocantById(Integer meberNo , Integer account  , Integer serviceId) throws Exception {
		if (account  == meberNo) {//我為應徵者
			return comApplicantDao.selectAccountMyById(account, serviceId);
		}else {//對方為應徵者
			return comApplicantDao.selectAccountOtherById(account, serviceId);
		}
	}
	//新增應徵項目
	@Override
	public int addApplicant(Integer serviceId, Integer memberNo) throws Exception {
		if (comApplicantDao.selectApplicantById(serviceId, memberNo) == 0) {
			System.out.println("新增應徵者資料");
			return comApplicantDao.addApplicant(serviceId, memberNo);
		}else {
			System.out.println("已有應徵者資料");
			return -1;
		}
	}
	//應徵狀態變更
	@Override
	public int statusUpdate(ComApplicant applicant) throws Exception {
		if (applicant.getReject() == 1){//拒絕
			return comApplicantDao.rejectStatus(applicant);
		}else {//接受
//			Integer upStatus = comApplicantDao.acceptStatusUpdate(applicant);
//			System.out.println(upStatus);
			comApplicantDao.acceptStatusUpdate(applicant);
			return comApplicantDao.updateAllStatus(applicant.getServiceId());
		}
	}
	

	
}
