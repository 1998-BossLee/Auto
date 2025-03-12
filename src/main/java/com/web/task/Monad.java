package com.web.task;

import com.web.model.Account;
import com.web.model.Task;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Monad {

    //大号和小号，交互金额不一样
    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;

        //打开网址-随便找个地方对焦-往下滚动-对焦钱包输入框-输入钱包地址-真人识别-点击领取
        task = new Task("monad-1", "monad官网领水", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://testnet.monad.xyz/", 30));
        actionList.add(Task.Action.buildMoveClickAction(1754, 227));
        actionList.add(Task.Action.buildScrollDownAction(-20));
        actionList.add(Task.Action.buildScrollDownAction(10));
        actionList.add(Task.Action.buildInputTextAction(1234, 392, account.evm));
        actionList.add(Task.Action.buildMoveClickAction(1161, 491));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildMoveClickAction(1343, 567));
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;

        //打开网址-对焦钱包输入框-输入钱包地址-send
        task = new Task("monad-2", "monad-morkie领水A", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 20));
        actionList.add(Task.Action.buildInputTextAction(950, 640, account.evm));
        for (int i=1;i<=5;i++) {
            actionList.add(Task.Action.buildMoveClickAction(950, 700));
            actionList.add(Task.Action.buildSleepAction(30));
        }
        task.actionList = actionList;

//        task = new Task("monad-2", "monad-morkie领水B", 0);
//        taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 20));
//        actionList.add(Task.Action.buildInputTextAction(923, 679, account.evm));
//        actionList.add(Task.Action.buildMoveClickAction(945, 738));
//        actionList.add(Task.Action.buildSleepAction(30));
//        actionList.add(Task.Action.buildMoveClickAction(945, 738));
//        actionList.add(Task.Action.buildSleepAction(30));
//        actionList.add(Task.Action.buildMoveClickAction(945, 738));
//        actionList.add(Task.Action.buildSleepAction(30));
//        actionList.add(Task.Action.buildMoveClickAction(945, 738));
//        actionList.add(Task.Action.buildSleepAction(30));
//        actionList.add(Task.Action.buildMoveClickAction(945, 738));
//        actionList.add(Task.Action.buildSleepAction(30));
//        task.actionList = actionList;

