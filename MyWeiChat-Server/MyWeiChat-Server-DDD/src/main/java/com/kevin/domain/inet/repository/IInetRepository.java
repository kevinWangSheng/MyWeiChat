package com.kevin.domain.inet.repository;



import com.kevin.domain.inet.model.ChannelUserInfo;
import com.kevin.domain.inet.model.ChannelUserReq;

import java.util.List;

/**
 
 */
public interface IInetRepository {

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
