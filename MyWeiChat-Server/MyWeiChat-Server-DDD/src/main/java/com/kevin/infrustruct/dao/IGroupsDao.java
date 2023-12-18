package com.kevin.infrustruct.dao;

import com.kevin.infrustruct.po.Groups;
import org.apache.ibatis.annotations.Mapper;


/**
 
 */
@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(String groupsId);

}
