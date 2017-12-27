package com.moekosu.service.impl;

import com.moekosu.constant.ShoppingCar;
import com.moekosu.dao.ShoppingCarMapper;
import com.moekosu.service.ShoppingCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCarServiceImpl.class);

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Override
    public void addCar(int userId, int goodsId)
    {
        ShoppingCar car = new ShoppingCar();
        Integer carId = null;

        // 检查用户有没有购物车car 取carID 如果没有新建车
        car.setUserId(userId);
        car = shoppingCarMapper.getCarDetails(car);
        if(car == null || (car != null && car.getsId() == null))
        {
            try {
                // 新增购物车，返回购物车主键
                carId = shoppingCarMapper.createCar(userId);
            }
            catch (Exception e){
                logger.error("新增用户专属购物车失败: ", e);
            }
        }
        else
        {
            carId = car.getsId();
        }

        try {
            // TODO 新增商品进购物车
            shoppingCarMapper.addCar(carId, goodsId);
        }
        catch (Exception e){
            logger.error("添加商品进购物车失败: ", e);
        }
    }

    @Override
    public void removeGoods(int userId, int goodsId)
    {
        ShoppingCar car = new ShoppingCar();
        car.setUserId(userId);
        car = shoppingCarMapper.getCarDetails(car);

        if(car != null && car.getsId() != null)
        {
            try {
                shoppingCarMapper.removeGoods(car.getsId(), goodsId);
            }
            catch (Exception e){
                logger.error("从购物车中删除商品失败: ", e);
            }
        }
        else
        {
            logger.error("无用户专属购物车，无法删除其中商品");
        }
    }

    public void removeCar(int userId)
    {

    }

}
