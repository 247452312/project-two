package service;


import entity.Checkmain;

public interface Checkmain_Service extends Basic_Service<Checkmain>{

    public void autocreate(int id);

    public Checkmain getNew();
}
