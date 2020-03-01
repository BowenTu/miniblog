package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.BlogMapper;
import top.bowentu.pojo.Blog;
import top.bowentu.service.IBlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getBlogList(Integer userid) {
        List<Blog> result = blogMapper.findAllByUserId(userid);
        return result;
    }
}
