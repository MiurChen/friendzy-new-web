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
	public int acceptStatusUpdate (ComApplicant applicant) throws Exception{
		
		return -1;
	}
	public int updateAllStatus(Integer serviceId) throws Exception{
		
		return -1;
	}
	
	public int rejectStatus(ComApplicant applicant) throws Exception{
		
		return -1;
	}

	public List<ComApplicant> showAllApplicant(Integer memberId)throws Exception{
		
		return null;
	} 
	
	public ComApplicant selectAccountMyById(Integer account ,Integer serviceId)throws Exception{
		
		return null;
	}
	
	public ComApplicant selectAccountOtherById(Integer account ,Integer serviceId)throws Exception{
		
		return null;
	}
	
	public int addApplicant(Integer serviceId,Integer memberNo ) throws Exception{
		
		return -1;
	}
	
	public int selectApplicantById(Integer serviceId,Integer memberNo ) throws Exception{
		
		return -1;
	}
	
	
}
