package com.web.task;

import com.web.model.Account;
import com.web.model.Task;

import java.util.*;

import static com.web.constant.Constants.*;
import static com.web.constant.Constants.MOVE_AND_CLICK;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Monad {

    //大号和小号，交互金额不一样
    public static List<Task> getMonadTask(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;

        //打开网址-随便找个地方对焦-往下滚动-对焦钱包输入框-输入钱包地址-真人识别-点击领取
//        task = new Task("monad-1", "monad官网领水", 0);
//        taskList.add(task);
//        List<Task.Action> actionList = new ArrayList<>();
//        actionList.add(new Task.Action(OPEN_URL, "https://testnet.monad.xyz/", 0, 0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",0, 0, 0));
//        actionList.add(new Task.Action(SCROLL_DOWN, "", 0,0, -20));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(INPUT_TEXT, account.evm,  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(SLEEP, "",  0,0, 10000)); //给10秒等人机验证
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
//        task.actionList = actionList;
//
//        //打开网址-对焦钱包输入框-输入钱包地址-send
//        task = new Task("monad-2", "monad-morkie领水", 0);
//        taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(new Task.Action(OPEN_URL, "https://faucet.morkie.xyz/monad#google_vignette", 0, 0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(INPUT_TEXT, account.evm,  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
//        task.actionList = actionList;
//
//        //六连击
//        task = new Task("monad-3", "monad-talentum领水", 0);
//        taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(new Task.Action(OPEN_URL, "https://monad.talentum.id/", 0, 0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
//        task.actionList = actionList;




        task = new Task("monad-4", "monad-talentum签到", 0);
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
//            task = new Task("monad-5", "monad-talentum-11个NFT", 0);
//            actionList = new ArrayList<>();
//            actionList.add(new Task.Action(OPEN_URL, url, 1457, 929, 0));

        }


        //打开url、移动到输入框对焦、输入质押金额、质押、签名
        task = new Task("monad-6", "monad-stake质押", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/", 7 * 1000));
        actionList.add(Task.Action.buildMoveClickAction(980, 318));
        actionList.add(Task.Action.buildInputTextAction(random(account, 0.001, 0.01, 4)));
        actionList.add(Task.Action.buildMoveClickAction(948, 563));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        return taskList;
    }

    private static String random(Account account, double min, double max, int digit) {
        double d = min * account.quality + Math.random() * (max - min);
        return String.format("%." + digit + "f", d);
    }

}
