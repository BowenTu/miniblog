package top.bowentu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.bowentu.pojo.User;

@Repository
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(String username) ;

    @Insert("insert into user(username,password) value(#{username},#{password})")
    void insertUser(String username, String password);

    @Select("select * from user where uid=#{userid}")
    User findByUserId(Integer userid);
}
