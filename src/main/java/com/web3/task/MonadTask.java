package com.web3.task;

import com.web3.*;
import java.util.*;

import static com.web3.Constants.*;
import static com.web3.Constants.MOVE_AND_CLICK;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class MonadTask {

    //大号和小号，交互金额不一样
    public static List<Task> getMonadTask(Account account) {
        List<Task> taskList = new ArrayList<>();

        Task task = new Task(102, "monad官网领水", 0);
        taskList.add(task);
        List<Task.Action> actionList = new ArrayList<>();
        //打开网址-随便找个地方对焦-往下滚动-对焦钱包输入框-输入钱包地址-真人识别-点击领取
        actionList.add(new Task.Action(OPEN_URL, "https://testnet.monad.xyz/", 0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",0, 0, 0));
        actionList.add(new Task.Action(SCROLL_DOWN, "", 0,0, -20));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(INPUT_TEXT, account.evm,  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(SLEEP, "",  0,0, 10000));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));
        task.actionList = actionList;

        task = new Task(103, "monad-morkie领水", 0);
        taskList.add(task);
        //打开网址-对焦钱包输入框-输入钱包地址-send
        actionList.add(new Task.Action(OPEN_URL, "https://faucet.morkie.xyz/monad#google_vignette", 0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(INPUT_TEXT, account.evm,  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "", 0, 0, 0));

        task = new Task(104, "monad-talentum领水", 0);
        //六连击
        actionList.add(new Task.Action(OPEN_URL, "https://monad.talentum.id/", 0, 0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));
        actionList.add(new Task.Action(MOVE_AND_CLICK, "",  0,0, 0));


        task = new Task(105, "monad-talentum签到", 0);


        return taskList;
    }

    private static String random(Account account, double min, double max, int digit) {
        double d = min + Math.random() * (max - min) * account.quality;
        return String.format("%." + digit + "f", d);
    }

}
