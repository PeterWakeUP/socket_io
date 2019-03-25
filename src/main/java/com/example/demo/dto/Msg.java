package com.example.demo.dto;

/**
 * Created by 苏文辉 on 2019/3/18.
 */
public class Msg {

    String userName;

    String content;

    String room;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
