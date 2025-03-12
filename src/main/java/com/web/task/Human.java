package com.web.task;

import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-03-07 03:01
 * @Description:
 */
public class Human {
    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();

        Task task = new Task("human-1", "human领水", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.testnet.humanity.org/", 20));
        actionList.add(Task.Action.buildInputTextAction(940, 716, account.evm));
        actionList.add(Task.Action.buildMoveClickAction(1213, 705));
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildMoveClickAction(1213, 705));
        actionList.add(Task.Action.buildSleepAction(5));
        task.actionList = actionList;
        taskList.add(task);
        return taskList;
    }
}
