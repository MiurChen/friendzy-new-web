package web.companion.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.companion.pojo.ComApplicant;
import web.companion.service.ComApplicantService;
import web.companion.service.Impl.ComApplicantServiceImpl;

@Path("/companion/appoint")
public class ComApplicantController{
	private ComApplicantService applicantService;
	
	public ComApplicantController() throws NamingException{
		applicantService = new ComApplicantServiceImpl();
	}
	
	//取得所有應徵
	@GET
	@Path("/showAll/{memberNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComApplicant> showAllApplicants(@PathParam("memberNo") Integer memberNo) throws Exception{
		return applicantService.showAllApplocant(memberNo);
	}
	
	//取得單筆詳細資訊
	@GET
	@Path("/showId/{memberNo}/{account}/{serviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComApplicant showApplicant(@PathParam("memberNo") Integer meberNo , @PathParam("account") Integer account , @PathParam("serviceId") Integer serviceId)throws Exception{
		System.out.println(account);
		return applicantService.showApplocantById(meberNo, account, serviceId);
	}
	//狀態變更
	@PUT
	@Path("/statusUpdate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int upStatus(ComApplicant applicant) throws Exception{
		return applicantService.statusUpdate(applicant);
	}
	//刊登
	@POST
	@Path("/addApplicant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int addApplicant(Integer serviceId,Integer MemberNo) throws Exception{
		return applicantService.addApplicant(serviceId,MemberNo);
	}
	
}
