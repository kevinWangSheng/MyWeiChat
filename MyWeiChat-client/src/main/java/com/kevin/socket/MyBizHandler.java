package com.kevin.socket;

import com.kevin.applicaton.UIService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wang
 * @create 2023-12-18-22:17
 */
public abstract class MyBizHandler<T> extends SimpleChannelInboundHandler<T> {
    protected UIService uiService;

    public MyBizHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, T t) throws Exception {
        channelRead(channelHandlerContext,t);
    }

    public abstract void channelRead(Channel ctx, T msg);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channel is out of connection");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("occur the exception and the exception cause is :"+cause.getCause());
    }
}
