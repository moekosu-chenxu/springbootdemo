package com.moekosu.service;


public interface ShoppingCarService {

    void addCar(int userId, int goodsId);

    void removeGoods(int carId, int goodsId);

}
