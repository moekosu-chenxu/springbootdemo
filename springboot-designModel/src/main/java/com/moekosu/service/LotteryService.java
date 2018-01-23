package com.moekosu.service;

import com.moekosu.constant.LotteryUser;

import java.util.List;

public interface LotteryService {

    String Lottery(String activityId);

    String Lottery(List<LotteryUser> userList);

}
