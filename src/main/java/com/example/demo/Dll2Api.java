package com.example.demo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @Author: tianyong
 * @Date: 2020/8/14 10:39
 * @Description: java调用dll文件暴露restful API接口
 */
@Controller
public class Dll2Api {


    // window加载库的路径
    static {
        System.loadLibrary("sum");
    }


    /*
     * @Author: tianyong
     * @Date: 2020/8/14 10:52
     * @Description: 调用自定义dll函数库
     */
    public interface CustomLibrary extends Library {
        // 书写格式：Native.loadLibrary(dll_name,interface.class)
        // 创建动态库实例对象
        CustomLibrary INSTANCE = (CustomLibrary) Native.loadLibrary("sum", CustomLibrary.class);
        // 书写自定义方法 (自定义方法：似如下写法垒加即可)
        int sum(int x, int y);
    }


    /*
     * @Author: tianyong
     * @Date: 2020/8/14 11:05
     * @Description:暴露求和rest接口
     */
    @ResponseBody
    @RequestMapping("/service/sum")
    public Integer getSum(Integer var1,Integer var2){
        return CustomLibrary.INSTANCE.sum(var1,var2);
    }


}
