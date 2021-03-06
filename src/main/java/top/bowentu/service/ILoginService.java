package top.bowentu.service;

import top.bowentu.pojo.User;

public interface ILoginService {
    boolean checkLogin(String password, User user);

    public boolean insertRegister(String username, String password);

    String checkRegister(String username, String password, String confirmPassword);
}
