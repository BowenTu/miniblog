package top.bowentu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.bowentu.pojo.Blog;

import java.util.List;
@Repository
public interface BlogMapper {
    @Select("select * from blog where userid=#{userid}")
    List<Blog> findAllByUserId(@Param("userid") Integer userid);

    @Select("select * from blog where userid=(select uid from user where username=#{username})")
    List<Blog> findAllByUserName(@Param("username") String username);

    @Insert("insert into blog(userid,content) value(#{userid},#{blogContent})")
    void saveBlog(@Param("userid") Integer userid, @Param("blogContent") String blogContent);
}
