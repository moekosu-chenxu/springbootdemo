package com.moekosu.constant;

import java.math.BigDecimal;

/**
 * 奖品类
 * @author chenxu
 * @date 2017-12-26
 */
public class LotteryPrize {

    // 奖品id
    private String prizeId;

    // 奖品名称
    private String prizeName;

    // 描述
    private String prizeDesc;

    // 奖品类型(来源：XX活动)
    private String prizeType;

    // 奖品总数
    private int prizeCount;

    // 奖品剩余数量
    private int prizeLeaveCount;

    // 奖品当日可中奖总数
    // TODO 先不做，思路是每日的限额，每天通过job定时计算奖品总的剩余数量
    //private String prizeDayCount;

    // 奖品中奖率
    private BigDecimal prizeRate;

    // 奖品logo图片存放地址(资源服务器)
    private String prizePicPath;

    // 奖品状态(0:未领取;1:已领取)
    private String prizeStatus;

    // 兑换码(生成)
    private String redeemCode;

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeDesc() {
        return prizeDesc;
    }

    public void setPrizeDesc(String prizeDesc) {
        this.prizeDesc = prizeDesc;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public int getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(int prizeCount) {
        this.prizeCount = prizeCount;
    }

    public int getPrizeLeaveCount() {
        return prizeLeaveCount;
    }

    public void setPrizeLeaveCount(int prizeLeaveCount) {
        this.prizeLeaveCount = prizeLeaveCount;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public BigDecimal getPrizeRate() {
        return prizeRate;
    }

    public void setPrizeRate(BigDecimal prizeRate) {
        this.prizeRate = prizeRate;
    }

    public String getPrizePicPath() {
        return prizePicPath;
    }

    public void setPrizePicPath(String prizePicPath) {
        this.prizePicPath = prizePicPath;
    }

    public String getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(String prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode;
    }
}
