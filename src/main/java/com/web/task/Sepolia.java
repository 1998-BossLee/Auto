package com.web.task;

import com.web.model.Account;
import com.web.model.Task;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-03-07 03:00
 * @Description:
 */
public class Sepolia {

    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();

        Task task = new Task("Sepolia-1", "谷歌领水", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://cloud.google.com/application/web3/faucet/ethereum/sepolia", 20 ));
        actionList.add(Task.Action.buildInputTextAction(1251, 454, account.evm));
        actionList.add(Task.Action.buildMoveClickAction(883, 536));
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        return taskList;
    }
}
