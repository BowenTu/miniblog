package top.bowentu.service;

import top.bowentu.pojo.Blog;
import top.bowentu.pojo.BlogDetail;
import top.bowentu.pojo.User;

import java.util.List;

public interface IBlogService {
    List<Blog> insertBlogListByUserId(Integer userid);

    List<BlogDetail> findBlogDetailListByUserId(Integer userid);

    List<Blog> findBlogListByUserName(String username);

    void insertBlog(Integer userid, String blogContent);

    List<BlogDetail> getFollowingBlogDetail(Integer uid);

    List<BlogDetail> getAllRecentBlogDetail();

    void deleteBlog(Integer uid, Integer blogId);
}
