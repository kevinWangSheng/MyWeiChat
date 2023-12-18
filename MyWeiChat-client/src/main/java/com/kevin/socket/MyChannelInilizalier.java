package com.kevin.socket;

import com.kevin.applicaton.UIService;
import com.kevin.chat.codec.ObjDecoder;
import com.kevin.chat.codec.ObjEncoder;
import com.kevin.socket.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author wang
 * @create 2023-12-18-22:35
 */
public class MyChannelInilizalier extends ChannelInitializer<SocketChannel> {
    private UIService uiService;

    public MyChannelInilizalier(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ObjDecoder());

        socketChannel.pipeline().addLast(new AddFriendHandler(uiService));
        socketChannel.pipeline().addLast(new LoginHandler(uiService));
        socketChannel.pipeline().addLast(new MsgGroupHandler(uiService));
        socketChannel.pipeline().addLast(new MsgHandler(uiService));
        socketChannel.pipeline().addLast(new SearchFriendHandler(uiService));
        socketChannel.pipeline().addLast(new TalkNoticeHandler(uiService));

        socketChannel.pipeline().addLast(new ObjEncoder());
    }
}
