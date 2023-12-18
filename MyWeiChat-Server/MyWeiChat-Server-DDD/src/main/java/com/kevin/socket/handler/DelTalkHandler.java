package com.kevin.socket.handler;

import com.kevin.application.UserService;
import com.kevin.chat.protocol.talk.DelTalkRequest;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;


/**
 
 */
public class DelTalkHandler extends MyBizHandler<DelTalkRequest> {

    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}
