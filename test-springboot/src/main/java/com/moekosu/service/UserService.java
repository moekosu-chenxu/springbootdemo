package com.moekosu.service;

import com.moekosu.constant.User;
import com.moekosu.constant.UserLogin;

import java.util.List;

public interface UserService {

    List<User> getAllUserList();

    List<UserLogin> getUserLoginMsgList();

}
