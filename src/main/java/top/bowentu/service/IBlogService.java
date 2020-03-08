package top.bowentu.service;

import top.bowentu.pojo.Blog;
import top.bowentu.pojo.BlogDetail;
import top.bowentu.pojo.User;

import java.util.List;

public interface IBlogService {
    List<Blog> getBlogListByUserId(Integer userid);

    List<BlogDetail> getBlogDetailListByUserId(Integer userid);

    List<Blog> getBlogListByUserName(String username);

    void saveBlog(Integer userid, String blogContent);

    List<BlogDetail> getBlogDetailListAccordToTime();
}
