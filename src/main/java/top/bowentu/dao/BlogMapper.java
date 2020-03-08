package top.bowentu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
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
    void saveBlog(Blog blog);

    @Select("SELECT * FROM blog INNER JOIN(USER) ON blog.`userid`=user.`uid` WHERE blog.`userid`=#{userid}")
    List<BlogDetail> findAllDetailByUserId(Integer userid);

    @Select("SELECT COUNT(blogid) FROM blog WHERE userid=#{userid}")
    Integer getBlogNumByUserId(Integer userid);

    @Select("SELECT * FROM blog INNER JOIN(USER) ON blog.`userid`=user.`uid` ORDER BY(publishtime) DESC LIMIT 100")
    List<BlogDetail> getBlogDetailListAccordToTime();

    @Select("SELECT * FROM blog INNER JOIN(USER) ON blog.`userid`=user.`uid` WHERE blogid=#{blogid}")
    BlogDetail findDetailByBlogId(Integer blogid);

    @Select("select * from blog where blogid=#{blogid}")
    Blog findByBlogId(Integer blogid);

    @Select("select * from blog where userid=#{userid}")
    Blog findByUserId(Integer userid);
}
