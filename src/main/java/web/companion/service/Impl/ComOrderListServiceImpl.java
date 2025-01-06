package web.companion.service.Impl;

import java.util.List;

import javax.naming.NamingException;

import web.companion.dao.ComOrderDao;
import web.companion.dao.impl.ComOrderDaoImpl;
import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;
import web.companion.service.ComOrderListService;

public class ComOrderListServiceImpl implements ComOrderListService{
	private ComOrderDao comOrderDao;
	
	public ComOrderListServiceImpl() throws NamingException {
		comOrderDao = new ComOrderDaoImpl();
	}
	//取得所有訂單的基本資訊
	@Override
	public List<ComOrder> shortAllOrder() throws Exception {
		return comOrderDao.seleteAll();
	}
	//取得特定ID的訂單詳細資訊，我為刊登者
	@Override
	public ComOrder shortMyOrder(Integer id) throws Exception {
		return comOrderDao.selectPosterMeBy(id);
	}
	//取得特定ID的訂單詳細資訊，對方為刊登者
	@Override
	public ComOrder shortOtherOrder(Integer id) throws Exception {
		return comOrderDao.selectPosterOtherBy(id);
	}
	//更新訂單狀態
	@Override
	public ComOrder update(ComOrder orderSt) throws Exception {
		if (comOrderDao.update(orderSt) > 0) {
			return orderSt;
		}else {
			return null;
		}
	}
	//取消訂單更新應徵者的應徵狀態、應徵結果
	@Override
	public String cancelApply(ComApplicant applicant) throws Exception {
		if (comOrderDao.cancelApplyUpdate(applicant) > 0) {
			return applicant.toString();
//			return "更改成功："+serviceId.toString()+"比資料受影響";
		}else {
			return null;			
		}
	}
	
	
}
