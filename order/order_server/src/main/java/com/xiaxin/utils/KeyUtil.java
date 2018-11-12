package com.xiaxin.utils;

import java.util.Random;

/**
 * Created by xiaxin on 2018/2/20.
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     *
     * @return
     */
    public static synchronized String getUniqueKey() {

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
