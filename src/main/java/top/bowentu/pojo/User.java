package top.bowentu.pojo;

public class User {
    private String username;
    private String password;
    private Integer uid;
    private Integer portrait;

    public Integer getPortrait() {
        return portrait;
    }

    public void setPortrait(Integer portrait) {
        this.portrait = portrait;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", uid=" + uid +
                ", portrait=" + portrait +
                '}';
    }
}
