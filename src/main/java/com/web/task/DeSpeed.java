package com.web.task;

import com.web.constant.Constants;
import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;


public class DeSpeed {

    public static List<Task> getDeSpeedTask(Account account) {
        List<Task> taskList = new ArrayList<>();
        if (!Constants.DP_ACCOUNTS.contains(account.name)) {
            return taskList;
        }
        Task task = new Task("DeSpeed-1", "DeSpeed签到", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://app.despeed.net/dashboard", 10 * 1000));
        actionList.add(Task.Action.buildMoveClickAction(415, 765));
        task.actionList = actionList;
        taskList.add(task);
        return taskList;

    }

}
