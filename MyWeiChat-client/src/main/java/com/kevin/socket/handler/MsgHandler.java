package com.kevin.socket.handler;

import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.msg.MsgResponse;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

/**
 * @author wang
 * @create 2023-12-18-22:33
 */
public class MsgHandler extends MyBizHandler<MsgResponse> {

    public MsgHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel ctx, MsgResponse msg) {
        IChatMethod chat = uiService.getChatMethod();
        Platform.runLater(() -> {
            chat.addTalkMsgUserLeft(msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });
    }
}
