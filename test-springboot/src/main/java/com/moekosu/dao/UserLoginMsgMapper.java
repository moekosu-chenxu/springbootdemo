package com.moekosu.dao;

import com.moekosu.constant.UserLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginMsgMapper {

    /**
     * get all user login message list
     * @return list
     */
    List<UserLogin> getUserLoginMsgList();

}
