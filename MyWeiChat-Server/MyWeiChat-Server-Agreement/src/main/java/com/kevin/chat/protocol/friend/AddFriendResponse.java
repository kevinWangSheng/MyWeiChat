package com.kevin.chat.protocol.friend;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

/**
 * @author wang
 * @create 2023-12-18-20:34
 */
public class AddFriendResponse extends Packet {
    private String friendId;    // 朋友ID

    private String friendNickName; // 朋友昵称

    private String friendHead; // 朋友头像

    public AddFriendResponse(String friendId, String friendNickName, String friendHead) {
        this.friendId = friendId;
        this.friendNickName = friendNickName;
        this.friendHead = friendHead;
    }

    public AddFriendResponse() {
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }

    @Override
    public Byte getCommand() {
        return Command.AddFriendResponse;
    }
}
