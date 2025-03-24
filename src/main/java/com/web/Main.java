package com.web;

import com.web.constant.TaskConstant;
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
        taskList.addAll(Newton.getDailyTasks());

        taskList.addAll(Monad.getAllTask(account));
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
//            add("hub-42");
//            add("hub-43");
//            add("hub-44");
//            add("hub-45");
            add("hub-46");
//            add("hub-47");
//            add("hub-48");
//            add("hub-49");
//            add("hub-50");
        }
    };

    static HashSet<String> taskIds = new HashSet<>() {
        {
//            add(TaskConstant.Monad.FAUCET);
//            add(TaskConstant.Monad.FAUCET_MORKIE);
//            add(TaskConstant.Monad.FAUCET_TALENTUM);
//            add(TaskConstant.Monad.FAUCET_MONAI);
//            add(TaskConstant.Monad.FAUCET_NERZO);
//            add(TaskConstant.Monad.NFT_TALENTUM);
//            add(TaskConstant.Monad.NFT_MONAI);
//            add(TaskConstant.Monad.NFT_NERZO);
//            add(TaskConstant.Monad.NFT_MAGICEDEN);
//            add(TaskConstant.Monad.NFT_MORKIE);
//            add(TaskConstant.Monad.NFT_NERZO);
            add(TaskConstant.Monad.VISIT_TALENTUM);
//            add(TaskConstant.Monad.A_PRIOR);h
//            add(TaskConstant.Monad.BEAN);
//            add(TaskConstant.Monad.AICRAFT);
//            add(TaskConstant.Monad.BEBOP);
//            add(TaskConstant.Monad.SHMONAD);
//            add(TaskConstant.Monad.KINZA);
//            add(TaskConstant.Monad.OWLTO);
//            add(TaskConstant.Monad.MINTAIR);
//            add(TaskConstant.Monad.KURU);
//            add(TaskConstant.Monad.KINSU);
//            add(TaskConstant.Monad.MONORAIL);
//
//            add(TaskConstant.DeSpeed.SIGN);
//            add(TaskConstant.Human.SIGN);
//            add(TaskConstant.Human.FAUCET);
//            add(TaskConstant.Sepolia.FAUCET);
//            add(TaskConstant.Newton.SING);
//            add(TaskConstant.LayerEdge.SING);
        }
    };

    //TODO 提前打开一个没有用的页面
    public static void main(String[] args) throws Exception {
//        randomSleepMinutes(60, 120);
        Collections.shuffle(accountList);
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            if (!testAccounts.contains(account.name)) {
                continue;//打开就是单测任务
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
                if (!taskIds.contains(task.id)) {
                    continue;//打开就是单测任务
                }
                System.out.println("current task:" + task.id + "-" + task.name);
                if (!needToExecuteTask(account.name, task)) {
                    continue;
                }
                for (Task.Action action : task.actionList) {
                    MouseUtil.executeAction(action);
                }
                Task.Action action = new Task.Action(SLEEP, "", 0, 0, 10);
                MouseUtil.executeAction(action);
            }
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
