package com.web;

import com.web.task.Monad;
import com.web.task.Sepolia;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Main {

    static List<Account> accountList = initAccount();
    static List<Task> taskList;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    private static List<Account> initAccount() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("1", "0x6393B782e36a6333787850A910db6b7Da70aeA86", 0, 0, 1));
        return accountList;
    }

    private static List<Task> initTask(Account account) {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(Sepolia.getSepoliaTask(account));
        taskList.addAll(Monad.getMonadTask(account));








        return taskList;
    }


}
