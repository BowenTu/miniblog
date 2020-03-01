package top.bowentu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.bowentu.pojo.Blog;

import java.util.List;
@Repository
public interface BlogMapper {
    @Select("select * from blog where userid=#{userid}")
    List<Blog> findAllByUserId(Integer userid);
}
