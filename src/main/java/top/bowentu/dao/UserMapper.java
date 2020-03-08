package top.bowentu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.bowentu.pojo.User;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(@Param("username") String username) ;

    @Insert("insert into user(username,password) value(#{username},#{password})")
    void insertUser(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where uid=#{userid}")
    User findByUserId(@Param("userid") Integer userid);

    @Select("select * from user")
    List<User> findAll();

    @Select("select count(uid) from user")
    Integer getUserNum();
}
