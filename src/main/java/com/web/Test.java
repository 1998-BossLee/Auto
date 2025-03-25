package com.web;

/**
 * @author: liyangjin
 * @create: 2025-03-07 16:31
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        calculate();
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
