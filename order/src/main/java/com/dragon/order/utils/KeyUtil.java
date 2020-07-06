package com.dragon.order.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生产唯一的主键
     * 格式： 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
