package com.web;

import com.web.task.Monad;
import com.web.task.Sepolia;
import com.web.util.FileUtil;
import com.web.util.MouseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.web.Constants.*;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Main {

    static List<Account> accountList = FileUtil.readAccountFile();
    static List<Task> taskList;

    public static void main(String[] args) throws Exception {
        MouseUtil.init();
        for (Account account : accountList) {
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
            }
        }
    }


    private static List<Task> initTask(Account account) {
        taskList = new ArrayList<>();
        taskList.addAll(Sepolia.getSepoliaTask(account));
        //taskList.addAll(Monad.getMonadTask(account));

        System.out.println("Main.initTask success size=" + taskList.size());
        return taskList;
    }


}
