package top.bowentu.service;

import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;

import java.util.List;

public interface IBlogService {
    List<Blog> getBlogListByUserId(Integer userid);

    List<Blog> getBlogListByUserName(String username);

    User findUserByUserName(String username);

    User findUserByUid(Integer userid);
}
