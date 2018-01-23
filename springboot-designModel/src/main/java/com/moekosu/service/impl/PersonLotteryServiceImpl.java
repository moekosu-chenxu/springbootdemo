package com.moekosu.service.impl;

import com.moekosu.constant.LotteryUser;
import com.moekosu.service.LotteryService;
import com.moekosu.service.abstractImpl.LotteryAbstractService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 抽奖类2：单个奖品，从多个不同的用户中抽取中奖的用户
 */
@Service("personLottery")
public class PersonLotteryServiceImpl extends LotteryAbstractService {

    @Override
    public String Lottery(List<LotteryUser> userList)
    {
        String prizeUser = "none";
        // 有人参加
        if(userList.size() > 0)
        {
            // 获取抽奖随机数
            int randomNum = userList.size();
            Random r = new Random();
            int random = r.nextInt(randomNum) +1;
            // 抽奖
            for(LotteryUser user : userList)
            {
                if(random == user.getId())
                {
                    // 中奖
                    prizeUser = user.getUserName();
                    break;
                }
            }
        }
        return prizeUser;
    }

}
