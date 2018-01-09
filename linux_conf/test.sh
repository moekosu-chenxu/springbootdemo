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
echo ${ARR[1]} #输出第二个参数(从0开始)
echo ${ARR[@]} #输出数组所有参数
echo ${#ARR[@]} #输出数组长度

# $数字 能输出传入的参数 0是文件名 从1开始是传入的参数，如./test.sh zz，则$1会输出zz，以此类推，$2 $3
echo $0
echo $1
echo $# #输出传入字符串的个数
echo $* #输出传入的所有字符串，用""括起来，相当于输出了一个参数
#循环测试，只会输出一个zz xx cc
for i in "$*"; do echo $i 
done
echo $@ #也是输出所有字符串，但是每个都单独用""括起来，相当于输出了多个参数
#循环测试，会输出三个参数，分别是zz xx cc
for j in "$@"; do echo $j 
done
echo $$ #输出脚本运行的当前进程ID

#运算符操作，注意使用运算要使用`反引号，而不是'单引号，特别注意运算符中间要带空格，必须写 2 + 2
#+加法 -减法 *乘法 /除法 %取余 =赋值 ==判断相等 !=判断不等
sum=`expr 2 + 2`
echo "sum is $sum"
#关系运算符，只支持数字
#-eq判断是否相等 -ne判断是否不相等 -gt是否大于 -lt是否小于 -ge是否大于等于 -le是否小于等于
num1=10
num2=20
if [ $num1 -gt $num2 ]
then
  echo 'n1 bigger than n2'
else
  echo 'n1 smaller than n2'
fi
#布尔运算符 
#!非 [!false]返回true
#-o或运算 [$num1 -lt 20 -o $num2 -gt 15] n1小于20 || n2大于15
#也可以使用||
#-a与运算 [$num1 -lt 20 -a $num2 -gt 15] n1小于20 && n2大于15
#也可以使用&&
if [ $num1 -lt 20 ]
then
  echo 1
else
  echo 2
fi
#字符串运算符
#=是否相等 !=是否不等 -z长度是否为0[-z str1] -n长度是否不为0[-n str1] 直接写str判断字符串是否为空不为空返回true
str1='asd'
if [ $str1 ]
then
  echo 'has value'
else
  echo 'no value'
fi
#文件测试运算符
#-b file是否是块设备文件 -c file是否是字符设备文件 -d file是否是目录 -f file是否是普通文件(不是目录也不是设备文件)
#-g file是否设置了SGID位 -k file是否设置了黏着位 -p file是否是有名管道 -u file是否设置了SUID位
#-r file是否可读 -w file是否可写 -x file是否可执行 -s file是否为空(文件大小是否大于0) -e file文件是否存在(包含目录)
if [ -e '/opt/aspire/product/dccp_test/test/test.sh' ]
then 
  echo 'file exist'
else
  echo 'file not exist'
fi

#echo -e开启转义 \c开启换行
echo -e "Hello \n World!"
#显示日期，注意是反引号
echo `date`
#将结果写入文件
echo 'this is a test inputstream' > myfile

#printf与echo类似，但是也有强大之处
#%c %s %d %f 格式替代符
#%-10s 表示宽度为10个字符，-表示左对齐，不够10个字符用空格替代
#%-4.2f 指格式化为小数，.2表示保留2位小数
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.1234 
printf "%-10s %-8s %-4.2f\n" 杨过 男 48.6543 
printf "%-10s %-8s %-4.2f\n" 郭芙 女 47.9876
#%s表示string字符串 %d表示decimal数字(必须是十进制数字) %f表示float小数 %c表示char字符
printf "%s %s\n" "123" "456" "asd" "zxc"

#test 跟[] 一样 if test a gt b = if [ a gt b ]

#if运算符
if [ 1=1 ]
then
 echo '1=1'
#else 这里可以不写
fi
#if else if else 用法
if [ 1=1 ]
then
  echo 1
elif [ 1=1 ]
then 
  echo 2
else
  echo 3
fi

#for循环
for loop in 1 2 3
do
  echo 'value is: $loop'
x
done

#while语句
whileInt=1
while(( $whileInt<=5 ))
do
    echo $whileInt
    let "whileInt++"
done
#同时while能读取键盘输入，这里用户输入的内容定为FILM
echo -n '输入你最喜欢的网站名: '
while read FILM
do
  echo "是的！$FILM 是一个好网站"
  break
done

#无限循环
#while :
#do ... done
#或者
#while true
#do ... done
#或者
#for (( ; ; ))

#case表达式 使用in 各种情况值后必须加) 内容语句执行到;;结束 使用esac结尾 PS可以使用break continue
echo '输入 1 到 4 之间的数字:'
echo '你输入的数字为:'
read aNum
case $aNum in
    1)  echo '你选择了 1'
    ;;
    2)  echo '你选择了 2'
    ;;
    3)  echo '你选择了 3'
    ;;
    4)  echo '你选择了 4'
    ;;
    *)  echo '你没有输入 1 到 4 之间的数字'
    ;;
esac

#定义function 可以写function name(){} 也可以直接写funName(){} 
funCode(){
  echo 'funCode.call that my fun.'
}
echo 'call function start--'
funCode
echo 'call function end--'
#带有return的方法 调用的时候使用$?
funReturn(){
  return 'fun return 1'
}
funReturn
echo "funReturn return $?" #$?只能输出数字，字符串不知道用什么 TODO
#方法传递参数
funParam(){
  echo "方法传入的第1个参数: $1"
  echo "总共有 $# 个参数"
  echo "所有参数: $@"
}
funParam 'p1' 'p2' 'p3' 'p4'

#输入重定向操作
#a > file 将a输入到file 注意file内的内容会清空替换为a
#a >> file 将a添加到file的最末尾 不会清空原来的内容
echo 'zz' >> myfile
#cat 输出文件内容
cat myfile
#输出重定向操作 TODO 报错
echo file1 < myfile
echo fileLen -l < myfile #文件行数


