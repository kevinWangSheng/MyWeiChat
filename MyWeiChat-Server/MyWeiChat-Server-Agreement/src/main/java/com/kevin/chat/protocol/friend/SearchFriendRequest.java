package com.kevin.chat.protocol.friend;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;

/**
 * @author wang
 * @create 2023-12-18-20:36
 */
public class SearchFriendRequest extends Packet {
    private String userId;

    private String searchKey;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public SearchFriendRequest(String userId, String searchKey) {
        this.userId = userId;
        this.searchKey = searchKey;
    }

    @Override
    public Byte getCommand() {
        return  Command.SearchFriendRequest;
    }
}
