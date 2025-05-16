package com.web;

/**
 * @author: liyangjin
 * @create: 2025-03-07 16:31
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        String line = "OAID_84467FEA0A28B4A2C355DC599AFDE419   2       f82d8660-1d68-4401-902a-84dc4210d59e    HUAWEI  1747230317";
        String[] parts = line.trim().split("\\s+");
        System.out.println("parts.length=" + parts.length);
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
