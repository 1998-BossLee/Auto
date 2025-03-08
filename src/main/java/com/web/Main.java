package com.web;

import com.web.model.Account;
import com.web.model.Task;
import com.web.task.DeSpeed;
import com.web.task.Monad;
import com.web.task.Sepolia;
import com.web.util.FileUtil;
import com.web.util.MouseUtil;

import java.util.ArrayList;
import java.util.Collections;
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

    //TODO 提前打开一个没有用的页面
    public static void main(String[] args) throws Exception {
        for (Account account : accountList) {
            if (!"ads-5".equals(account.name)) {
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
                Task.Action action = new Task.Action(SLEEP, "", 0,0, 5000);
                MouseUtil.executeAction(action);
            }
        }
    }


    private static List<Task> initTask(Account account) {
        taskList = new ArrayList<>();
        //taskList.addAll(Sepolia.getSepoliaTask(account));
        taskList.addAll(Monad.getMonadTask(account));
        //taskList.addAll(DeSpeed.getMonadTask(account));
        System.out.println("Main.initTask success size=" + taskList.size());
        return taskList;
    }


}
