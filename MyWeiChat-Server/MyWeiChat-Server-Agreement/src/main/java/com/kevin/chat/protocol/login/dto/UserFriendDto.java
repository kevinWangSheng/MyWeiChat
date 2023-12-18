package com.kevin.chat.protocol.login.dto;

/**
 * @author wang
 * @create 2023-12-18-20:56
 */
public class UserFriendDto {
    private String friendId;    // 好友ID
    private String friendName;  // 好友名称
    private String friendHead;  // 好友头像

    public UserFriendDto(String friendId, String friendName, String friendHead) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendHead = friendHead;
    }

    public UserFriendDto() {
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }
}
