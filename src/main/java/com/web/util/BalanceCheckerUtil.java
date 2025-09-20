package com.web.util;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.math.BigInteger;
import java.util.*;

public class BalanceCheckerUtil {

    // 链配置
    private static final Map<String, ChainConfig> CHAINS = new HashMap<>();

    static {
        CHAINS.put("eth", new ChainConfig(
                "https://eth.llamarpc.com",
                "ETH",
                Map.of("USDT", "0xdAC17F958D2ee523a2206206994597C13D831ec7")
        ));
        CHAINS.put("bsc", new ChainConfig(
                "https://bsc-dataseed.binance.org/",
                "BNB",
                Map.of("USDT", "0x55d398326f99059fF775485246999027B3197955")
        ));
        CHAINS.put("op", new ChainConfig(
                "https://mainnet.optimism.io",
                "ETH",
                Map.of("USDT", "0x94b008aa00579c1307b0ef2c499ad98a8ce58e58")
        ));
        CHAINS.put("monad", new ChainConfig(
                "https://testnet-rpc.monad.xyz",
                "MON",
                Map.of(
                        "USDT", "0x88b8e2161dedc77ef4ab7585569d2415a1c1055d",
                        "YAKI", "0xfe140e1dce99be9f4f15d657cd9b7bf622270c50"
                )
        ));
    }

    public static void main(String[] args) throws Exception {
        String wallet = "0x6393b782e36a6333787850a910db6b7da70aea86"; // 你的钱包地址

        double balance =  getChainNativeBalance("monad", wallet);
        System.out.println(balance);
    }

    // 查询 ERC20 代币余额
    private static double getTokenBalance(Web3j web3, String wallet, String tokenAddr) throws Exception {
        ReadonlyTransactionManager txManager = new ReadonlyTransactionManager(web3, wallet);

        // decimals()
        Function decimalsFunc = new Function("decimals", List.of(), List.of(new TypeReference<org.web3j.abi.datatypes.generated.Uint8>() {
        }));
        BigInteger decimals = callFunction(web3, tokenAddr, decimalsFunc).get(0).getValue() instanceof BigInteger
                ? (BigInteger) callFunction(web3, tokenAddr, decimalsFunc).get(0).getValue()
                : BigInteger.valueOf(18);

        // balanceOf(address)
        Function balanceOfFunc = new Function("balanceOf",
                List.of(new Address(wallet)),
                List.of(new TypeReference<org.web3j.abi.datatypes.generated.Uint256>() {
                }));
        BigInteger balance = (BigInteger) callFunction(web3, tokenAddr, balanceOfFunc).get(0).getValue();

        return balance.doubleValue() / Math.pow(10, decimals.intValue());
    }

    private static List<Type> callFunction(Web3j web3, String contract, Function function) throws Exception {
        String encoded = FunctionEncoder.encode(function);
        EthCall response = web3.ethCall(
                org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
                        null, contract, encoded),
                DefaultBlockParameterName.LATEST).send();

        return FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
    }

    public static double getChainNativeBalance(String chain, String wallet) throws Exception {
        ChainConfig cfg = CHAINS.get(chain);
        if (cfg == null) {
            throw new Exception("Unsupported chain: " + chain);
        }
        Web3j web3 = Web3j.build(new HttpService(cfg.rpc));
        EthGetBalance ethGetBalance = web3.ethGetBalance(wallet, DefaultBlockParameterName.LATEST).send();
        BigInteger balanceWei = ethGetBalance.getBalance();
        return balanceWei.doubleValue() / Math.pow(10, 18);
    }


    // 链配置类
    static class ChainConfig {
        String rpc;
        String nativeSymbol;
        Map<String, String> tokens;

        ChainConfig(String rpc, String nativeSymbol, Map<String, String> tokens) {
            this.rpc = rpc;
            this.nativeSymbol = nativeSymbol;
            this.tokens = tokens;
        }
    }
}