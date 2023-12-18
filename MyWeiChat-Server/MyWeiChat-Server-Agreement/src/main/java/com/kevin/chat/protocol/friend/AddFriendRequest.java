package com.kevin.chat.protocol.friend;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

/**
 * @author wang
 * @create 2023-12-18-20:32
 */
public class AddFriendRequest extends Packet {
    private String userId;

    private String friendId;

    public AddFriendRequest(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public AddFriendRequest() {
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

    @Override
    public Byte getCommand() {
        return Command.AddFriendRequest;
    }
}
