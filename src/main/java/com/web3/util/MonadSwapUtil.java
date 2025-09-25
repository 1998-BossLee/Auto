package com.web3.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.utils.Numeric;

public class MonadSwapUtil {

    static Map<String, String> privateKeyMap = new HashMap<>() {
        {
            put("0x4b83388e9e139d0d60e133f98fe3106785ebf588", "60fa35e447a1d3dd34f30efe40e7b1f868852665c1a37a05c90e17d202f6d524");
        }
    };

    // 从成功交易中提取的准确地址
    static final String BEAN_ROUTER = "0xCa810D095e90Daae6e867c19DF6D9A8C56db2c89";
    static final String UNISWAP_V2_ROUTER = "0x4c4eABd5Fb1D1A7234A48692551eAECFF8194CA7";
    static final String WMON_ADDRESS = "0x760AfE86e5de5fa0Ee542fc7B7B713e1c5425701";
    static final String YAKI_ADDRESS = "0xfe140e1dCe99Be9F4F15d657CD9b7BF622270C50";
    static final String CHOG_ADDRESS = "0xe0590015a873bf326bd645c3e1266d4db41c4e6b";
    static final String GMOND_ADDRESS = "0xaeef2f6b429cb59c9b2d7bb2141ada993e8571c3";
    static final String DAK_ADDRESS = "0x0f0bdebf0f83cd1ee3974779bcb7315f9808c714";

    public static void swapMONtoYAKI(String addressFrom, double monAmount) {
        try {
            String privateKey = privateKeyMap.get(addressFrom);
            if (privateKey == null) {
                System.out.println("私钥未找到");
                return;
            }

            Web3j web3 = Web3j.build(new HttpService("https://testnet-rpc.monad.xyz"));
            Credentials credentials = Credentials.create(privateKey);

            BigInteger nonce = web3.ethGetTransactionCount(addressFrom, DefaultBlockParameterName.PENDING).send().getTransactionCount();
            BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
            BigInteger adjustedGasPrice = gasPrice.multiply(BigInteger.valueOf(12)).divide(BigInteger.valueOf(10));

            BigInteger amountInWei = BigInteger.valueOf((long) (monAmount * 1e18));

            // 使用更保守的amountOutMin
            BigInteger amountOutMin = BigInteger.ZERO; // 先设为0测试

            BigInteger deadline = BigInteger.valueOf(System.currentTimeMillis() / 1000 + 1800);

            System.out.println("=== Bean路由器 MON → YAKI 交换 ===");
            System.out.println("路径: MON → WMON → 代币A → 代币B → YAKI");
            System.out.println("金额: " + monAmount + " MON");

            // 使用准确的4段路径
            Function function = new Function(
                    "swapExactETHForTokens",
                    Arrays.asList(
                            new Uint256(amountOutMin),
                            new DynamicArray<>(
                                    Address.class,
                                    Arrays.asList(
                                            new Address(WMON_ADDRESS),
                                            new Address(YAKI_ADDRESS),
                                            new Address(CHOG_ADDRESS),
                                            new Address(DAK_ADDRESS)
                                    )
                            ),
                            new Address(addressFrom),
                            new Uint256(deadline)
                    ),
                    Collections.emptyList()
            );

            String encodedFunction = FunctionEncoder.encode(function);

            RawTransaction rawTransaction = RawTransaction.createTransaction(
                    nonce,
                    adjustedGasPrice,
                    BigInteger.valueOf(600_000), // 增加gasLimit
                    BEAN_ROUTER,
                    amountInWei,
                    encodedFunction
            );

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            System.out.println("发送交易...");

            EthSendTransaction tx = web3.ethSendRawTransaction(hexValue).send();

            if (tx.hasError()) {
                System.out.println("❌ 交易失败: " + tx.getError().getMessage());
                // 如果是INVALID_PATH，说明路径可能已变化
                if (tx.getError().getMessage().contains("INVALID_PATH")) {
                    System.out.println("💡 路径可能已失效，需要更新中间代币地址");
                }
            } else {
                System.out.println("✅ 交易已发送!");
                System.out.println("Hash: " + tx.getTransactionHash());
            }

        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String address = "0x4b83388e9e139d0d60e133f98fe3106785ebf588";

        // 先用极小金额测试
        swapMONtoYAKI(address, 0.001);
    }
}