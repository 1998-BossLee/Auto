package com.web.task;

import com.web.constant.Constants;
import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-03-07 16:26
 * @Description: 邀请链接
 * https://magicnewton.com/portal?referral=y2plzju0abl4khub
 * https://magicnewton.com/portal?referral=g1vi2js2ic1cxme8
 * https://magicnewton.com/portal?referral=14eewt7ytjmh49hz
 * 7天最多推荐5个人，共250积分
 */
public class Newton {

    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        if (!Constants.DP_ACCOUNTS.contains(account.name)) {
            return taskList;
        }
        Task task = new Task(TaskConstant.Newton.SIGN, "roll", 0);
        taskList.add(task);
        List<Task.Action> actionList = new java.util.ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.magicnewton.com/portal/rewards", 15));
        actionList.add(Task.Action.buildMoveClickAction(1050, 700));
        actionList.add(Task.Action.buildMoveClickAction(950, 730));
        actionList.add(Task.Action.buildMoveClickAction(950, 620));
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;
        taskList.add(task);
        return taskList;
    }
}
