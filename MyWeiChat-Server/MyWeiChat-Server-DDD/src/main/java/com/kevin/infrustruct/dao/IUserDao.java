package com.kevin.infrustruct.dao;


import com.kevin.domain.inet.model.ChannelUserReq;
import com.kevin.infrustruct.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 
 */
@Mapper
public interface IUserDao {

    String queryUserPassword(String userId);

    User queryUserById(String userId);

    List<User> queryFuzzyUserList(String userId, String searchKey);

    Long queryChannelUserCount(ChannelUserReq req);

    List<User> queryChannelUserList(ChannelUserReq req);

}
