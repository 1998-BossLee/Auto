package com.web3.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2025-09-24 17:43
 * @Description:
 */
public class MonadUniswapV2Util {

    static Map<String, String> privateKeyMap = new HashMap<>() {
        {
            put("0x4b83388e9e139d0d60e133f98fe3106785ebf588", "60fa35e447a1d3dd34f30efe40e7b1f868852665c1a37a05c90e17d202f6d524");
        }
    };

    public static void main(String[] args) {
        String address = "0x4b83388e9e139d0d60e133f98fe3106785ebf588";
        String privateKey = privateKeyMap.get(address);

    }

}
