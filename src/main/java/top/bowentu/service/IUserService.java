package top.bowentu.service;

import top.bowentu.pojo.ColumnCount;
import top.bowentu.pojo.User;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User findByUserName(String username);

    User findByUserId(Integer userid);

    Set<User> getFollowers(Integer userid);

    Set<Integer> getFollowerIds(Integer userid);

    List<User> findAll();

    void follow(Integer userid, Integer theUserid);

    Set<User> getFollowings(Integer userid);

    void delFollow(Integer userid, Integer theUserid);

    ColumnCount getUserColumnCount(Integer userid);
}
