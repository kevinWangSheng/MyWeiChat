package com.kevin.socket.handler;

import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.friend.AddFriendRequest;
import com.kevin.chat.protocol.friend.AddFriendResponse;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import javafx.application.Platform;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

/**
 * @author wang
 * @create 2023-12-18-22:22
 */
public class AddFriendHandler extends MyBizHandler<AddFriendResponse> {

    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel ctx, AddFriendResponse msg) {
        IChatMethod chat = uiService.getChatMethod();
        Platform.runLater(() -> {
            chat.addFriendUser(true, msg.getFriendId(), msg.getFriendNickName(), msg.getFriendHead());
        });
    }
}
