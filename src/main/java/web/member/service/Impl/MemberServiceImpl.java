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
		if(email == null || password == null 
				|| email.isEmpty() || password.isEmpty()) {
			return "欄位不可為空";
		}
		if (!EmailValidator.getInstance().isValid(email)) {
			return "信箱格式錯誤";
		}
		Member existMember = memberDao.seleteBy(email);
		if(existMember == null) {
			return "該帳號尚未註冊";
		}
//		if(existMember.getMpassword().equals(password)) {
//			return null;
//		}
		// 驗證密碼
	    if(existMember.getMpassword().equals(password)) {
	        // 密碼正確，將完整會員資訊複製到傳入的member物件
	        member.setMember_no(existMember.getMember_no());
	        member.setMember_name(existMember.getMember_name());
	        member.setMember_nick_name(existMember.getMember_nick_name());
	        member.setMember_pic(existMember.getMember_pic());
	        member.setPhone(existMember.getPhone());
	        member.setIntroduction(existMember.getIntroduction());
	        member.setCompanion_comment(existMember.getCompanion_comment());
	        member.setCompanion_score(existMember.getCompanion_score());
	        member.setCustmer_comment(existMember.getCustmer_comment());
	        member.setCustmer_score(existMember.getCustmer_score());
	        member.setRegistration_time(existMember.getRegistration_time());
	        member.setMember_status(existMember.getMember_status());
	        member.setMember_token(existMember.getMember_token());
	        
	        return null; 
	    }else {
	    	return "登入失敗";
		}
	}

	@Override
	public String forget(Member member) throws Exception {
		String email = member.getEmail();
		if(email == null || email.isEmpty()) {
			return "欄位不可為空";
		}
		if (!EmailValidator.getInstance().isValid(email)) {
			return "信箱格式錯誤";
		}
		Member existMember = memberDao.seleteBy(email);
		if(existMember == null) {
			return "無此用戶";
		}else {
			return null; //API接到信箱連接回來
		}
		
	}

}
