package com.moekosu.service.impl;

import com.moekosu.constant.Activity;
import com.moekosu.constant.LotteryPrize;
import com.moekosu.service.LotteryService;
import com.moekosu.service.abstractImpl.LotteryAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 抽奖类1：用户从定义的多个奖品(包含不同中奖率)中抽奖，抽取奖品
 */
@Service("prizeLottery")
public class PrizeLotteryServiceImpl extends LotteryAbstractService {

    private static final Logger logger = LoggerFactory.getLogger(PrizeLotteryServiceImpl.class);

    @Override
    public String Lottery(String activityId)
    {
        //
        Integer lotteryCount = 0; //抽奖剩余次数
        LotteryPrize prize; //奖品

        // TODO 判断用户是否正在抽奖 防止重复提交 根据Redis抽奖状态判断

        // TODO 获取用户信息，获取当前活动detail

        // TODO 判断用户剩余抽奖次数是否足够

        // TODO 正式抽奖
        // TODO 1.用户抽奖次数-1
        // TODO 2.记录用户抽奖历史记录
        // TODO 3.抽奖
        prize = lotteryDetail(activityId);
        if(prize != null)
        {
            // TODO 记录中奖记录 db
            // TODO 下发奖品
            // TODO 下发邮件/短信

            return prize.getPrizeName();
        }

        return "";
    }

    /**
     * 具体抽奖方法
     * @param activityId
     * @return
     */
    private LotteryPrize lotteryDetail(String activityId)
    {
        logger.debug("start lottery");

        // TODO 活动信息表1 奖品信息表2 1对多
        Activity currActivity = getActivityDetail(activityId);
        List<LotteryPrize> prizeList = currActivity.getPrizeList();
        LotteryPrize winPrize = new LotteryPrize(); // 保存获得的奖品

        //
        if(prizeList != null && prizeList.size() > 0)
        {
            // 100,0000内随机数
            Random r = new Random();
            int random = r.nextInt(1000000) +1;

            int rate = 0;
            for (LotteryPrize prize : prizeList)
            {
                // 中奖率阶乘10000
                int rateRange = prize.getPrizeRate().multiply(new BigDecimal(10000)).intValue();
                //
                int mixRate = rate;
                int maxRate = rate + rateRange;

                logger.debug("抽奖随机数="+random+",抽奖范围("+mixRate+","+maxRate+")");

                // 随机数是否落在奖品范围内 && 是否还有奖品
                if((random > mixRate && random <= maxRate) && checkHasPrize(prize.getPrizeId()))
                {
                    // 中奖
                    logger.debug("中奖");
                    // 检测用户权限 是否可以获得奖品
                    if(checkUserPrizeAuthority())
                    {
                        winPrize = prize;
                        break;
                    }
                    logger.debug("!!!无权限获得奖品");
                }
                logger.debug("未中奖");
                // 下个抽奖区间
                rate = maxRate;
            }
        }

        return winPrize;
    }

    /**
     * 获取活动详情 包含预设置的奖品列表（模拟）
     * @param activityId
     * @return
     */
    private Activity getActivityDetail(String activityId)
    {
        Activity activity = new Activity();
        List<LotteryPrize> list = new ArrayList<LotteryPrize>();

        LotteryPrize p1 = new LotteryPrize();
        p1.setPrizeId("1");
        p1.setPrizeName("prize1");
        p1.setPrizeRate(new BigDecimal(33));

        LotteryPrize p2 = new LotteryPrize();
        p2.setPrizeId("2");
        p2.setPrizeName("prize2");
        p2.setPrizeRate(new BigDecimal(33));

        LotteryPrize p3 = new LotteryPrize();
        p3.setPrizeId("3");
        p3.setPrizeName("prize3");
        p3.setPrizeRate(new BigDecimal(34));

        list.add(p1);
        list.add(p2);
        list.add(p3);
        activity.setPrizeList(list);
        return activity;
    }

    /**
     * 检测是否还有剩余奖品
     * @return
     */
    private boolean checkHasPrize(String prizeId)
    {
        // TODO 模拟
        return true;
    }

    /**
     * 检测用户是否有获得奖品的权限
     * @return
     */
    private boolean checkUserPrizeAuthority()
    {
        // TODO 模拟
        return true;
    }


}
