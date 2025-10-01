package com.web3.api;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;


import java.security.SecureRandom;
import java.util.Locale;

public class WalletGenerator {

    /**
     * 生成 12 个助记词，并派生 BIP44 (m/44'/60'/0'/0/0) 的第一个以太坊（EVM）地址与私钥
     */
    public static WalletInfo generateMnemonicWallet() {
        try {
            // 1) 生成 128-bit 随机熵 -> 对应 12 个助记词
            byte[] initialEntropy = new byte[16]; // 16 bytes = 128 bits = 12 words
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(initialEntropy);

            // 2) 生成助记词（12 words）
            String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);

            // 3) 使用空密码派生种子（可以传非空密码加强保护）
            byte[] seed = MnemonicUtils.generateSeed(mnemonic, "");

            // 4) 从种子生成 BIP32 master keypair
            Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(seed);

            // 5) BIP44 路径：m/44'/60'/0'/0/0  （Ethereum）
            final int hardenedBit = Bip32ECKeyPair.HARDENED_BIT;
            int[] path = {
                    44 | hardenedBit,
                    60 | hardenedBit,
                    0  | hardenedBit,
                    0,
                    0
            };
            Bip32ECKeyPair bip44Keypair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path);

            // 6) 创建 Credentials（包含私钥、公钥、地址）
            Credentials credentials = Credentials.create(bip44Keypair);

            String address = credentials.getAddress(); // 0x...
            String privateKeyHex = credentials.getEcKeyPair().getPrivateKey().toString(16);
            String publicKeyHex = credentials.getEcKeyPair().getPublicKey().toString(16);

            // 7) 返回结果
            return new WalletInfo(mnemonic, address, normalizeHex(privateKeyHex), normalizeHex(publicKeyHex));
        } catch (Exception e) {
            throw new RuntimeException("生成钱包失败", e);
        }
    }

    // 保证 hex 字符串偶数长度并小写（可按需改）
    private static String normalizeHex(String hex) {
        if (hex == null) return null;
        hex = hex.toLowerCase(Locale.ROOT);
        if (hex.length() % 2 == 1) {
            hex = "0" + hex;
        }
        return "0x" + hex;
    }

    public static void main(String[] args) {
        WalletInfo w = generateMnemonicWallet();
        System.out.println("助记词 (12 words)：\n" + w.mnemonic);
        System.out.println("地址: " + w.address);
        System.out.println("私钥: " + w.privateKeyHex);
        System.out.println("公钥: " + w.publicKeyHex);
        System.out.println("\n**请立即离线备份助记词和私钥，切勿泄露**");
    }

    public static class WalletInfo {
        public final String mnemonic;
        public final String address;
        public final String privateKeyHex;
        public final String publicKeyHex;

        public WalletInfo(String mnemonic, String address, String privateKeyHex, String publicKeyHex) {
            this.mnemonic = mnemonic;
            this.address = address;
            this.privateKeyHex = privateKeyHex;
            this.publicKeyHex = publicKeyHex;
        }
    }
}