package web.companion.dao;

import java.rmi.server.ExportException;
import java.util.List;

import core.dao.dao;
import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;

public class ComApplicantDao implements dao<ComApplicant> {

	@Override
	public int insert(ComApplicant item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ComApplicant item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ComApplicant seleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComApplicant> seleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	//新加的↓
	public int applicantAccountUpdate (ComApplicant applicant) throws Exception{
		
		return -1;
	}
	public int applyStatusUpdate(Integer serviceId) throws Exception{
		
		return -1;
	}

	
}
