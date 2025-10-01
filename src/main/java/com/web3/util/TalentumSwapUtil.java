package com.web3.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TalentumSwapUtil {

    // Talentum Router 地址
    private static final String ROUTER_ADDRESS = "0x0000000000001fF3684f28c67538d4D072C22734";

    // 连接 Monad 测试网 RPC
    private static final Web3j web3 = Web3j.build(new HttpService("https://testnet-rpc.monad.xyz"));

    // 钱包私钥
    private static final String PRIVATE_KEY = "60fa35e447a1d3dd34f30efe40e7b1f868852665c1a37a05c90e17d202f6d524";

    // 代币地址
    private static final String TOKEN_ADDRESS = "0xfe140e1dCe99Be9F4F15d657CD9b7BF622270C50"; // YAKI

    public static void main(String[] args) {
        try {
            Credentials credentials = Credentials.create(PRIVATE_KEY);
            String fromAddress = credentials.getAddress();

            // 构造 swap 参数
            BigInteger amountOutMin = BigInteger.ZERO; // 可根据滑点设置
            BigInteger deadline = BigInteger.valueOf(System.currentTimeMillis() / 1000 + 300); // 5分钟有效
            BigInteger value = BigInteger.valueOf((long) (0.1 * 1e18)); // 0.1 MON

            // 路径 MON -> Token
            List<Address> path = new ArrayList<>();
            path.add(new Address(fromAddress)); // MON 地址（通常是 WMON，这里示例简化）
            path.add(new Address(TOKEN_ADDRESS));

            // 通过 ABI 文件读取方法
            ObjectMapper mapper = new ObjectMapper();
            JsonNode abiArray = mapper.readTree(new File("TalentumRouterABI.json")); // ABI 文件路径

            Function swapFunction = null;
            for (JsonNode node : abiArray) {
                if (node.has("name") && node.get("name").asText().equals("swapExactETHForTokens")) {
                    // 构造 Function
                    swapFunction = new Function(
                            node.get("name").asText(),
                            List.of(
                                    new Uint256(amountOutMin),
                                    new DynamicArray<>(Address.class, path),
                                    new Address(fromAddress),
                                    new Uint256(deadline)
                            ),
                            List.of()
                    );
                    break;
                }
            }

            if (swapFunction == null) {
                System.out.println("swapExactETHForTokens 方法未在 ABI 中找到");
                return;
            }

            String encodedFunction = FunctionEncoder.encode(swapFunction);

            // 构造交易
            EthSendTransaction tx = web3.ethSendTransaction(
                    Transaction.createFunctionCallTransaction(
                            fromAddress,
                            null, // nonce
                            BigInteger.valueOf(20_000_000_000L), // gasPrice
                            BigInteger.valueOf(300_000),        // gasLimit
                            ROUTER_ADDRESS,
                            value,
                            encodedFunction
                    )
            ).send();

            System.out.println("交易 hash: " + tx.getTransactionHash());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
