package com.kevin.event;

import com.kevin.chat.protocol.login.LoginRequest;
import com.kevin.infrustruct.utils.BeanUtil;
import com.kevin.infrustruct.utils.CacheUtil;
import io.netty.channel.Channel;
import org.itstack.naive.chat.ui.view.login.ILoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang
 * @create 2023-12-18-22:14
 */
public class LoginEvent implements ILoginEvent {
    private Logger logger = LoggerFactory.getLogger(LoginEvent.class);

    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        CacheUtil.userId = userId;
    }
}
