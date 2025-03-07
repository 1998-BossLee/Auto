package com.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.web.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static List<Account> readAccountFile() {
        List<Account> accountList = null;
        String filePath = "data/account.json"; // 文件路径

        try {
            // 读取整个文件内容为字符串
            String jsonString = Files.readString(Paths.get(filePath));
            // 将字符串解析为 List<Account>
            accountList = JSON.parseObject(jsonString, new TypeReference<List<Account>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("read account.json success account.size = "+accountList.size());
        for (Account account : accountList) {
            System.out.println(account.name);
        }
        return accountList;
    }

    public static JSONObject readConfig() {
        String filePath = "data/config.json";
        try {
            // 读取整个文件内容为字符串
            String jsonString = Files.readString(Paths.get(filePath));
            // 将字符串解析为 List<Account>
            JSONObject json = JSONObject.parseObject(jsonString);
            System.out.println("read config.json success");
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(readAccountFile());
        System.out.println(readConfig());
    }

}
