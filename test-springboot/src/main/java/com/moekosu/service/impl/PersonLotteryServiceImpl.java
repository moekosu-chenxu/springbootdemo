package com.moekosu.service.impl;

import com.moekosu.constant.LotteryUser;
import com.moekosu.service.LotteryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 抽奖类2：单个奖品，从多个不同的用户中抽取中奖的用户
 */
@Service("personLottery")
public class PersonLotteryServiceImpl implements LotteryService{

    // 参与抽奖的用户
    private List<LotteryUser> userList = new ArrayList<>();
    // 抽奖序号
    private int seqNum = 1;

    @Override
    public String Lottery(String activityId)
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

    /**
     * 进入抽奖队列
     * @param userName
     */
    public void setUserList(String userName)
    {
        LotteryUser user = new LotteryUser();
        user.setId(seqNum++);
        user.setUserName(userName);
        this.userList.add(user);
    }

    /**
     * 抽奖结束，清空队列
     */
    public void emptyUserList()
    {
        userList = new ArrayList<>();
        seqNum = 1;
    }

}
