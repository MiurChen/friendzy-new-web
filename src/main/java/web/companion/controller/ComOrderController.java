package web.companion.controller;

import java.lang.reflect.Member;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import web.companion.dao.ComOrderDao;
import web.companion.dao.impl.ComOrderDaoImpl;
import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;
import web.companion.service.ComOrderListService;
import web.companion.service.Impl.ComOrderListServiceImpl;

@Path("/companion/order")
public class ComOrderController{
	private ComOrderListService orderListService;
	private ComOrderDao comOrderDao;
	
	public ComOrderController() throws NamingException{
		orderListService = new ComOrderListServiceImpl();
		comOrderDao = new ComOrderDaoImpl();
	}
	//取得所有訂單的基本資訊
	@GET
	@Path("/showAll/{memberNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComOrder> showList(@PathParam("memberNo") Integer meberNo) throws Exception{
//		return comOrderDao.seleteAll();
		return orderListService.showAllOrder(meberNo);
	}
	//取得特定ID的訂單詳細資訊
	// FIXME 之後再再補上自己的會員編號
	@GET
	@Path("/showId/{memberNo}/{servicePoster}/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComOrder showOrder(@PathParam("memberNo") Integer meberNo , @PathParam("servicePoster") Integer poster , @PathParam("orderId") Integer orderId) throws Exception {
//		return comOrderDao.selectPosterMeBy(id);
//		return comOrderDao.selectPosterOtherBy(id);
		System.out.println("memberNo："+meberNo+" Poster:"+poster+" orderId："+orderId);
		return orderListService.showMyOrder(meberNo,poster,orderId);
//		return orderListService.shortOtherOrder(id);
	}
	//變更訂單狀態
	@PUT
	@Path("/StatusUp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ComOrder update(ComOrder comOrder)throws Exception{
		return orderListService.statusUpdate(comOrder);
	}
	//取消訂單 取消所有應徵者
	//從服務ID service_id取得此項目並更改所有【應徵狀態】、【應徵結果】
	@PUT
	@Path("/cancelApply")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String cancelApply(ComApplicant applicant)throws Exception{
		return orderListService.cancelApply(applicant.getServiceId());
//		return null;
	} 
	
	//刊登(新增【服務】&【訂單】)
	
	//預約(新增【應徵者】)
}
