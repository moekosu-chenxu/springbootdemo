package com.moekosu.controller;

import com.moekosu.service.ShoppingCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCarController {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCarController.class);

    @Autowired
    private ShoppingCarService shoppingCarService;

    // TODO 商品加入购物车
    public void add2Car(String goodsId)
    {
        Integer userId = getUserInfo4Login();
        int gId = Integer.valueOf(goodsId);

        shoppingCarService.addCar(userId, gId);
    }

    // TODO 商品删除出购物车
    public void remove4Car(String goodsId)
    {
        int userId = getUserInfo4Login();
        int gId = Integer.valueOf(goodsId);

        shoppingCarService.removeGoods(userId, gId);
    }

    // TODO 结账
    public void buy()
    {

    }

    private Integer getUserInfo4Login()
    {
        // TODO 用户登录信息 session.get UserInfo.getUserId
        // TODO error return null
        // TODO 做一个BaseController做返回
        return 1;
    }

}
