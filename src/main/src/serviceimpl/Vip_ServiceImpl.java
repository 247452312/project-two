package serviceimpl;


import entity.Vip;
import mapper.Vip_Mapper;
import org.springframework.stereotype.Service;
import service.Vip_Service;
import utils.*;

import javax.annotation.Resource;
import java.util.List;

@Service("Vip_ServiceImpl")
public class Vip_ServiceImpl extends Basic_ServiceImpl<Vip> implements Vip_Service {
    @Resource(name = "Vip_Mapper")
    Vip_Mapper mapper;

    @Override
    public void insert(Vip vip) {
        vip.setStatus(1);
        vip.setCreatedate(Info.getNow());
        super.insert(vip);
    }

    @Override
    public void update(Vip vip) {
        Vip v = mapper.getById(vip.getId());
        vip.setCreatedate(v.getCreatedate());
        vip.setUserid(v.getUserid());
        vip.setStatus(v.getStatus());
        super.update(vip);
    }




    public void updateStatus(int id) {
        Vip v = mapper.getById(id);
        if (v.getStatus() == 1) {
            mapper.updateAttr(new JsonData1("status", id, 0));
        } else {
            mapper.updateAttr(new JsonData1("status", id, 1));
        }
    }

}

