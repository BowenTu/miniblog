package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.BlogMapper;
import top.bowentu.dao.UserMapper;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.BlogDetail;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogDao;

    @Override
    public List<Blog> getBlogListByUserId(Integer userid) {
        return blogDao.findAllByUserId(userid);
    }

    @Override
    public List<BlogDetail> getBlogDetailListByUserId(Integer userid) {
        return blogDao.findAllDetailByUserId(userid);
    }

    @Override
    public List<Blog> getBlogListByUserName(String username) {
        return blogDao.findAllByUserName(username);
    }

    @Override
    public void saveBlog(Integer userid, String blogContent) {
        blogDao.saveBlog(userid,blogContent);
    }

    @Override
    public List<BlogDetail> getBlogDetailListAccordToTime() {
        return blogDao.getBlogDetailListAccordToTime();
    }


}
