package com.kevin.chat.protocol.friend;

import com.kevin.chat.protocol.Command;
import com.kevin.chat.protocol.Packet;
import com.kevin.chat.protocol.friend.dto.UserDto;

import java.util.List;

/**
 * @author wang
 * @create 2023-12-18-20:37
 */
public class SearchFriendResponse extends Packet {

    private List<UserDto> list;

    public SearchFriendResponse() {
    }

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendResponse;
    }
}
