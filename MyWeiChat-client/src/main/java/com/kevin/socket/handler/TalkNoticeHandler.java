package com.kevin.socket.handler;

import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.talk.TalkNoticeResponse;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

/**
 * @author wang
 * @create 2023-12-18-22:34
 */
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeResponse> {
    public TalkNoticeHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel ctx, TalkNoticeResponse msg) {
        IChatMethod chat = uiService.getChatMethod();
        Platform.runLater(() -> {
            chat.addTalkBox(-1, 0, msg.getTalkId(), msg.getTalkName(), msg.getTalkHead(), msg.getTalkSketch(), msg.getTalkDate(), false);
        });
    }
}
