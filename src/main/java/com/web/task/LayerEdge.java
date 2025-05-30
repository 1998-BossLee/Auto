package com.web.task;

import com.web.constant.Constants;
import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;

import java.util.ArrayList;
import java.util.List;

public class LayerEdge {


    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        if (!Constants.DP_ACCOUNTS.contains(account.name)) {
            return taskList;
        }
        Task task;
        List<Task.Action> actionList;

        //打开网址-随便找个地方对焦-往下滚动-对焦钱包输入框-输入钱包地址-真人识别-点击领取
        task = new Task(TaskConstant.LayerEdge.SIGN, "", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://dashboard.layeredge.io/", 30));
        actionList.add(Task.Action.buildMoveClickAction(950, 660)); //最近会弹出来一个提示按钮
        actionList.add(Task.Action.buildMoveClickAction(1057, 222));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildMoveClickAction(1057, 680));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;
        return taskList;
    }

}
