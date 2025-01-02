package web.member.service;

import web.member.pojo.Member;

public interface MemberService {
	String register(Member member) throws Exception;
	
	String login(Member member) throws Exception;
	
	String forget(Member member) throws Exception;
}
