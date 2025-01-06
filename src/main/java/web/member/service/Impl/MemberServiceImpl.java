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
		if(existMember.getMpassword().equals(password)) {
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
 
	 @Override
	    public Member findByEmail(String email) throws Exception {
	        return memberDao.seleteBy(email); // 根據 email 查詢會員資料
	    }

	@Override
	public String savePassword(Member member) {
		String password = member.getMpassword();
	    if (password == null || password.length() < 8) {
	        return "密碼長度需大於8";
	    }
	    if (memberDao.updatePassword(member) > 0) {
	        return null;
	    } else {
	        return "密碼更新失敗";
	    }
	}

	@Override
	public String saveNickname(Member member) {
		String nickname = member.getMember_nick_name();
	    if (nickname != null && nickname.length() > 20) {
	        return "暱稱長度不得超過10個字元";
	    }

	    if (memberDao.updateNickname(member) > 0) {
	        return null;
	    } else {
	        return "暱稱更新失敗";
	    }
	}

	@Override
	public String savePhone(Member member) {
		String phone = member.getPhone();
	    if (phone != null && phone.length() > 20) {
	        return "電話長度不得超過20個字元";
	    }

	    if (memberDao.updatePhone(member) > 0) {
	        return null;
	    } else {
	        return "電話更新失敗";
	    }	
	 }
	
}
