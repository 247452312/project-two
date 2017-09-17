package serviceimpl;


import entity.Client;
import entity.User;
import entity.Vip;
import mapper.Client_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Client_Service;
import utils.Info;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("Client_ServiceImpl")
public class Client_ServiceImpl extends Basic_ServiceImpl<Client> implements Client_Service {
	@Resource(name="Client_Mapper")
    Client_Mapper mapper;

    public void insert(Client client) {
        client.setCreatedate(Info.getNow());
        super.insert(client);
    }

    @Override
    public void update(Client client) {
        Client c = mapper.getById(client.getId());
        client.setCreatedate(c.getCreatedate());
        client.setUserid(c.getUserid());
        super.update(client);
    }

}
