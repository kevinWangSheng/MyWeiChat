package com.kevin.chat.protocol.login.dto;

import java.util.Date;
import java.util.List;

/**
 * @author wang
 * @create 2023-12-18-20:44
 */
public class ChatTalkDto {
    private String talkId;
    private String talkType; // 聊天类型，0好友，1群组

    private String userNickName; // 用户昵称

    private String userHead; // 用户头像

    private String talkSketch;  // 消息简述
    private Date talkDate;      // 消息时间


    private List<ChatRecordDto> chatRecordList;  // 聊天记录

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getTalkType() {
        return talkType;
    }

    public void setTalkType(String talkType) {
        this.talkType = talkType;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getTalkSketch() {
        return talkSketch;
    }

    public void setTalkSketch(String talkSketch) {
        this.talkSketch = talkSketch;
    }

    public Date getTalkDate() {
        return talkDate;
    }

    public void setTalkDate(Date talkDate) {
        this.talkDate = talkDate;
    }

    public List<ChatRecordDto> getChatRecordList() {
        return chatRecordList;
    }

    public void setChatRecordList(List<ChatRecordDto> chatRecordList) {
        this.chatRecordList = chatRecordList;
    }
}
