package top.bowentu.dao;

import org.springframework.stereotype.Repository;
import top.bowentu.common.utils.RedisPool;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRelationDao {
    private static final String FOLLOWER_NAMESPACE="followers:";
    private static final String FOLLOWING_NAMESPACE="followings:";

    public Set<Integer> getFollowerIds(Integer userid) {
        Set<String> followerIds = RedisPool.smembers(FOLLOWER_NAMESPACE + userid);
        return convert2IntegerSet(followerIds);
    }

    public void addFollow(Integer userid, Integer theUserid) {
        RedisPool.sadd(FOLLOWER_NAMESPACE+theUserid, userid+"");
        RedisPool.sadd(FOLLOWING_NAMESPACE+userid, theUserid+"");
    }

    public Set<Integer> getFollowingIds(Integer userid) {
        Set<String> followingIds = RedisPool.smembers(FOLLOWING_NAMESPACE + userid);
        return convert2IntegerSet(followingIds);
    }

    public void delFollow(Integer userid, Integer theUserid) {
        RedisPool.srem(FOLLOWER_NAMESPACE+theUserid, userid+"");
        RedisPool.srem(FOLLOWING_NAMESPACE+userid, theUserid+"");
    }

    private Set<Integer> convert2IntegerSet(Set<String> stringSet){
        Set<Integer> integerSet = new HashSet<>();
        for(String s:stringSet){
            integerSet.add(Integer.parseInt(s));
        }
        return integerSet;
    }

    public Integer getFollowerNum(Integer userid) {
        return RedisPool.scard(FOLLOWER_NAMESPACE+userid).intValue();
    }

    public Integer getFollowingNum(Integer userid) {
        return RedisPool.scard(FOLLOWING_NAMESPACE+userid).intValue();
    }
}
