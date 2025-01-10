package web.companion.service.Impl;

import java.util.List;

import javax.naming.NamingException;

import web.companion.dao.ComPublishDao;
import web.companion.dao.impl.ComPublishDaoImpl;
import web.companion.pojo.ComArea;
import web.companion.pojo.ComPublish;
import web.companion.pojo.ComSkill;
import web.companion.service.ComPublishService;

public class ComPublishServiceImpl implements ComPublishService{
	private ComPublishDao comPublishDao;
	
	public ComPublishServiceImpl() throws NamingException{
		comPublishDao = new ComPublishDaoImpl();
	}

	@Override
	public List<ComPublish> showOtherPublishAll(Integer memberNo) throws Exception {
		return comPublishDao.selectPublishList(memberNo);
	}

	@Override
	public ComPublish showDetailPublich(Integer memberNo, Integer serviceId) throws Exception {
		return comPublishDao.selectPublishDetail(memberNo, serviceId);
	}

	@Override
	public String addMyPublish(ComPublish publish) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComSkill> showAllSkill() throws Exception {
		return comPublishDao.selectSkill();
	}

	@Override
	public List<ComArea> showAllArea() throws Exception {
		return comPublishDao.selectArea();
	}
	
	
	
}