package com.moekosu.design.prototype;

import java.io.*;

/**
 * 设计模式：原型模式
 * 将一个对象作为原型进行复制克隆
 * PS 本类包含Java的浅复制与深复制实例
 * @author chenxu
 * @date 2018/01
 */
public class Prototype implements Cloneable, Serializable {

    /**
     * 普通复制(浅复制)：
     * 复制出来的对象的变量都是相同的值，但对其他对象的引用都还是原来的引用
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException
    {
        // 重点是这里的super.clone() 调用的是Object的clone方法
        Prototype proto = (Prototype) super.clone();
        return proto;
    }

    /**
     * 深复制：变量是相同的值，引用的对象指向的都是被复制过的新对象
     * 实现深复制须采用流的方式，获取一个新的对象
     * @return
     * @throws IOException 操作io流
     * @throws ClassNotFoundException ois.readObject抛错
     */
    public Object deepClone() throws IOException, ClassNotFoundException
    {
        // 写入当前对象的二进制流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(this);

        // 读出二进制流产生的新对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

}
