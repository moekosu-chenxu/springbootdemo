package com.moekosu.testspringboot;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

/**
 * 简单实用算法合集
 * ps: treeSet自动排序+不重复
 * @author chenxu
 * @date 2018/03
 */
public class SuanfaTest {

    /**
     * 1.微信拆分红包
     * 板子拆分法
     */
    @Test
    public void test1()
    {
        Scanner scanner = new Scanner(System.in);
        // 总钱数(元)
        double money1 = scanner.nextDouble();
        // 红包个数
        int bag = scanner.nextInt();

        int money = (int) money1 * 100; // 保证最低到分0.01
        // 板子
        int[] boards = new int[bag+1]; // 板子个数=红包个数+1
        boards[0] = 0;     // 板子1：第1个板子一定在最前面
        boards[1] = money; // 板子2：第2个板子一定在最后面
        for (int i=0; i< bag-1; i++){
            // TODO
        }

    }

    /**
     * 2.洗牌(打乱元素顺序)
     * 随机换位：每张牌跟第一张牌换，换的次数够多牌就够乱
     */
    @Test
    public void test2()
    {
        int[] poker = new int[54];

        // 初始化
        for (int i=0; i< poker.length; i++){
            poker[i] = i+1;
        }
        // 洗牌
        xipai(poker, 100);
        // 显示牌
        int lun = 0;
        for (int j=0; j< poker.length; j++){
            System.out.println(poker[j] + "\t");
            lun++;
            if(lun % 10 == 0){
                System.out.println("\n");
            }
        }
    }
    private void xipai(int[] poker, int count)
    {
        for (int i=0; i< count; i++){
            int ram = new Random().nextInt(54); // 0-53 随机位置
            int temp = poker[0];
            poker[0] = poker[ram];
            poker[ram] = temp;
        }
    }

    /**
     * 3.π计算
     * 蒙特卡洛算法：假设r半径=1，πr²=π，则包裹住圆形的正方形的面积为2*2=4
     * 添加随机数，则落在圆形内的次数b/总次数a=π/4
     * 则可得π=(b*4)/a
     * ps: 随机数根据xy坐标，x²+y²<=1即落在圆内
     */
    @Test
    public void test4()
    {
        int a = 100000;  // 抛硬币总次数
        int b = 0;       // 落在圆内的次数

        for (int i=0; i< a; i++){
            double x = Math.random() * 2.0 -1; // Math.random()返回0.0-1.0，包含0不包含1
            double y = Math.random() * 2.0 -1; // Math.random() * 2.0 -1 从-1到1之间
            if( (x*x) + (y*y) <= 1 ){
                b++;
            }
        }

        double p = (4.0*b)/a;
        System.out.println("π: "+ p);
    }

}
