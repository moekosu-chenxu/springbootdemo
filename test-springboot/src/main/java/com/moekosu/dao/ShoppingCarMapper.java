package com.moekosu.dao;

import com.moekosu.constant.ShoppingCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShoppingCarMapper {

    // TODO 表1 User (id name...
    // TODO 表2 ShoppingCar (包含userId外键) (id user_id priceSum
    // TODO 表3 Goods 商品表 (id name...
    // TODO 表4 Car_Goods 多对多中间表 (car_id goods_id buyCount(数量)

    void addCar(@Param("carId") int carId, @Param("goodsId") int goodsId) throws Exception;

    ShoppingCar getCarDetails(ShoppingCar car);

    int createCar(int userId) throws Exception;

    void removeGoods(@Param("carId") int carId, @Param("goodsId") int goodsId) throws Exception;

}
