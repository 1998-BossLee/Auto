package com.web.task;

import com.web.*;
import java.util.*;

import static com.web.Constants.*;
import static com.web.Constants.MOVE_AND_CLICK;

/**
 * @author: liyangjin
 * @create: 2025-03-07 03:00
 * @Description:
 */
public class Sepolia {

    public static List<Task> getSepoliaTask(Account account) {
        List<Task> taskList = new ArrayList<>();

        Task task = new Task(101, "谷歌领水", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(new Task.Action(OPEN_URL, "https://cloud.google.com/application/web3/faucet/ethereum/sepolia",  0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
        actionList.add(new Task.Action(INPUT_TEXT, account.evm, 0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
        task.actionList = actionList;
        taskList.add(task);
        return taskList;
    }
}
