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
import java.util.stream.Collectors;

import static com.web.constant.Constants.*;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description: E:\IdeaProject\Auto\src\main\resources\needConnectUrls.html
 */
public class Main {

    static List<Account> accountList = FileUtil.readAccountFile();
    static List<Task> taskList;
    static Set<String> finishedTasks = FileUtil.readFinishedTask();

    private static List<Task> initRandomTask(Account account) {
        taskList = new ArrayList<>();
//        taskList.addAll(Sepolia.getDailyTasks(account));
//        taskList.addAll(DeSpeed.getDailyTasks(account));
//        taskList.addAll(LayerEdge.getDailyTasks(account));
//        taskList.addAll(Newton.getDailyTasks(account));
//        taskList.addAll(Sahara.getDailyTasks(account));

        taskList.addAll(Monad.getAllTask(account));
        taskList = taskList.stream().filter(task -> taskIds.contains(task.id)).collect(Collectors.toList());
        return taskList;
    }

    static HashSet<String> testAccounts = new HashSet<>() {
        {
            add("google");
            add("ads-1");
            add("ads-2");
            add("ads-4");
            add("ads-5");
            add("ads-6");
            add("hub-41");
            add("hub-42");
            add("hub-43");
            add("hub-44");
            add("hub-45");
            add("hub-46");
            add("hub-47");
            add("hub-48");
            add("hub-49");
            add("hub-50");
            add("hub-51");
            add("hub-52");
            add("hub-53");
            add("hub-54");
            add("hub-55");
        }
    };

    static HashSet<String> taskIds = new HashSet<>() {
        {
//            add(TaskConstant.Monad.SWITCH);


//            add(TaskConstant.Monad.FAUCET);
            add(TaskConstant.Monad.FAUCET_MORKIE);
            add(TaskConstant.Monad.FAUCET_TALENTUM);
            add(TaskConstant.Monad.FAUCET_DUSTED);
            add(TaskConstant.Monad.FAUCET_NERZO);

//            add(TaskConstant.Monad.NFT_TALENTUM);
//            add(TaskConstant.Monad.NFT_MONAI);
//            add(TaskConstant.Monad.NFT_NERZO);
//            add(TaskConstant.Monad.NFT_MAGICEDEN);
//            add(TaskConstant.Monad.NFT_MORKIE);

//            add(TaskConstant.Monad.VISIT_TALENTUM);

//            add(TaskConstant.Monad.MINTAIR);

            add(TaskConstant.Monad.TALENTUM_STREASK);
            add(TaskConstant.Monad.KURU);
            add(TaskConstant.Monad.A_PRIOR);
            add(TaskConstant.Monad.BEAN);
            add(TaskConstant.Monad.AICRAFT);
            add(TaskConstant.Monad.BEBOP);
            add(TaskConstant.Monad.SHMONAD);
            add(TaskConstant.Monad.KINZA);
            add(TaskConstant.Monad.OWLTO);
            add(TaskConstant.Monad.KINTSU);
            add(TaskConstant.Monad.MONORAIL);
            add(TaskConstant.Monad.ATLANTIS);

//            add(TaskConstant.Depin.DESPEED);
//            add(TaskConstant.Depin.BLOCK_MESH);
//            add(TaskConstant.Sepolia.FAUCET);
//            add(TaskConstant.Newton.SIGN);
//            add(TaskConstant.Sahara.FAUCET);
        }
    };

    //TODO 提前打开一个没有用的页面
    public static void main(String[] args) throws Exception {
        Collections.shuffle(accountList);
//        Collections.reverse(accountList);
        while (true) {
            for (int i = 0; i < accountList.size(); i++) {
                Account account = accountList.get(i);
                try {
                    if (!testAccounts.contains(account.name)) {
                        continue;//打开就是单测任务
                    }
                    if (account.evm == null || account.evm.isEmpty()) {
                        continue;
                    }
                    Task.Action startAccountAction = new Task.Action(MOVE_AND_CLICK, "", account.x, account.y, 0);
                    MouseUtil.executeAction(startAccountAction);
                    taskList = initRandomTask(account);

                    for (Task task : taskList) {
                        System.out.println(String.format("%s account:%s %s/%s currentTask:%s", getCurrentTime(), account.name, taskList.indexOf(task) + 1, taskList.size(), task.id + "_" + task.name));
                        if (!needToExecuteTask(account.name, task)) {
                            continue;
                        }
                        for (Task.Action action : task.actionList) {
                            MouseUtil.executeAction(action);
                        }
                        Thread.sleep(3000 + random.nextInt(10000));
                    }
                } catch (Exception e) {
                    System.err.println("error:" + e.getMessage());
                }
            }
            Thread.sleep(20*60*1000);
//            break;
        }
    }

    static Random random = new Random();

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