//
        //六连击
        task = new Task("monad-3", "monad-talentum领水", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://monad.talentum.id/", 59));
        actionList.add(Task.Action.buildMoveClickAction(1300, 150));
        actionList.add(Task.Action.buildMoveClickAction(950, 660));
        actionList.add(Task.Action.buildMoveClickAction(950, 580));
        actionList.add(Task.Action.buildMoveClickAction(950, 690));
        actionList.add(Task.Action.buildMoveClickAction(950, 780));
        actionList.add(Task.Action.buildSleepAction(15));
        actionList.add(Task.Action.buildMoveClickAction(950, 665));
        task.actionList = actionList;


//
//
//
//
//        //第一次需要手动对币对授权或者无脑加一个授权按钮的点击
//        task = new Task("monad-8", "monad-Liquidity", 0);
//
//
//
//        //投票
//        task = new Task("monad-8", "monad-Liquidity", 0);
//
//

        return taskList;
    }


    //不算做完，单号，挨个手动做。
    public static List<Task> getTalentumNFTTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
        //暂时没水，不做
        List<String> urls = new ArrayList<>();
        urls.add("https://monad.talentum.id/tasks/task/2739");
        urls.add("https://monad.talentum.id/tasks/task/2797");
        urls.add("https://monad.talentum.id/tasks/task/2745");
        urls.add("https://monad.talentum.id/tasks/task/2750");
        urls.add("https://monad.talentum.id/tasks/task/2760");
        urls.add("https://monad.talentum.id/tasks/task/2779");
        urls.add("https://monad.talentum.id/tasks/task/2812");
        urls.add("https://monad.talentum.id/tasks/task/2813");
        urls.add("https://monad.talentum.id/tasks/task/2857");
        urls.add("https://monad.talentum.id/tasks/task/2838");
        urls.add("https://monad.talentum.id/tasks/task/2778");
        Collections.shuffle(urls);
        for (String url : urls) {
            task = new Task("monad-5", "monad-talentum-11个NFT", 0);
            taskList.add(task);
            actionList = new ArrayList<>();
            //【start work】-【Mint】-【sign】-【verify】-【claim reward】
            actionList.add(Task.Action.buildOpenUrlAction(url, 15));
            actionList.add(Task.Action.buildMoveClickAction(1511, 932));
            actionList.add(Task.Action.buildSleepAction(15));
            actionList.add(Task.Action.buildMoveClickAction(744, 815));
            actionList.add(Task.Action.buildSleepAction(5));
            actionList.add(Task.Action.buildSignAction());
            actionList.add(Task.Action.buildSleepAction(15));
            actionList.add(Task.Action.buildMoveClickAction(1049, 815));
            actionList.add(Task.Action.buildSleepAction(10));
            actionList.add(Task.Action.buildMoveClickAction(1511, 932));
            actionList.add(Task.Action.buildSleepAction(10));
            task.actionList = actionList;
        }

        return taskList;
    }

    public static List<Task> getTalentumVisitTasks() {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
        Map<String, Integer> map = new HashMap<>();
        //taskId - x,x,y
        //最平常的
        int workX = 550, verifyX = 1050;
        map.put("2734,2906,2923,2927,2956,2816,2957", 710);
        map.put("2917", 780);
        //map.put("2918", 780); //需要下滑动
        map.put("2931", 800);
        map.put("2837", 750);
        map.put("2815", 820);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] taskIds = entry.getKey().split(",");
            int y = entry.getValue();
            for (String taskId : taskIds) {
                task = new Task("monad-5", "monad-talentum-visit", 0);
                if (taskId.equals("2918")) {
                    continue;
                }
                taskList.add(task);
                actionList = new ArrayList<>();
                actionList.add(Task.Action.buildOpenUrlAction("https://monad.talentum.id/tasks/task/" + taskId, 20));
                actionList.add(Task.Action.buildMoveClickAction(1511, 932));//[stark work]
                actionList.add(Task.Action.buildSleepAction(15));
                actionList.add(Task.Action.buildMoveClickAction(workX, y));//[visit]
                actionList.add(Task.Action.buildSleepAction(10));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(verifyX, y));//[verify]
                actionList.add(Task.Action.buildSleepAction(5));
                actionList.add(Task.Action.buildMoveClickAction(1511, 932));
                actionList.add(Task.Action.buildSleepAction(10));
                task.actionList = actionList;
            }
        }
        Collections.shuffle(taskList);
        return taskList;
    }

    public static List<Task> getTalentumFollowXTasks(Account account) {
        return null;
    }

    public static List<Task> getRandomTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
        //打开url、移动到输入框对焦、输入质押金额、质押、签名
        task = new Task("monad-6", "monad-Stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/", 20));
        actionList.add(Task.Action.buildInputTextAction(950, 414, random(account, 0.001, 0.01, 4)));
        actionList.add(Task.Action.buildSleepAction(10));//需要一点时间计算质押对应的金额
        actionList.add(Task.Action.buildMoveClickAction(950, 655));
        actionList.add(Task.Action.buildSleepAction(25));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildCancelSignAction());
        task.actionList = actionList;

        //注意换币过程会弹出来价格变动 + 随机选币
        task = new Task("monad-7", "monad-Swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://swap.bean.exchange/swap", 15));
        actionList.add(Task.Action.buildMoveClickAction(1666, 322));//OKX和小狐狸二选一
        actionList.add(Task.Action.buildMoveClickAction(1000, 650));//切链，如果没有也不影响
        actionList.add(Task.Action.buildInputTextAction(950, 508, random(account, 0.01, 0.05, 4)));
        actionList.add(Task.Action.buildMoveClickAction(730, 547));
        List<Integer> swapCoinYs = new ArrayList<>();
        swapCoinYs.add(550);
        swapCoinYs.add(550 + 62);
        swapCoinYs.add(550 + 62 * 2);
        swapCoinYs.add(550 + 62 * 3);
        Collections.shuffle(swapCoinYs);
        actionList.add(Task.Action.buildMoveClickAction(950, swapCoinYs.get(0)));
        actionList.add(Task.Action.buildMoveClickAction(1130, 253));//叉掉
        actionList.add(Task.Action.buildMoveClickAction(950, 675));
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildMoveClickAction(1096, 500));
        actionList.add(Task.Action.buildMoveClickAction(950, 785));
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        Collections.shuffle(taskList);
        return taskList;
    }


    static Random random = new Random();

    private static String random(Account account, double min, double max, int digit) {
        double d = max * account.quality + min + random.nextFloat() * (max - min);
        String s = String.format("%." + digit + "f", d);
        System.out.println("random " + s);
        return s;
    }

}
