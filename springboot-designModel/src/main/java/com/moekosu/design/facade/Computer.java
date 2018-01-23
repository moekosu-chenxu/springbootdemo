package com.moekosu.design.facade;

/**
 * 设计模式：外观模式
 * @author chenxu
 * @date 2018/01
 */
public class Computer {

    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer()
    {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup()
    {
        System.out.println("Computer.startup");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("Computer.startup complete");
    }

    public void shutdown()
    {
        System.out.println("Computer.shutdown");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("Computer.shutdown finish");
    }

    public static void main(String[] args) {
        Computer c = new Computer();
        c.startup();
        c.shutdown();
    }

}
