package com.kevin.socket;

import com.kevin.applicaton.UIService;
import com.kevin.infrustruct.utils.BeanUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author wang
 * @create 2023-12-18-22:38
 */
public class NettyClient implements Callable<Channel> {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private EventLoopGroup group = new NioEventLoopGroup();

    private UIService uiService;

    private Channel channel;

    public String inetHost = "127.0.0.1";

    public int inetPort = 7369;

    public NettyClient(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    public Channel call() throws Exception {
        ChannelFuture future = null;
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ,true)
                    .handler(new MyChannelInilizalier(uiService));
            future = bs.connect(inetHost, inetPort).syncUninterruptibly();
            channel = future.channel();
            BeanUtil.putBean("channel",channel);
        } catch (Exception e) {
            logger.error("socket client start error", e.getMessage());
        } finally {
            if(null != future || future.isSuccess()){
                logger.info("socket client start done. ");
            }else {
                logger.info("socket client start error. ");
            }
        }
        return  channel;
    }

    public void destory(){
        if(null == channel) return;

        group.shutdownGracefully();
        channel.close();
    }

    public boolean isActive(){
        return null != channel && channel.isActive();
    }

    public Channel channel(){
        return channel;
    }
}
