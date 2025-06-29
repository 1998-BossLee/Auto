package com.web.task;

import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-04-14 10:25
 * @Description:
 * 区块链浏览器：https://shannon-explorer.somnia.network/
 *
 */
public class Somnia {

    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
//        Task task = new Task(TaskConstant.Sahara.FAUCET, "", 0);
//        taskList.add(task);
//        List<Task.Action> actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://testnet.somnia.network/", 15 ));
//        actionList.add(Task.Action.buildMoveClickAction(660, 700));
//        actionList.add(Task.Action.buildMoveClickAction(1220, 630));
//        task.actionList = actionList;


//        task = new Task(TaskConstant.Monad.MINTAIR, "deploy", 0);
//        taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://contracts.mintair.xyz/", 20));
//        actionList.add(Task.Action.buildMoveClickAction(875, 500));
//        actionList.add(Task.Action.buildMoveClickAction(950, 875));//switch chian
//        actionList.add(Task.Action.buildMoveClickAction(950, 875));//deploy
//        actionList.add(Task.Action.buildSignAction());
//        actionList.add(Task.Action.buildSleepAction(10));
//        actionList.add(Task.Action.buildCancelSignAction());//如果是二连击，会弹出来2个，关掉一个。
//        actionList.add(Task.Action.buildSignAction());//稳妥起见最后再确定一下
//        task.actionList = actionList;
        return taskList;
    }

    public static List<Task> getAllTask(Account account) {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(getDailyTasks(account));
        return taskList;
    }


}
