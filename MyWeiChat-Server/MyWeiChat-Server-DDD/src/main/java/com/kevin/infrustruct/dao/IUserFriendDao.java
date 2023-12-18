package com.kevin.infrustruct.dao;

import com.kevin.infrustruct.po.UserFriend;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 
 */
@Mapper
public interface IUserFriendDao {

    List<String> queryUserFriendIdList(String userId);

    UserFriend queryUserFriendById(UserFriend req);

    void addUserFriendList(List<UserFriend> list);

}
