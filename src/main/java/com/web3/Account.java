package com.web3;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Account {

    public String id;
    public String evm;
    public int x;
    public int y;
    //大号还是小号，1-小号 >1是大号
    public int quality;

    public Account(String id, String evm, int x, int y, int quality) {
        this.id = id;
        this.evm = evm;
        this.x = x;
        this.y = y;
        this.quality = quality;
    }

}

