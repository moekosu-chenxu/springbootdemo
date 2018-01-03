#!/bin/bash

TEST_HOME="zz.com"
echo "this is ${TEST_HOME}zz"
#只读
readonly TEST_HOME
#删除变量 不能删除只读变量cannot unset readonly variable
unset TEST_HOME
#单引号 (不能中间插入变量;不能使用反斜杠来使用另一个单引号
echo 'single variable'
#双引号 (能够中间插入变量;能够使用反斜杠
echo "double variable \"${TEST_HOME}\""

#${#name}输出字符串的长度
echo “variable length is: ${#TEST_HOME}.”

STRING="helloworld"
#截取字符串 从第0个开始，总共4个 默认从0开始
echo ${STRING:1:4}
#查找字符串中l的位置 相当于indexof
echo `expr index "$STRING" l`

#TODO 拓展，多种截取字符串方式
SPLIT="http://www.mogushu.xin/zz.html"
#1 变量名 #是运算符 *//表示将 [第一个//] 左边的所有字符串删除，包含//，结果是www.mogushu.xin/zz.html
echo ${SPLIT#*//}
#2 ##是运算符 */表示将 从左边开始到最后一个/ 为止的字符串删除，包含最后一个/，结果是zz.html
echo ${SPLIT##*/}
#3 %是运算符 /*表示将 从右边开始到第一个/ 为止的字符串删除，包含/，结果是http://www.mogushu.xin
echo ${SPLIT%/*}
#4 %%运算符 /*表示将 从右边开始到最左边的最后一个/ 为止的字符串删除，包含/，结果是http:
echo ${SPLIT%%/*}
#5 截取第1个开始的总共5个字符串
echo ${SPLIT:0:5}
#6 从左边第4个字符开始直到结束
echo ${SPLIT:3}
#7 0-7表示从右算起的第7个开始，截取3个字符串
echo ${SPLIT:0-7:3}
#8 0-7表示从右算起的第7个开始，一直截取到最左边结束
echo ${SPLIT:0-7}

#定义数组
ARR=("a" "b")
echo ${ARR[1]}
echo ${ARR[@]} #输出数组所有参数
echo ${#ARR[@]} #输出数组长度

