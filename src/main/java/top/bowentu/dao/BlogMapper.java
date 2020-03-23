package top.bowentu.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.BlogDetail;

import java.util.List;
@Repository
public interface BlogMapper {
    @Select("select * from blog where userid=#{userid}")
    List<Blog> findAllByUserId(Integer userid);

    @Select("select * from blog where userid=(select uid from user where username=#{username})")
    List<Blog> findAllByUserName(String username);

    @Insert("insert into blog(userid,publishtime,content) value(#{userid},#{publishtime},#{content})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="blogid", before=false, resultType=Integer.class)
    void insertBlog(Blog blog);

    @Select("select * from blog inner join(user) on blog.`userid`=user.`uid` where blog.`userid`=#{userid}")
    List<BlogDetail> findAllDetailByUserId(Integer userid);

    @Select("select count(blogid) from blog where userid=#{userid}")
    Integer findBlogNumByUserId(Integer userid);

    @Select("select * from blog inner join(user) on blog.`userid`=user.`uid` where blogid=#{blogid}")
    BlogDetail findDetailByBlogId(Integer blogid);

    @Select("select * from blog where blogid=#{blogid}")
    Blog findByBlogId(Integer blogid);

    @Select("select * from blog where userid=#{userid}")
    Blog findByUserId(Integer userid);

    @Delete("delete from blog where blogid=#{blogid}")
    void delByBlogId(Integer blogId);
}
