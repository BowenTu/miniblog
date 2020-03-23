package top.bowentu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bowentu.dao.BlogMapper;
import top.bowentu.dao.UserMapper;
import top.bowentu.dao.UserRelationDao;
import top.bowentu.pojo.ColumnCount;
import top.bowentu.pojo.User;
import top.bowentu.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userDao;
    @Autowired
    private UserRelationDao userRelationDao;
    @Autowired
    private BlogMapper blogDao;

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findByUserId(Integer userid) {
        return userDao.findByUserId(userid);
    }

    @Override
    public Set<User> getFollowers(Integer userid) {
        Set<Integer> followerIds = userRelationDao.getFollowerIds(userid);
        Set<User> followers = new HashSet<>();
        for(int fid:followerIds){
            followers.add(userDao.findByUserId(fid));
        }
        return followers;
    }

    @Override
    public Set<Integer> getFollowerIds(Integer userid) {
        return userRelationDao.getFollowerIds(userid);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void follow(Integer userid, Integer theUserid) {
        userRelationDao.addFollow(userid, theUserid);
    }

    @Override
    public Set<User> getFollowings(Integer userid) {
        Set<Integer> followingIds = userRelationDao.getFollowingIds(userid);
        Set<User> followings = new HashSet<>();
        for(int fid:followingIds){
            followings.add(userDao.findByUserId(fid));
        }
        return followings;
    }

    @Override
    public void delFollow(Integer userid, Integer theUserid) {
        userRelationDao.delFollow(userid, theUserid);
    }

    @Override
    public ColumnCount findUserColumnCount(Integer userid) {
        ColumnCount cc = new ColumnCount();
        cc.setBlogNum(blogDao.findBlogNumByUserId(userid));
        cc.setFollowerNum(userRelationDao.getFollowerNum(userid));
        cc.setFollowingNum(userRelationDao.getFollowingNum(userid));
        cc.setUserNum(userDao.findUserNum());
        return cc;
    }
}
