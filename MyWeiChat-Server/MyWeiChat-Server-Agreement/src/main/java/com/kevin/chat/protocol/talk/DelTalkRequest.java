package com.kevin.chat.protocol.talk;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

/**
 * @author wang
 * @create 2023-12-18-21:08
 */
public class DelTalkRequest extends Packet {

    private String userId;

    private String talkId;
    @Override
    public Byte getCommand() {
        return Command.DelTalkRequest;
    }

    public DelTalkRequest(String userId, String talkId) {
        this.userId = userId;
        this.talkId = talkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }
}
