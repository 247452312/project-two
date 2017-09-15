package serviceimpl;


import entity.Checkdetail;
import mapper.Checkdetail_Mapper;
import org.springframework.stereotype.Service;
import service.Checkdetail_Service;

import javax.annotation.Resource;

@Service("Checkdetail_ServiceImpl")
public class Checkdetail_ServiceImpl extends Basic_ServiceImpl<Checkdetail> implements Checkdetail_Service {
	@Resource(name="Checkdetail_Mapper")
    Checkdetail_Mapper mapper;

}
