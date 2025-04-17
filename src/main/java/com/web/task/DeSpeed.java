package com.web.task;

import com.web.constant.Constants;
import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;


public class DeSpeed {

    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        if (!Constants.DP_ACCOUNTS.contains(account.name)) {
            return taskList;
        }
        Task task = new Task(TaskConstant.Depin.DESPEED, "", 0);
        taskList.add(task);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://app.despeed.net/dashboard", 10 ));
        actionList.add(Task.Action.buildMoveClickAction(415, 765));
        task.actionList = actionList;

        task = new Task(TaskConstant.Depin.BLOCK_MESH, "", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildMoveClickAction(1770, 65));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildMoveClickAction(1720, 415));
        task.actionList = actionList;
        return taskList;

    }

}
