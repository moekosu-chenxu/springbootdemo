package com.moekosu.constant;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {

    // 商品id
    private Integer gId;

    // 商品名称
    private String goodsName;

    // 类型
    private String goodsType;

    // 规格 TODO
    private List<GoodsSpec> goodsSpecList;

    // 描述
    private String goodsDesc;

    // 购买数量(给购物车用，这里懒得用继承解耦)
    private String goodsNum;

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public List<GoodsSpec> getGoodsSpecList() {
        return goodsSpecList;
    }

    public void setGoodsSpecList(List<GoodsSpec> goodsSpecList) {
        this.goodsSpecList = goodsSpecList;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }
}
