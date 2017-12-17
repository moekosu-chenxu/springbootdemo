package com.moekosu.service.impl;

import com.moekosu.constant.User;
import com.moekosu.constant.UserLogin;
import com.moekosu.dao.UserLoginMsgMapper;
import com.moekosu.dao.UserMapper;
import com.moekosu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMsgMapper userLoginMsgMapper;

    @Override
    public List<User> getAllUserList()
    {
        return userMapper.getUserList();
    }

    @Override
    public List<UserLogin> getUserLoginMsgList()
    {
        return userLoginMsgMapper.getUserLoginMsgList();
    }

}
