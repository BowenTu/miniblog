package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.UserMapper;
import top.bowentu.pojo.User;
import top.bowentu.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userDao;

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findByUserId(Integer userid) {
        return userDao.findByUserId(userid);
    }
}
