package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.UserMapper;
import top.bowentu.pojo.User;
import top.bowentu.service.ILoginService;
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userDao;
    @Override
    public boolean login(String username, String password) {
        User user = userDao.findByUserName(username);
        System.out.println(user);
        if(user.getPassword()!=null&&password.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public boolean register(String username, String password) {
        User user = userDao.findByUserName(username);
        if(user!=null){
            return false;
        }
        userDao.insertUser(username,password);
        return true;
    }
}
