package web.companion.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.companion.pojo.ComApplicant;
import web.companion.service.ComApplicantService;
import web.companion.service.Impl.ComApplicantServiceImpl;

@Path("/companion/appoint")
public class ComApplicantController{
	private ComApplicantService ApplicantService;
	
	public ComApplicantController() throws NamingException{
		ApplicantService = new ComApplicantServiceImpl();
	}
	
	//取得所有應徵
	@GET
	@Path("/showAll/{memberNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComApplicant> showAllApplicants(@PathParam("memberNo") Integer memberNo) throws Exception{
		return ApplicantService.showAllApplocant(memberNo);
	}
	
	//取得單筆詳細資訊
	@GET
	@Path("/showId/{memberNo}/{account}/{serviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComApplicant showApplicant(@PathParam("memberNo") Integer meberNo , @PathParam("servicePoster") Integer account , @PathParam("serviceId") Integer serviceId)throws Exception{
		return ApplicantService.showApplocantById(meberNo, account, serviceId);
	}
	
	
	
}
