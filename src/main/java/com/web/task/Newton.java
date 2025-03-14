package com.web.task;

import com.web.model.Account;
import com.web.model.Task;

import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-03-07 16:26
 * @Description: 邀请链接 https://magicnewton.com/portal?referral=y2plzju0abl4khub
 * 7天最多推荐5个人，共250积分
 */
public class Newton {

    public static List<Task> getDailyTasks() {
        List<Task> taskList = new java.util.ArrayList<>();
        Task task = new Task("newton-1", "roll", 0);
        List<Task.Action> actionList = new java.util.ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.magicnewton.com/portal/rewards", 20));

        task.actionList = actionList;
        taskList.add(task);
        return taskList;

    }
}
