package top.bowentu.service;

import top.bowentu.pojo.User;

public interface ILoginService {
    User findByUserName(String username);

    boolean checkLogin(String password, User user);

    public boolean register(String username, String password);

    String checkRegister(String username, String password, String confirmPassword);
}
