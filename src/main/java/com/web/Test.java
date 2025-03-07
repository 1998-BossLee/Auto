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
        int y = 10;
        int a = 1, b = 20, n = 20;
        for (int i = a; i <= n; i++) {
            int x = a + (b - a) / (n - 1) * (i - 1);
            System.out.println("i=" + i + " x=" + x + " y=" + y);
        }
    }

}
