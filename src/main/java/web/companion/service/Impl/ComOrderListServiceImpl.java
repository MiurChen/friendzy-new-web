package web.companion.service.Impl;

import java.util.Iterator;
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
import web.member.pojo.Member;

public class ComOrderListServiceImpl implements ComOrderListService {
	private ComOrderDao comOrderDao;
	private ComApplicantDao comApplicantDao;

	public ComOrderListServiceImpl() throws NamingException {
		comOrderDao = new ComOrderDaoImpl();
		comApplicantDao = new ComApplicantDaoImpl();
	}

	// 取得所有訂單的基本資訊
	@Override
	public List<ComOrder> showAllOrder(Integer meberNo) throws Exception {
		List<ComOrder> orderList = comOrderDao.showAllOrder(meberNo);
		List<Member> memberList = comOrderDao.selectAllName();
		for (ComOrder order:orderList) {
			for(Member member:memberList){
				if (order.getOrderPoster() == member.getMember_no()) {
					order.setOrderPosterName(member.getMember_name());
				}
				if (order.getOrderPerson() == member.getMember_no()) {
					order.setOrderPersonName(member.getMember_name());
				}
			}
			if (order.getOrderPoster() == 0) {
				order.setOrderPosterName("");
			}
			if (order.getOrderPerson() == 0) {
				order.setOrderPersonName("");
			}
		}
		System.out.println("order："+orderList);
		return orderList;
	}

	// 取得特定ID的訂單詳細資訊
	@Override
	public ComOrder showMyOrder(Integer meberNo, Integer poster, Integer orderId) throws Exception {
		if (poster == meberNo) {// 我為刊登者
			System.out.println("posterMe");
			ComOrder order = comOrderDao.selectPosterMeBy(orderId);
			List<Member> memberList = comOrderDao.selectAllName();
			for(Member member:memberList){
				if (order.getOrderPoster() == member.getMember_no()) {
					order.setOrderPosterName(member.getMember_name());
				}
				if (order.getOrderPerson() == member.getMember_no()) {
					order.setOrderPersonName(member.getMember_name());
				}
				if (order.getTheirId() == member.getMember_no()) {
					order.setTheirName(member.getMember_name());
				}
			}
			if (order.getOrderPoster() == 0) {
				order.setOrderPosterName("");
			}
			if (order.getOrderPerson() == 0) {
				order.setOrderPersonName("");
			}
			if (order.getTheirId() == 0) {
				order.setTheirName("");
			}
			return order;
		} else {// 對方為刊登者
			System.out.println("posterOther");
			ComOrder order = comOrderDao.selectPosterOtherBy(orderId);
			List<Member> memberList = comOrderDao.selectAllName();
			for(Member member:memberList){
				if (order.getOrderPoster() == member.getMember_no()) {
					order.setOrderPosterName(member.getMember_name());
				}
				if (order.getOrderPerson() == member.getMember_no()) {
					order.setOrderPersonName(member.getMember_name());
				}
				if (order.getTheirId() == member.getMember_no()) {
					order.setTheirName(member.getMember_name());
				}
			}
			if (order.getOrderPoster() == 0) {
				order.setOrderPosterName("");
			}
			if (order.getOrderPerson() == 0) {
				order.setOrderPersonName("");
			}
			if (order.getTheirId() == 0) {
				order.setTheirName("");
			}
			return order;
		}
	}

	// 更新訂單狀態
	@Override
	public ComOrder statusUpdate(ComOrder orderSt) throws Exception {
		int order = comOrderDao.update(orderSt);
		if (order > 0) {
			return orderSt;
		} else {
			return null;
		}
	}

	// 取消訂單 更新應徵者的應徵狀態
	@Override
	public int cancelApply(Integer serviceId) throws Exception {
		if (comApplicantDao.updateAllStatus(serviceId) > 0) {
			return serviceId;
//			return "更改成功："+serviceId.toString()+"比資料受影響";
		} else {
			return -1;
		}
	}

	// 更改評價
	@Override
	public int updateRate(ComOrder comOrder) throws Exception {
		System.out.println("評價");
		if (comOrder.getPosterStatus() == 1) {
			if (comOrder.getMemberNo() == comOrder.getOrderPoster()) {
				System.out.println("我為刊登人(陪伴者)");
				return comOrderDao.rateUpdateCompanionOther(comOrder);
			}else {
				System.out.println("我為購買人(陪伴者)");
				return comOrderDao.rateUpdateCompanionMe(comOrder);
			}
		} else {//刊登人為顧客
			if (comOrder.getMemberNo() == comOrder.getOrderPoster()) {
				System.out.println("我為刊登人(顧客)");
				return comOrderDao.rateUpdateCompanionMe(comOrder);
			}else {
				System.out.println("我為購買人(顧客)");
				return comOrderDao.rateUpdateCompanionOther(comOrder);
			}				

		}
	}
}
