package top.bowentu.pojo;

import java.sql.Timestamp;

public class BlogDetail {
    private Integer blogid;

    private Integer userid;

    private Timestamp publishtime;

    private String content;

    private String username;

    private Integer portrait;

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPublishtime() {
        return publishtime.toString().substring(0, publishtime.toString().indexOf("."));
    }

    public void setPublishtime(Timestamp publishtime) {
        this.publishtime = publishtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPortrait() {
        return portrait;
    }

    public void setPortrait(Integer portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "BlogDetail{" +
                "blogid=" + blogid +
                ", userid=" + userid +
                ", publishtime='" + publishtime + '\'' +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", portrait=" + portrait +
                '}';
    }
}
