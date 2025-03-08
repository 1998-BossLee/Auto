package com.web.task;

import com.web.constant.Constants;
import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;

import static com.web.constant.Constants.*;
import static com.web.constant.Constants.MOVE_AND_CLICK;

public class DeSpeed {

    public static List<Task> getMonadTask(Account account) {
        List<Task> taskList = new ArrayList<>();
        if (!Constants.DP_ACCOUNTS.contains(account.name)) {
            return taskList;
        }
        Task task = new Task("DeSpeed-1", "DeSpeed签到", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(new Task.Action(OPEN_URL, "https://app.despeed.net/dashboard",  0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 415, 765, 0));
        task.actionList = actionList;
        taskList.add(task);
        return taskList;

    }

}
