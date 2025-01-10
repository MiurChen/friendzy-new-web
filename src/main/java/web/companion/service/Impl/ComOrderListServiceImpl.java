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
import web.companion.service.ComOrderListService;

public class ComOrderListServiceImpl implements ComOrderListService{
	private ComOrderDao comOrderDao;
	private ComApplicantDao comApplicantDao;
	
	public ComOrderListServiceImpl() throws NamingException {
		comOrderDao = new ComOrderDaoImpl();
		comApplicantDao = new ComApplicantDaoImpl();
	}
	
	//取得所有訂單的基本資訊
	@Override
	public List<ComOrder> showAllOrder(Integer meberNo) throws Exception {
		return comOrderDao.showAllOrder(meberNo);
	}
	
	//取得特定ID的訂單詳細資訊
	@Override
	public ComOrder showMyOrder(Integer meberNo , Integer poster , Integer orderId) throws Exception {
		if (poster == meberNo) {//我為刊登者
			System.out.println("posterMe");
			return comOrderDao.selectPosterMeBy(orderId);
		}else {//對方為刊登者
			System.out.println("posterOther");
			return comOrderDao.selectPosterOtherBy(orderId);
		}
	}
	
	//更新訂單狀態
	@Override
	public ComOrder statusUpdate(ComOrder orderSt) throws Exception {
		if (comOrderDao.update(orderSt) > 0) {
			return orderSt;
		}else {
			return null;
		}
	}
	
	//取消訂單 更新應徵者的應徵狀態
	@Override
	public String cancelApply(Integer serviceId) throws Exception {
		if (comApplicantDao.applyStatusUpdate(serviceId) > 0) {
			return serviceId.toString();
//			return "更改成功："+serviceId.toString()+"比資料受影響";
		}else {
			return null;			
		}
	}
	//取得所有應徵(我或別人)

	
}
