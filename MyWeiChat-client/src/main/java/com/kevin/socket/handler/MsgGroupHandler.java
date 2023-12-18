package com.kevin.socket.handler;

import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.msg.MsgGroupResponse;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

/**
 * @author wang
 * @create 2023-12-18-22:32
 */
public class MsgGroupHandler extends MyBizHandler<MsgGroupResponse> {
    public MsgGroupHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel ctx, MsgGroupResponse msg) {
        IChatMethod chat = uiService.getChatMethod();
        Platform.runLater(() -> {
            chat.addTalkMsgGroupLeft(msg.getTalkId(), msg.getUserId(), msg.getUserNickName(), msg.getUserHead(), msg.getMsg(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });
    }
}
