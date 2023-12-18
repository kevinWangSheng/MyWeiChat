package com.kevin.application;

import com.kevin.domain.inet.model.ChannelUserInfo;
import com.kevin.domain.inet.model.ChannelUserReq;
import com.kevin.domain.inet.model.InetServerInfo;

import java.util.List;

/**
 
 *
 * 网络信息查询
 */
public interface InetService {

    InetServerInfo queryNettyServerInfo();

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
