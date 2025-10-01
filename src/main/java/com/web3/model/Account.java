package com.web3.model;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Account {

    public String name;
    public String evm;
    public int x;
    public int y;
    //大号还是小号，1-小号 >1是大号
    public int quality;

    public Account(String name, String evm, int x, int y, int quality) {
        this.name = name;
        this.evm = evm;
        this.x = x;
        this.y = y;
        this.quality = quality;
    }

}

