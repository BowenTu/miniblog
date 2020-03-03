package top.bowentu.service;

import top.bowentu.pojo.User;

public interface IUserService {
    User findByUserName(String username);

    User findByUserId(Integer userid);
}
