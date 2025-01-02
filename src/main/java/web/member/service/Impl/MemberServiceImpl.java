package web.member.service.Impl;

import javax.naming.NamingException;

import org.apache.commons.validator.routines.EmailValidator;

import web.member.dao.MemberDao;
import web.member.dao.Impl.MemberDaoImpl;
import web.member.pojo.Member;
import web.member.service.MemberService;

public class MemberServiceImpl implements MemberService{

	private MemberDao memberDao;

	public MemberServiceImpl() throws NamingException {
		memberDao = new MemberDaoImpl();
	}

	@Override
	public String register(Member member) throws Exception {
		String email = member.getEmail();
		String password = member.getMpassword();
		String name = member.getMember_name();
		if (email == null || password == null || name == null) {
			return "欄位不可為空";
		}

		if (memberDao.seleteBy(email) != null) {
			return "該帳戶已註冊過，請確認後再執行";
		}

		if (!EmailValidator.getInstance().isValid(email)) {
			return "信箱格式錯誤";
		}

		if (password.length() < 8) {
			return "密碼長度需大於8";
		}

		int count = memberDao.insert(member);
		if (count > 0) {
			return null;
		} else {
			return "註冊失敗";
		}

	}

	@Override
	public String login(Member member) throws Exception {
		String email = member.getEmail();
		String password = member.getMpassword();
		if(email == null || password == null) {
			return "欄位不可為空";
		}
		if (!EmailValidator.getInstance().isValid(email)) {
			return "信箱格式錯誤";
		}
		if(memberDao.seleteBy(email) == null) {
			return "該帳號尚未註冊";
		}
		if(memberDao.seleteBy(email).getEmail().equals(email) 
				|| memberDao.seleteBy(password).getMpassword().equals(password)) {
			return null;
		}else {
			return "登入失敗";
		}
	}

	@Override
	public String forget(Member member) throws Exception {
		String email = member.getEmail();
		if(email == null) {
			return "欄位不可為空";
		}
		if (!EmailValidator.getInstance().isValid(email)) {
			return "信箱格式錯誤";
		}
		if(memberDao.seleteBy(email).getEmail().equals(email)) {
			return null; //接API發mail接回去
		}else {
			return "無此用戶";
		}
	}

}
