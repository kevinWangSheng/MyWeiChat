package com.kevin.socket.handler;

import com.alibaba.fastjson.JSON;
import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.login.LoginResponse;
import com.kevin.chat.protocol.login.dto.ChatRecordDto;
import com.kevin.chat.protocol.login.dto.ChatTalkDto;
import com.kevin.chat.protocol.login.dto.GroupsDto;
import com.kevin.chat.protocol.login.dto.UserFriendDto;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

import java.util.List;

/**
 * @author wang
 * @create 2023-12-18-22:30
 */
public class LoginHandler extends MyBizHandler<LoginResponse> {
    public LoginHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel ctx, LoginResponse msg) {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println("消息内容：" + JSON.toJSONString(msg));
        if (!msg.isSuccess()) {
            System.out.println("登陆失败");
            return;
        }
        Platform.runLater(() -> {
            uiService.getLoginEvent().doLoginSuccess();
            IChatMethod chat = uiService.getChatMethod();
            // 设置用户信息
            chat.setUserInfo(msg.getUserId(), msg.getUserNickName(), msg.getUserHead());
            // 对话框
            List<ChatTalkDto> chatTalkList = msg.getChatTalkList();
            if (null != chatTalkList) {
                chatTalkList.forEach(talk -> {
                            chat.addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getTalkHead(), talk.getTalkSketch(), talk.getTalkDate(), true);
                            switch (talk.getTalkType()) {
                                // 好友
                                case 0:
                                    List<ChatRecordDto> userRecordList = talk.getChatRecordList();
                                    if (null == userRecordList || userRecordList.isEmpty()) return;
                                    for (int i = userRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = userRecordList.get(i);
                                        //  自己的消息
                                        if (0 == chatRecord.getMsgType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            continue;
                                        }
                                        // 好友的消息
                                        if (1 == chatRecord.getMsgType()) {
                                            chat.addTalkMsgUserLeft(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                    break;
                                // 群组
                                case 1:
                                    List<ChatRecordDto> groupRecordList = talk.getChatRecordList();
                                    if (null == groupRecordList || groupRecordList.isEmpty()) return;
                                    for (int i = groupRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = groupRecordList.get(i);
                                        //  自己的消息
                                        if (0 == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            continue;
                                        }
                                        // 他人的消息
                                        if (1 == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgGroupLeft(chatRecord.getTalkId(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }

                        }
                );

            }
            // 群组
            List<GroupsDto> groupsList = msg.getGroupsList();
            if (null != groupsList) {
                groupsList.forEach(groups -> chat.addFriendGroup(groups.getGroupId(), groups.getGroupName(), groups.getGroupHead()));
            }
            // 好友
            List<UserFriendDto> userFriendList = msg.getUserFriendList();
            if (null != userFriendList) {
                userFriendList.forEach(friend -> chat.addFriendUser(false, friend.getFriendId(), friend.getFriendName(), friend.getFriendHead()));
            }
        });
    }
}
