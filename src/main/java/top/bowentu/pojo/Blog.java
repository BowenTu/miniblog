package top.bowentu.pojo;

import java.sql.Timestamp;

public class Blog {
    private Integer blogid;
    private Integer userid;
    private Timestamp publishtime;
    private String content;

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

    public void setPublishtime(String publishtime) {
        this.publishtime = Timestamp.valueOf(publishtime);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogid=" + blogid +
                ", userid=" + userid +
                ", publishtime=" + publishtime +
                ", content='" + content + '\'' +
                '}';
    }
}
