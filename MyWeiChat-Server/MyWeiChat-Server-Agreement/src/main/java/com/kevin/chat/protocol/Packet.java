package com.kevin.chat.protocol;

import com.kevin.chat.protocol.friend.AddFriendRequest;
import com.kevin.chat.protocol.friend.AddFriendResponse;
import com.kevin.chat.protocol.friend.SearchFriendRequest;
import com.kevin.chat.protocol.friend.SearchFriendResponse;
import com.kevin.chat.protocol.login.LoginRequest;
import com.kevin.chat.protocol.login.LoginResponse;
import com.kevin.chat.protocol.login.ReconnectRequest;
import com.kevin.chat.protocol.msg.MsgGroupRequest;
import com.kevin.chat.protocol.msg.MsgGroupResponse;
import com.kevin.chat.protocol.msg.MsgRequest;
import com.kevin.chat.protocol.msg.MsgResponse;
import com.kevin.chat.protocol.talk.DelTalkRequest;
import com.kevin.chat.protocol.talk.TalkNoticeRequest;
import com.kevin.chat.protocol.talk.TalkNoticeResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-12-18-20:02
 */
public abstract class Packet {
    private static final Map<Byte,Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();

    static{
        packetTypeMap.put(Command.LoginRequest, LoginRequest.class);
        packetTypeMap.put(Command.LoginResponse, LoginResponse.class);
        packetTypeMap.put(Command.MsgRequest, MsgRequest.class);
        packetTypeMap.put(Command.MsgResponse, MsgResponse.class);
        packetTypeMap.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);
        packetTypeMap.put(Command.TalkNoticeResponse, TalkNoticeResponse.class);
        packetTypeMap.put(Command.SearchFriendRequest, SearchFriendRequest.class);
        packetTypeMap.put(Command.SearchFriendResponse, SearchFriendResponse.class);
        packetTypeMap.put(Command.AddFriendRequest, AddFriendRequest.class);
        packetTypeMap.put(Command.AddFriendResponse, AddFriendResponse.class);
        packetTypeMap.put(Command.DelTalkRequest, DelTalkRequest.class);
        packetTypeMap.put(Command.MsgGroupRequest, MsgGroupRequest.class);
        packetTypeMap.put(Command.MsgGroupResponse, MsgGroupResponse.class);
        packetTypeMap.put(Command.ReconnectRequest, ReconnectRequest.class);
    }


    public static Class<? extends Packet> getPacket(Byte command){
        return packetTypeMap.get(command);
    }

    public abstract Byte getCommand();
}
