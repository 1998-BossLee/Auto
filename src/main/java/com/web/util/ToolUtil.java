package com.web.util;

import java.util.Random;

public class ToolUtil {

    static Random random = new Random();

    //x分之一的概率
    public static boolean randomBoolean(int x) {
        return random.nextInt(x) == 0;
    }

}
