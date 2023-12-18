package com.kevin.chat.protocol.talk;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

/**
 * @author wang
 * @create 2023-12-18-21:06
 */
public class TalkNoticeRequest extends Packet {
    private String userId;       // 用户ID
    private String friendUserId; // 好友ID
    private Integer talkType;    // 对话框类型[0好友、1群组]
    @Override
    public Byte getCommand() {
        return Command.TalkNoticeRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TalkNoticeRequest(String userId, String friendUserId, Integer talkType) {
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.talkType = talkType;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }
}
