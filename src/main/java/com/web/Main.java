package com.web;

import com.web.model.Account;
import com.web.model.Task;
import com.web.task.*;
import com.web.util.FileUtil;
import com.web.util.MouseUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.web.constant.Constants.*;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Main {

    static List<Account> accountList = FileUtil.readAccountFile();
    static List<Task> taskList;
    static Set<String> finishedTasks = FileUtil.readFinishedTask();

    private static List<Task> initTask(Account account) {
        taskList = new ArrayList<>();
        taskList.addAll(Sepolia.getDailyTasks(account));
        taskList.addAll(DeSpeed.getDailyTasks(account));
        taskList.addAll(LayerEdge.getDailyTasks(account));
        taskList.addAll(Human.getDailyTasks(account));
//        taskList.addAll(Monad.getTalentumVisitTasks(account));
        taskList.addAll(Monad.getRandomTasks(account));
        taskList.addAll(Monad.getDailyTasks(account));
//        taskList.addAll(Monad.getTestTasks(account));
//        taskList.addAll(Monad.getMonadAINFTTasks());
        System.out.println("Main.initTask success size=" + taskList.size());
        return taskList;
    }

    static HashSet<String> testAccounts = new HashSet<>() {
        {
//            add("ads-1");
//            add("ads-2");
//            add("ads-4");
//            add("ads-5");
//            add("ads-6");
//            add("hub-41");
            add("hub-42");
//            add("hub-43");
//            add("hub-44");
//            add("hub-45");
        }
    };

    //TODO 提前打开一个没有用的页面
    public static void main(String[] args) throws Exception {
//        randomSleepMinutes(60, 120);
        Collections.shuffle(accountList);
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            if (!testAccounts.contains(account.name)) {
//                continue;//打开就是单测任务
            }
            if (account.evm == null || account.evm.isEmpty()) {
                continue;
            }
            System.out.println(String.format("%s %s/%s account:%s", getCurrentTime(), i + 1, accountList.size(), account.name));
            Task.Action startAccountAction = new Task.Action(MOVE_AND_CLICK, "", account.x, account.y, 0);
            MouseUtil.executeAction(startAccountAction);
            taskList = initTask(account);
            Collections.shuffle(taskList);

            for (Task task : taskList) {
                System.out.println("current task:" + task.name);
                if (!needToExecuteTask(account.name, task)) {
                    continue;
                }
                for (Task.Action action : task.actionList) {
                    MouseUtil.executeAction(action);
                }
                //任务之间睡眠5秒
                Task.Action action = new Task.Action(SLEEP, "", 0, 0, 5);
                MouseUtil.executeAction(action);
            }
            randomSleepMinutes(5, 10);
        }
    }

    private static void randomSleepMinutes(int min, int max) throws Exception {
        Random random = new Random();
        long randomMinutes = random.nextInt(max - min + 1) + min;
        Thread.sleep(randomMinutes * 60 * 1000);
    }

    public static String getCurrentTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化当前时间
        return now.format(formatter);
    }

    private static boolean needToExecuteTask(String accountName, Task task) {
        if (task.type == TaskType.DAILY) {
            return true;
        }
        String taskUid = accountName + "_" + task.id + "_" + task.name;
        if (finishedTasks.contains(taskUid)) {
            System.out.println("task finished:" + taskUid + " skip");
            return false;
        }
        FileUtil.appendFinishedTask(taskUid);
        return true;
    }


}
