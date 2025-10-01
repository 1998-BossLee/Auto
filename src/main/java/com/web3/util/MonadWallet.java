package com.web3.util;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

/**
 * @author: liyangjin
 * @create: 2025-09-23 16:26
 * @Description:
 */
public class MonadWallet {

    public static void main(String[] args) throws Exception {
        // 生成新的密钥对
        ECKeyPair keyPair = Keys.createEcKeyPair();

        // 从密钥对创建钱包凭证
        Credentials credentials = Credentials.create(keyPair);

        // 打印钱包信息
        System.out.println("钱包地址: " + credentials.getAddress());
        System.out.println("私钥: " + credentials.getEcKeyPair().getPrivateKey().toString(16));
        System.out.println("公钥: " + credentials.getEcKeyPair().getPublicKey().toString(16));
    }
}
