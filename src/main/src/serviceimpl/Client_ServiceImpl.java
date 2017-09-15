package serviceimpl;


import entity.Client;
import entity.Vip;
import mapper.Client_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Client_Service;
import utils.Info;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("Client_ServiceImpl")
public class Client_ServiceImpl extends Basic_ServiceImpl<Client> implements Client_Service {
	@Resource(name="Client_Mapper")
    Client_Mapper mapper;

    @Override
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

    public void selectByAll(int[] trem, int[] compare, String[] text, int[] join, ModelMap m) {
        SeachInfo sea = new SeachInfo();
        StringBuilder sb = new StringBuilder("where ");
        sea.setTable("Client");
        for (int i = 0; i < trem.length; i++) {
            sea.setCol(StatusUtils.ClientSelectMap.get(trem[i]));
            sea.setMath(StatusUtils.compareMap.get(compare[i]));
            sea.setValue(text[i]);
            sb.append(sea.getNoWhere());
            if (join[i] == 0) {
                sb.append(" and ");
            } else {
                sb.append(" or  ");
            }
        }
        String where = sb.toString().substring(0, sb.length() - 4);
        sea.setWhere(where);
        sea.setPageno(1);
        sea.setRowcount(mapper.getSize(sea));
        List<Client> list = mapper.getAll(sea);
        m.put("list", list);
        m.put("info", sea);
    }
}
