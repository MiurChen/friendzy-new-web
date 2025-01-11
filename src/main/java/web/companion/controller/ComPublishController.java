package web.companion.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.companion.pojo.ComArea;
import web.companion.pojo.ComPublish;
import web.companion.pojo.ComSkill;
import web.companion.service.ComPublishService;
import web.companion.service.Impl.ComPublishServiceImpl;

@Path("/companion/publish")
public class ComPublishController {
	private ComPublishService publishService;
	
	public ComPublishController() throws NamingException{
		publishService = new ComPublishServiceImpl();
	}
	
	@GET
	@Path("/showAll/{memberNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComPublish> showList(@PathParam("memberNo") Integer memberNo) throws Exception{
		return publishService.showOtherPublishAll(memberNo);
	}
	
	@GET
	@Path("/showId/{serviceId}/{memberNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComPublish showpublishDetail(@PathParam("serviceId") Integer serviceId,@PathParam("member") Integer memberNo) throws Exception{
		return publishService.showDetailPublich(memberNo, serviceId);
	}
	
	@GET
	@Path("/skill")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComSkill> getAllSkill()throws Exception{
		return publishService.showAllSkill();
	}
	
	@GET
	@Path("/area")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComArea> getAllArea()throws Exception{
		return publishService.showAllArea();
	}
	
	@POST
	@Path("/addPublish")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int addPublish(ComPublish publish) throws Exception{
		return publishService.addMyPublish(publish);
	}
}
