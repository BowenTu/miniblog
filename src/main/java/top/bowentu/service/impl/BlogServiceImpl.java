package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.BlogMapper;
import top.bowentu.dao.UserMapper;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogDao;
    @Autowired
    private UserMapper userDao;

    @Override
    public List<Blog> getBlogListByUserId(Integer userid) {
        return blogDao.findAllByUserId(userid);
    }

    @Override
    public List<Blog> getBlogListByUserName(String username) {
        return blogDao.findAllByUserName(username);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findUserByUid(Integer userid) {
        User user = userDao.findByUserId(userid);
        System.out.println(user);
        return user;
    }


}
