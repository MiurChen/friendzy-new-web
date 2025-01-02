package web.member.controller;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.member.dao.MemberDao;
import web.member.dao.Impl.MemberDaoImpl;
import web.member.pojo.Member;

@Path("/test")
public class TestController {
	private MemberDao memberDao;
	
	public TestController() throws NamingException {
		memberDao = new MemberDaoImpl();
	}
	
	@GET
	@Path("/t1")
	@Produces(MediaType.APPLICATION_JSON)
	public Member t1() throws Exception {
		var member = memberDao.seleteBy("john.doe@example.com");
		return member;
	}
}
