package com.kevin.socket.handler;

import com.alibaba.fastjson.JSON;
import com.kevin.application.UserService;
import com.kevin.chat.protocol.friend.SearchFriendRequest;
import com.kevin.chat.protocol.friend.SearchFriendResponse;
import com.kevin.chat.protocol.friend.dto.UserDto;
import com.kevin.domain.user.model.LuckUserInfo;
import com.kevin.socket.MyBizHandler;
import io.netty.channel.Channel;


import java.util.ArrayList;
import java.util.List;

/**
 
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest> {

    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {
        logger.info("搜索好友请求处理：{}", JSON.toJSONString(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        channel.writeAndFlush(response);
    }

}
