package com.web.task;

import com.web.constant.Constants;
import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Sahara {

    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(TaskConstant.Sahara.FAUCET, "", 0);
        taskList.add(task);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.saharalabs.ai/", 15 ));
        actionList.add(Task.Action.buildMoveClickAction(660, 700));
        actionList.add(Task.Action.buildMoveClickAction(1220, 630));
        task.actionList = actionList;
        return taskList;
    }

}
