package com.example.demo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import java.io.File;

/*
 * @Author: tianyong
 * @Date: 2020/8/14 10:39
 * @Description: java调用dll文件暴露restful API接口
 */
public class Dll2Api {

    /*
     * @Author: tianyong
     * @Date: 2020/8/14 10:52
     * @Description: 调用自定义dll函数库
     */
    public interface CustomLibrary extends Library {
        // 书写格式：Native.loadLibrary(dll_name,interface.class)
        // 创建动态库实例对象
        CustomLibrary INSTANCE = (CustomLibrary) Native.loadLibrary("sum", CustomLibrary.class);
        // 书写自定义方法
        int sum(int x, int y);
    }

    // 加载库的绝对路径
    static {
        File file = new File("src\\main\\resources\\static\\sum.dll");
        System.load(file.getAbsolutePath());
    }

    // 主程序入口
    public static void main(String[] args) {
        // 调用dll中的函数
        System.out.println(CustomLibrary.INSTANCE.sum(1,2));;
    }

}
