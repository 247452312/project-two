package serviceimpl;


import entity.Checkdetail;
import entity.Checkmain;
import mapper.Checkdetail_Mapper;
import mapper.Checkmain_Mapper;
import org.springframework.stereotype.Service;
import service.Checkmain_Service;

import javax.annotation.Resource;
import java.util.List;

@Service("Checkmain_ServiceImpl")
public class Checkmain_ServiceImpl extends Basic_ServiceImpl<Checkmain> implements Checkmain_Service {
    @Resource(name = "Checkmain_Mapper")
    Checkmain_Mapper mapper;
    @Resource(name = "Checkdetail_Mapper")
    Checkdetail_Mapper cmapper;

    public void insertAll(Checkmain check, List<Checkdetail> checkdetailsList) {
        mapper.insert(check);
        for (Checkdetail checkdetail : checkdetailsList) {
            cmapper.insert(checkdetail);
        }
    }

    public void updateAll(Checkmain check, List<Checkdetail> checkdetailsList) {
        mapper.update(check);
        for (Checkdetail checkdetail : checkdetailsList) {
            cmapper.insert(checkdetail);
        }
    }

    @Override
    public void autocreate(int id) {
        mapper.autocreate(id);
    }

    public Checkmain getNew(){
        return mapper.getNew();
    };
}
