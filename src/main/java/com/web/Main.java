package com.web;

import com.web.model.Account;
import com.web.model.Task;
import com.web.task.*;
import com.web.util.FileUtil;
import com.web.util.MouseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.web.constant.Constants.*;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Main {

    static List<Account> accountList = FileUtil.readAccountFile();
    static List<Task> taskList;

    private static List<Task> initTask(Account account) {
        taskList = new ArrayList<>();
        taskList.addAll(Sepolia.getDailyTasks(account));
        taskList.addAll(Monad.getDailyTasks(account));
        taskList.addAll(DeSpeed.getDailyTasks(account));
        taskList.addAll(LayerEdge.getDailyTasks(account));
        taskList.addAll(Human.getDailyTasks(account));
        System.out.println("Main.initTask success size=" + taskList.size());
        return taskList;
    }

    static HashSet<String> testAccounts = new HashSet<>(){
        {
            add("ads-1");
            add("ads-2");
        }
    };

    //TODO 提前打开一个没有用的页面
    public static void main(String[] args) throws Exception {
        Collections.shuffle(accountList);
        for (Account account : accountList) {
            if (!testAccounts.contains(account.name)) {
                //单测任务
                continue;
            }
            if (account.evm == null || account.evm.isEmpty()) {
                continue;
            }
            System.out.println("current account name:" + account.name);
            Task.Action startAccountAction = new Task.Action(MOVE_AND_CLICK, "", account.x, account.y, 0) ;
            MouseUtil.executeAction(startAccountAction);
            taskList = initTask(account);
            Collections.shuffle(taskList);

            for (Task task : taskList) {
                System.out.println("current task:" + task.name);
                for (Task.Action action : task.actionList) {
                    MouseUtil.executeAction(action);
                }
                //任务之间睡眠5秒
                Task.Action action = new Task.Action(SLEEP, "", 0,0, 5);
                MouseUtil.executeAction(action);
            }
        }
    }





}
