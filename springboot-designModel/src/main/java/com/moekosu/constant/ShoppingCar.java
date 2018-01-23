package com.moekosu.constant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShoppingCar implements Serializable{

    // id
    private Integer sId;

    // 商品列表
    private List<Goods> goodsList;

    // 用户归属
    private int userId;

    // 总价
    private BigDecimal priceSum;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }
}
