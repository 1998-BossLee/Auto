package com.web;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2025-03-07 16:31
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        String s = "4648,2842,4759,3063,3118,4760,2867,4761,4795,2724,2922,2843,3121,4759,2998,2999,3122,4804,4805,2963,4809,4762,4817,3060,3156";
        String[] parts = s.split(",");
        Arrays.sort(parts);
        for (String part : parts) {
            System.out.println(part);
        }
        System.out.println(parts.length);
    }

    /**
     * 1 2 3 4 5
     */
    public static void calculate() {
        //等差计算浏览器坐标
        int d = 40;//(1160-400)/19
        for (int i = 0; i < 20; i++) {
            int x = 400 + i * d;
            System.out.println("i=" + i + " x=" + x);
        }
    }

}
