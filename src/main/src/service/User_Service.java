package service;


import entity.User;

public interface User_Service extends Basic_Service<User> {

    User login(String user, String pass);
}
