package com.web.task;

import com.web.constant.TaskConstant;
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
        List<Task.Action> actionList;

        Task task = new Task(TaskConstant.Human.FAUCET, "", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.testnet.humanity.org/", 20));
        actionList.add(Task.Action.buildInputTextAction(940, 716, account.evm));
        actionList.add(Task.Action.buildMoveClickAction(1213, 705));
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildMoveClickAction(1213, 705));
        actionList.add(Task.Action.buildSleepAction(5));
        task.actionList = actionList;
        taskList.add(task);

        task = new Task(TaskConstant.Human.SIGN, "", 0);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://testnet.humanity.org/dashboard", 20));
        actionList.add(Task.Action.buildMoveClickAction(1000, 700));//sign in as google
        actionList.add(Task.Action.buildSleepAction(30)); //wait for sign in
        actionList.add(Task.Action.buildMoveClickAction(950, 785));//close
        actionList.add(Task.Action.buildMoveClickAction(700, 1015));//claim daily reward
        actionList.add(Task.Action.buildSleepAction(30));
        task.actionList = actionList;
        return taskList;
    }
}
