package com.web3.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.web3.model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtil {

    public static List<Account> readAccountFile() {
        List<Account> accountList = null;
        String filePath = "data/account.json"; // 文件路径

        try {
            // 读取整个文件内容为字符串
            String jsonString = Files.readString(Paths.get(filePath));
            // 将字符串解析为 List<Account>
            accountList = JSON.parseObject(jsonString, new TypeReference<List<Account>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("read account.json success account.size = " + accountList.size());
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

    public static Set<String> readFinishedTask() {
        Set<String> finishedTasks = new HashSet<>();
        String filePath = "data/finishedTask.txt";
        try {
            // 读取整个文件内容为字符串
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            finishedTasks.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finishedTasks;
    }

    public static void appendFinishedTask(String taskUid) {
        String filePath = "data/finishedTask.txt";
        try {
            Files.writeString(Paths.get(filePath), "\n" + taskUid, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Set<String> finishedTasks = readFinishedTask();
        System.out.println(finishedTasks);
        String taskUid = "task-124";
        appendFinishedTask(taskUid);
        finishedTasks = readFinishedTask();
        System.out.println(finishedTasks);
        System.out.println(finishedTasks.contains("hub-2_monad-1_monad官网领水"));
    }

}
