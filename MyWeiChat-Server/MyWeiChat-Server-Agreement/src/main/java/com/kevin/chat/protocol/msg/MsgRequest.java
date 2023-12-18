package com.kevin.chat.protocol.msg;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

import java.util.Date;

/**
 * @author wang
 * @create 2023-12-18-21:03
 */
public class MsgRequest extends Packet {
    private String msgText;

    private String userId;

    private String friendId;

    private Integer msgType;

    private Date msgDate;

    @Override
    public Byte getCommand() {
        return Command.MsgRequest;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }
}
