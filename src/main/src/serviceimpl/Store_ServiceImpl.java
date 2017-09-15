package serviceimpl;


import entity.Store;
import mapper.Store_Mapper;
import org.springframework.stereotype.Service;
import service.Store_Service;

import javax.annotation.Resource;

@Service("Store_ServiceImpl")
public class Store_ServiceImpl extends Basic_ServiceImpl<Store> implements Store_Service {
	@Resource(name="Store_Mapper")
	Store_Mapper mapper;

}
