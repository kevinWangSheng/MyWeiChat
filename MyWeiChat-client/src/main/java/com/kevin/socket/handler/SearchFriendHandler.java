package com.kevin.socket.handler;

import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.friend.SearchFriendResponse;
import com.kevin.chat.protocol.friend.dto.UserDto;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

import java.util.List;

/**
 * @author wang
 * @create 2023-12-18-22:34
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendResponse> {
    public SearchFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel ctx, SearchFriendResponse msg) {
        List<UserDto> list = msg.getList();
        if (null == list || list.isEmpty()) return;
        IChatMethod chat = uiService.getChatMethod();
        Platform.runLater(() -> {
            for (UserDto user : list) {
                chat.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });
    }
}
