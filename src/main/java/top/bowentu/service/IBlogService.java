package top.bowentu.service;

import top.bowentu.pojo.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> getBlogList(Integer userid);
}
