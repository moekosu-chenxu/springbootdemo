package com.moekosu.testspringboot;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 排序法
 * @author chenxu
 * @date 2018/03
 */
public class SortTest {

    /**
     * 二分法：
     * 在数组中，输入一个数字，查找出这个数字在这个数组中的位置（下标）
     */
    @Test
    public void test1()
    {
        int[] arr = {0,5,12,15,26,44,52};
        Arrays.sort(arr);
        //
        //Scanner scanner = new Scanner(System.in);
        //int inp = scanner.nextInt();
        //
        System.out.println(m2(arr, 26));
    }
    private Integer m2(int[] arr, int num)
    {
        int begin = 0; // 开始位置
        int end = arr.length - 1; // 结束位置
        int mid1 = (begin + end) /2; // 中间位置
        Integer target = null;

        if(arr[mid1] == num){ // 如果中间就是目标值，直接返回
            target = mid1;
        }
        else{
            while (begin < end){
                int mid = (begin + end) /2; // 中间位置
                if(arr[mid] < num){  // 如果目标值比中间值大，则查找
                    begin = mid + 1;
                    if(begin == end){
                        if(arr[begin] == num){
                            target = begin;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
                else{
                    end = mid - 1;
                    if(begin == end){
                        if(arr[begin] == num){
                            target = begin;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }

        return target;
    }

}































