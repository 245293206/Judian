package com.example.ytian.young.moduel.bean;

/**
 * Created by YTian on 2017/8/14.
 */
//用户账号信息的bean类
public class UserInfo {
    private String username;
    private String hxId; //环信id
    private String nick; //用户昵称
    private String photo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserInfo(String username) {
        this.username = username;
        this.hxId = username;
        this.nick = username;
    }
    public UserInfo() {

    }
}
