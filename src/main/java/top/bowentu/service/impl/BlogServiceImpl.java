package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.*;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.BlogDetail;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogDao;
    @Autowired
    private UserTimeLineDao userTimeLineDao;
    @Autowired
    private UserRelationDao userRelationDao;
    @Autowired
    private BlogCacheDao blogCacheDao;
    @Autowired
    private UserMapper userDao;

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
        Blog blog = new Blog();
        blog.setUserid(userid);
        blog.setContent(blogContent);
        blog.setPublishtime(getCurrentTime());
        blogDao.saveBlog(blog);
        blogCacheDao.addBlog(blog);
        userTimeLineDao.add(userid,blog.getBlogid());
    }

    private String getCurrentTime() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time.toString().substring(0, time.toString().indexOf("."));
    }

    @Override
    public List<BlogDetail> getBlogDetailListAccordToTime() {
        return blogDao.getBlogDetailListAccordToTime();
    }

    @Override
    public List<BlogDetail> getFollowingBlogDetail(Integer userid) {
        List<Integer> blogIds = userTimeLineDao.get(userid, userRelationDao.getFollowingIds(userid));
        return blogIds2Details(blogIds);
    }

    @Override
    public List<BlogDetail> getAllRecentBlogDetail() {
        List<Integer> blogIds = userTimeLineDao.get(-1, userDao.getAllUserIds());
        return blogIds2Details(blogIds);
    }

    private BlogDetail blog2Detail(Blog blog) {
        User user = userDao.findByUserId(blog.getUserid());
        BlogDetail blogDetail = new BlogDetail();
        blogDetail.setBlogid(blog.getBlogid());
        blogDetail.setUserid(blog.getUserid());
        blogDetail.setContent(blog.getContent());
        blogDetail.setPublishtime(blog.getPublishtime());
        blogDetail.setUsername(user.getUsername());
        blogDetail.setPortrait(user.getPortrait());
        return blogDetail;
    }

    private List<BlogDetail> blogIds2Details(List<Integer> blogIds) {
        List<BlogDetail> blogDetailList = new ArrayList<>();
        for (Integer blogid : blogIds) {
            Blog blog = blogCacheDao.getBlog(blogid);
            BlogDetail blogDetail = blog2Detail(blog);
            blogDetailList.add(blogDetail);
        }
        return blogDetailList;
    }
}
