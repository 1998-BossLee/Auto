package com.web3.task;

import com.web3.constant.TaskConstant;
import com.web3.model.Account;
import com.web3.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Twitter {

    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();

        Task task = new Task(TaskConstant.Twitter.BROWSE, "", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://x.com/home", 20));
        task.actionList = actionList;
        return taskList;
    }

}
