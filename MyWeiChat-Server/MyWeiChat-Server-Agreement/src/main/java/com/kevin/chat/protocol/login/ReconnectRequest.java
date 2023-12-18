package com.kevin.chat.protocol.login;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

/**
 * @author wang
 * @create 2023-12-18-20:58
 */
public class ReconnectRequest extends Packet {

    private String userId;

    public ReconnectRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.ReconnectRequest;
    }
}
