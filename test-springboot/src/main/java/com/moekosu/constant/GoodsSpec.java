package com.moekosu.constant;

import java.io.Serializable;

public class GoodsSpec implements Serializable {

    // 商品id
    private Integer gId;

    // 商品颜色(红 蓝 黑)
    private String color;

    // 商品尺寸(L XL XXL)
    private String size;

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
