package web.customer.controller;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import web.customer.Service.blackListServiceImpl;
import web.customer.Service.blackListservice;
import web.customer.bean.BlackList;

@Path("/customer/deleteBlackList")
public class DeleteBlackListController {
	private blackListservice blackListservice;
	
	public DeleteBlackListController() throws NamingException {
		blackListservice = new blackListServiceImpl();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer deleteBlackList(BlackList blackList) throws Exception {
		System.out.println("successful remove");
		 Integer result = blackListservice.deleteByID(blackList);
		 return result;
	}
}
