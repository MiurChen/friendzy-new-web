package web.companion.dao;

import java.util.List;

import core.dao.dao;
import web.companion.pojo.ComArea;
import web.companion.pojo.ComPublish;
import web.companion.pojo.ComSkill;

public class ComPublishDao implements dao<ComPublish>{

	@Override
	public int insert(ComPublish item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ComPublish item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ComPublish seleteBy(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComPublish> seleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//新加的↓
	//主頁清單取得我以外的刊登項目
	public List<ComPublish> selectPublishList(Integer memberNo) throws Exception{
		
		return null;
	}
	//主頁單項刊登項目詳細資訊
	public ComPublish selectPublishDetail(Integer memberNo, Integer serviceId) throws Exception{
		
		return null;
	}
	
	//刊登取得地區
	public List<ComArea> selectArea() throws Exception{
		
		return null;
	}
	
	//刊登取得專長
	public List<ComSkill> selectSkill() throws Exception{
		
		return null;
	}
	
	//刊登新增服務資料
	public ComPublish addService(ComPublish publish) throws Exception{
		
		return null;
	}
	
	//刊登新增訂單資料
	public ComPublish addorder(ComPublish publish) throws Exception{
		
		return null;
	}
	
}
