package com.moekosu.service.impl;

import com.moekosu.constant.UserLogin;
import com.moekosu.dao.UserLoginMsgMapper;
import com.moekosu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginMsgMapper userLoginMsgMapper;

    public List<UserLogin> getUserLoginMsgList()
    {
        return userLoginMsgMapper.getUserLoginMsgList();
    }

}
