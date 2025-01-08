package web.companion.dao;

import java.rmi.server.ExportException;
import java.util.List;

import core.dao.dao;
import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;

public class ComOrderDao implements dao<ComOrder> {

	@Override
	public int insert(ComOrder item) throws Exception {

		return 0;
	}

	@Override
	public int update(ComOrder item) throws Exception {

		return -1;
	}

	@Override
	public int deleteBy(String id) throws Exception {

		return 0;
	}

	@Override
	public ComOrder seleteBy(String id) throws Exception {

		return null;
	}

	@Override
	public List<ComOrder> seleteAll() throws Exception {

		return null;
	}
	
	
	
	//新加的↓
	public List<ComOrder> showAllOrder(Integer meberNo) throws Exception {

		return null;
	}
	
	public ComOrder selectPosterMeBy(Integer id) throws Exception{
		
		return null;
	}
	
	public ComOrder selectPosterOtherBy(Integer id) throws Exception{
		
		return null;
	}


}
