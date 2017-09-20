package serviceimpl;


import entity.Store;
import mapper.Store_Mapper;
import org.springframework.stereotype.Service;
import service.Store_Service;
import utils.JsonData1;

import javax.annotation.Resource;

@Service("Store_ServiceImpl")
public class Store_ServiceImpl extends Basic_ServiceImpl<Store> implements Store_Service {
	@Resource(name="Store_Mapper")
	Store_Mapper mapper;

	@Override
	public void update(Store store) {
		JsonData1 jd1 = new JsonData1();
		jd1.setId(store.getId());

		jd1.setAttrName("count");
		jd1.setO(store.getCount());
		mapper.updateAttr(jd1);
		jd1.setAttrName("cbprice");
		jd1.setO(store.getCbprice());
		mapper.updateAttr(jd1);
	}
}
