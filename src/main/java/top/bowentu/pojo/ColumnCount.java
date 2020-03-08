package top.bowentu.pojo;

public class ColumnCount {
    private Integer blogNum;
    private Integer followerNum;
    private Integer followingNum;
    private Integer userNum;

    public Integer getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

    public Integer getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(Integer followerNum) {
        this.followerNum = followerNum;
    }

    public Integer getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(Integer followingNum) {
        this.followingNum = followingNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "ColumnCount{" +
                "blogNum=" + blogNum +
                ", followerNum=" + followerNum +
                ", followingNum=" + followingNum +
                ", userNum=" + userNum +
                '}';
    }
}
