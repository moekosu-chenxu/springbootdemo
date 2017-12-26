package com.moekosu.constant;

import java.util.List;

/**
 * 活动类（包含奖品列表）
 */
public class Activity {

    private String activityId;

    private String activityName;

    private List<LotteryPrize> prizeList;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<LotteryPrize> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<LotteryPrize> prizeList) {
        this.prizeList = prizeList;
    }
}
