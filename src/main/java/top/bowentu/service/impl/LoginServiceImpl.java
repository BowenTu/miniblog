package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.common.constant.InformMessage;
import top.bowentu.dao.UserMapper;
import top.bowentu.pojo.User;
import top.bowentu.service.ILoginService;
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userDao;

    @Override
    public boolean checkLogin(String password, User user) {
        if(user!=null&&user.getPassword()!=null&&password.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public boolean insertRegister(String username, String password) {
        User user = userDao.findByUserName(username);
        if(user!=null){
            return false;
        }
        userDao.insertUser(username,password);
        return true;
    }

    @Override
    public String checkRegister(String username, String password, String confirmPassword) {
        String msg;
        if("".equals(username)||"".equals(password)){
            msg= InformMessage.USERNAME_OR_PASSWORD_CAN_NOT_BE_NULL;
        }else if(!password.equals(confirmPassword)){
            msg= InformMessage.CONFIRMPASSWORD_NOT_SAME_WITH_PASSWORD;
        }else if(!insertRegister(username, password)){
            msg= InformMessage.USERNAME_ALREADY_EXIST;
        }else{
            msg= InformMessage.REGISTER_SUCCESS;
        }
        return msg;
    }
}
