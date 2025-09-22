package com.web.task;

import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;
import com.web.util.BalanceCheckerUtil;
import com.web.util.ToolUtil;

import java.util.*;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Monad {

    public static List<Task> getAllTask(Account account) {
        LinkedList<Task> taskList = new LinkedList<>();
        taskList.addAll(Monad.getTalentumVisitTasks(account));
        taskList.addAll(Monad.getRandomTasks(account));
//        taskList.addAll(Monad.getMonadAINFTTasks());
        taskList.addAll(Monad.getTalentumNFTsTasks(account));
        taskList.addAll(Monad.getMorkieNFTTasks(account));
        taskList.addAll(Monad.getNerzoNFTTasks(account));
        Collections.shuffle(taskList);

        //穷鬼账号只做2个任务
        String  poorAccountIds = "hub-51,hub-52,hub-53,hub-54,hub-55";
        if (poorAccountIds.contains(account.name)) {
            taskList = new LinkedList<>();
            taskList.addAll(Monad.getRandomTasks(account).subList(0, 3));
        }
        //全部打乱，领水任务重中之重，放在最前
        List<Task> faucetTasks = Monad.getFaucetTasks(account);
        for (Task task : faucetTasks) {
            taskList.addFirst(task);
//            taskList.add(task);
        }

        //最后加上switch任务
//        taskList.addFirst(switchMonadTask());
        return taskList;
    }

    public static Task switchMonadTask() {
        Task task = new Task(TaskConstant.Monad.SWITCH, "", 0);
        List<Task.Action> actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildMoveClickAction(1700, 160));//
        actionList.add(Task.Action.buildMoveClickAction(830, 600));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;
        return task;
    }

    //大号和小号，交互金额不一样
    public static List<Task> getFaucetTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;

        //打开网址-随便找个地方对焦-往下滚动-对焦钱包输入框-输入钱包地址-真人识别-点击领取
        if (BalanceCheckerUtil.getChainNativeBalance("eth", account.evm) > 0.03) {
            task = new Task(TaskConstant.Monad.FAUCET, "", 0);
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction("https://faucet.monad.xyz/", 20));
            for (int i = 1; i <= 3; i++) {
//                actionList.add(Task.Action.buildInputTextAction(950, 460, account.evm)); //input
//                actionList.add(Task.Action.buildSleepAction(10));//load
                actionList.add(Task.Action.buildMoveClickAction(780, 570));//human
                actionList.add(Task.Action.buildSleepAction(10));//reload
                actionList.add(Task.Action.buildMoveClickAction(950, 650));//get token
                actionList.add(Task.Action.buildSleepAction(10));//wait for submit
            }
            task.actionList = actionList;
        }


        task = new Task(TaskConstant.Sepolia.QUICK_NODE, "", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.quicknode.com/drip", 10));
        actionList.add(Task.Action.buildMoveClickAction(0, 0));//Connect Wallet
        actionList.add(Task.Action.buildMoveClickAction(0, 0));//Connect Metamask
        actionList.add(Task.Action.buildMoveClickAction(1800, 200));
        actionList.add(Task.Action.buildScrollDownAction(3));//往下滚动
        actionList.add(Task.Action.buildMoveClickAction(0, 0));//Continue
        actionList.add(Task.Action.buildInputTextAction(0, 0, "https://x.com/NePbYF6hKc73025/status/1922475877398380677"));//Tweet URL
        actionList.add(Task.Action.buildMoveClickAction(0, 0));//Claim
        task.actionList = actionList;

        //打开网址-对焦钱包输入框-输入钱包地址-send
        task = new Task(TaskConstant.Monad.FAUCET_MORKIE, "", 0);
        String morkieAccounts = "google,ads-1,ads-2,ads-4,ads-5,ads-6,hub-41,hub-42,hub-43,hub-44,hub-45,hub-46,hub-47,hub-48,hub-49,hub-50,hub-51,hub-52,hub-53,hub-54,hub-55";
        if (morkieAccounts.contains(account.name)) {
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 5));
            actionList.add(Task.Action.refreshPageAction(7));
            actionList.add(Task.Action.buildMoveClickAction(950, 570));
            actionList.add(Task.Action.buildInputTextAction(950, 570, account.evm));
            for (int i = 1; i <= 3; i++) {
                actionList.add(Task.Action.buildMoveClickAction(950, 630));
                actionList.add(Task.Action.buildSleepAction(5));
            }
            task.actionList = actionList;
        }


        String memeBridgeAccounts = "google,ads-1,ads-2,ads-4,hub-49";
        task = new Task(TaskConstant.Monad.FAUCET_MEME, "", 0);
        if (memeBridgeAccounts.contains(account.name)) {
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction("https://www.memebridge.xyz/faucet/monad", 10));
            actionList.add(Task.Action.buildInputTextAction(1220, 550, account.evm));

            actionList.add(Task.Action.buildMoveClickAction(1220, 655));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1630, 655));
            actionList.add(Task.Action.buildSleepAction(5));

            actionList.add(Task.Action.buildMoveClickAction(1220, 745));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1630, 745));
            actionList.add(Task.Action.buildSleepAction(5));


            actionList.add(Task.Action.buildMoveClickAction(1300, 830));
            actionList.add(Task.Action.buildSleepAction(5));
            task.actionList = actionList;
        }



        //六连击
        task = new Task(TaskConstant.Monad.FAUCET_TALENTUM, "", 0);
        taskList.add(task);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://monad.talentum.id/", 20));
        actionList.add(Task.Action.buildMoveClickAction(1210, 270));
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildMoveClickAction(1770, 150));
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildMoveClickAction(910, 770));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));


        actionList.add(Task.Action.buildMoveClickAction(1240, 160));
        actionList.add(Task.Action.buildMoveClickAction(950, 660));
        actionList.add(Task.Action.buildMoveClickAction(950, 580));
        actionList.add(Task.Action.buildMoveClickAction(950, 690));
        actionList.add(Task.Action.buildMoveClickAction(950, 780));
        actionList.add(Task.Action.buildSleepAction(15));
        actionList.add(Task.Action.buildMoveClickAction(950, 665));
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;




        task = new Task(TaskConstant.Monad.FAUCET_DUSTED, "", 0);
        String dustedAccountIds = "ads-1,ads-2,ads-4,ads-6,hub-41,hub-42,hub-43,hub-44,hub-45,hub-46,hub-47,hub-48";
        if (dustedAccountIds.contains(account.name)) {
            taskList.add(task);
        }
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.dusted.app/login", 15));
        //登录
        actionList.add(Task.Action.buildMoveClickAction(950, 670));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSignAction());
//        actionList.add(Task.Action.buildSleepAction(15));
//        actionList.add(Task.Action.buildMoveClickAction(950, 900));
//        actionList.add(Task.Action.buildSleepAction(5));
        //乞讨
//        String[] tipWords = {"somebody can tip me some mon, thanks!", "please tip me some mon, thanks!", "who can tip me some mon, thanks!", "I need some mon, thanks!"};
//        int tipWordIndex = random.nextInt(tipWords.length);
//        actionList.add(Task.Action.buildInputTextEnterAction(950, 1000, tipWords[tipWordIndex]));
//        actionList.add(Task.Action.buildInputTextEnterAction(950, 1000, tipWords[tipWordIndex]));
        //打开游戏跳过对话
        actionList.add(Task.Action.buildMoveClickAction(1750, 450));
        actionList.add(Task.Action.buildSleepAction(7));
        actionList.add(Task.Action.buildMoveClickAction(700, 450));
        actionList.add(Task.Action.buildSleepAction(7));
        actionList.add(Task.Action.buildMoveClickAction(700, 450));
        actionList.add(Task.Action.buildSleepAction(7));
        actionList.add(Task.Action.buildMoveClickAction(700, 450));
        //开始
        actionList.add(Task.Action.buildSleepAction(7));//start
        actionList.add(Task.Action.buildMoveClickAction(960, 620));//打开宝箱
        actionList.add(Task.Action.buildSleepAction(5));
        int dustedCnt = 4 + random.nextInt(5);
        for (int i = 1; i <= dustedCnt; i++) {
            actionList.add(Task.Action.buildMoveClickAction(960, 650));//play again
            actionList.add(Task.Action.buildMoveClickAction(960, 620));//打开宝箱
            actionList.add(Task.Action.buildSleepAction(8));
        }
        actionList.add(Task.Action.buildMoveClickAction(1885, 160));//close
        actionList.add(Task.Action.buildMoveClickAction(1700, 520));//claim
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;
        return taskList;
    }


    //只能做一次的任务，例如NFT
    public static List<Task> getTalentumNFTsTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        String alreadyAccount = "google";
        if (alreadyAccount.contains(account.name)) {
            return taskList;
        }
        Task task;
        List<Task.Action> actionList;
        //暂时没水，不做
        String s = "2750,2812,2857,2838,2778,2925,2920,2959,2914,2934,2909";
        s = "4648,2842,4759,3063,3118,4760,2867,4761,4795,2724,2922,2843,3121,4759,2998,2999,3122,4804,4805,2963,4809,4762,4817,3060,3156,3154,4651,3124,3120,4788,3119";
        List<String> talentumNFTTaskIds = Arrays.asList(s.split(","));
        Collections.shuffle(talentumNFTTaskIds);
//        talentumNFTTaskIds = talentumNFTTaskIds.subList(0, random.nextInt(talentumNFTTaskIds.size() / 3));
        for (String talentumNFTTaskId : talentumNFTTaskIds) {
            String url = "https://monad.talentum.id/tasks/task/" + talentumNFTTaskId;
            task = new Task(TaskConstant.Monad.NFT_TALENTUM, talentumNFTTaskId, 0);
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction(url, 10));
            actionList.add(Task.Action.buildMoveClickAction(1511, 932));// 【start work】
//            actionList.add(Task.Action.buildSleepAction(15));
//
//            actionList.add(Task.Action.buildMoveClickAction(550, 810));//【Mint】
//            actionList.add(Task.Action.buildSleepAction(5));
//            actionList.add(Task.Action.buildSignAction());//【sign】
//            actionList.add(Task.Action.buildSleepAction(5));
//            actionList.add(Task.Action.buildMoveClickAction(1050, 810));//【verify】
//            actionList.add(Task.Action.buildMoveClickAction(1511, 932));//【claim reward】
            task.actionList = actionList;
        }
        Collections.shuffle(taskList);
        return taskList;
    }

    public static List<Task> getMorkieNFTTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
        //morkie假任务系列
        //https://morkie.xyz/nads
        List<String> morkieNFTs = Arrays.asList("nads", "monarch", "monhog", "monad");
        Collections.shuffle(morkieNFTs);
        for (String morkieNFT : morkieNFTs) {
            task = new Task(TaskConstant.Monad.NFT_MORKIE, morkieNFT, 1);
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction("https://morkie.xyz/" + morkieNFT, 15));
            actionList.add(Task.Action.buildMoveClickAction(1800, 200));
            actionList.add(Task.Action.buildScrollDownAction(3));//往下滚动

            actionList.add(Task.Action.buildMoveClickAction(1505, 760));//
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1505, 816));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1505, 872));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1505, 940));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildSleepAction(15));
            actionList.add(Task.Action.buildMoveClickAction(1200, 620));//Mint NFT

            actionList.add(Task.Action.buildSignAction());
            task.actionList = actionList;
        }
        return taskList;
    }

    public static List<Task> getNerzoNFTTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
        List<String> nerzoNFTs = Arrays.asList("unlocked", "monad", "monadking", "monadian", "senera");
        Collections.shuffle(nerzoNFTs);
        for (String nerzoNFT : nerzoNFTs) {
            task = new Task(TaskConstant.Monad.NFT_NERZO, nerzoNFT, 1);
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction("https://nerzo.xyz/" + nerzoNFT, 15));
            //两种位置
            if (nerzoNFT.equals("unlocked") || nerzoNFT.equals("monad")) {
                actionList.add(Task.Action.buildMoveClickAction(1620, 600));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(1620, 640));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(1620, 680));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(1620, 720));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildSleepAction(25));
                actionList.add(Task.Action.buildMoveClickAction(1000, 880));//Mint NFT
                actionList.add(Task.Action.buildSignAction());
            } else {
                actionList.add(Task.Action.buildMoveClickAction(1620, 570));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(1620, 610));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(1620, 650));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(1620, 690));
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildSleepAction(25));
                actionList.add(Task.Action.buildMoveClickAction(1000, 850));//Mint NFT
                actionList.add(Task.Action.buildSignAction());
            }
            task.actionList = actionList;
        }
        return taskList;
    }


    public static List<Task> getTalentumVisitTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        String alreadyAccount = "google,hub-55";
        if (alreadyAccount.contains(account.name)) {
            return taskList;
        }
        Task task;
        List<Task.Action> actionList;
        Map<String, Integer> map = new HashMap<>();
        //taskId - x,x,y
        //最平常的
        int workX = 550, verifyX = 1050;
        //map.put("3085", 670);
        map.put("2956,2957,3113,3116,3124,3156,3159,3120,3162,3165,3166,3168,3169,4638,3170,3171,3172,3152,3154,2960,2961,2955,3001,3158,3119,4651", 700);
        map.put("3159,3163,3164,4640,4642,4643,4645,3157", 730);
        map.put("3160,3167,3161", 750);
        map.put("4637", 800);
        map.put("4636,4641", 850);
        map.put("4639", 890);
        map.put("2862", 950);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] taskIds = entry.getKey().split(",");
            for (String taskId : taskIds) {
                int y = entry.getValue();
                task = new Task(TaskConstant.Monad.VISIT_TALENTUM, taskId, 1);
                taskList.add(task);
                actionList = new ArrayList<>();
                actionList.add(Task.Action.buildOpenUrlAction("https://monad.talentum.id/tasks/task/" + taskId, 20));
                actionList.add(Task.Action.buildMoveClickAction(1511, 932));//[stark work]
                actionList.add(Task.Action.buildSleepAction(15));
                actionList.add(Task.Action.buildMoveClickAction(workX, y));//[visit]
                actionList.add(Task.Action.buildCloseWindowAction());
                actionList.add(Task.Action.buildMoveClickAction(verifyX, y));//[verify]
                actionList.add(Task.Action.buildSleepAction(5));
                actionList.add(Task.Action.buildMoveClickAction(1511, 932));
                actionList.add(Task.Action.buildSleepAction(5));
                task.actionList = actionList;
            }
        }
        Collections.shuffle(taskList);
        taskList = taskList.subList(0, taskList.size()/2);
        return taskList;
    }


    public static List<Task> getRandomTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;

        //1000w融资
        task = new Task(TaskConstant.Monad.A_PRIOR, "stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/", 15));
        actionList.add(Task.Action.buildMoveClickAction(950, 600));//切换到monad链
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildInputTextAction(950, 414, random(account, 0.001, 0.005, 4)));
        actionList.add(Task.Action.buildSleepAction(10));//需要一点时间计算质押对应的金额
        actionList.add(Task.Action.buildMoveClickAction(950, 655));
        actionList.add(Task.Action.buildSleepAction(15));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;


        task = new Task(TaskConstant.Monad.A_PRIOR, "withdrawals-request", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/withdrawals?tab=request", 15));
        actionList.add(Task.Action.buildMoveClickAction(950, 600));//切链
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildInputTextAction(950, 500, random(account, 0.01, 0.02, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 750));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        //TODO 对不准
        task = new Task(TaskConstant.Monad.A_PRIOR, "withdrawals-claim", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/withdrawals?tab=claim", 15));
        actionList.add(Task.Action.buildMoveClickAction(950, 600));//切链
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(30));
        actionList.add(Task.Action.buildMoveClickAction(660, 705));//勾选最后一个
        actionList.add(Task.Action.buildMoveClickAction(950, 820));//claim
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //注意换币过程会弹出来价格变动 + 随机选币
        task = new Task(TaskConstant.Monad.BEAN, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://swap.bean.exchange/swap", 7));
        actionList.add(Task.Action.refreshPageAction(7));
        actionList.add(Task.Action.buildMoveClickAction(1666, 322));//OKX和小狐狸二选一
        actionList.add(Task.Action.buildMoveClickAction(1000, 650));//切链，如果没有也不影响
        actionList.add(Task.Action.buildInputTextAction(950, 508, random(account, 0.001, 0.005, 4)));
        actionList.add(Task.Action.buildMoveClickAction(730, 547));
        int roll = random.nextInt(100) % 4;
//        actionList.add(Task.Action.buildScrollDownAction(roll));
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
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        task = new Task(TaskConstant.Monad.ATLANTIS, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://app.atlantisdex.xyz/mint-nft", 10));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(20));
        actionList.add(Task.Action.buildMoveClickAction(950, 720));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildOpenUrlAction("https://app.atlantisdex.xyz/swap/v4/?inputCurrency=0x0000000000000000000000000000000000000000&outputCurrency=0x1eA9099E3026e0b3F8Dd6FbacAa45f30fCe67431", 15));
        String amount = random(account, 0.0001, 0.0005, 4);
        int atlantiCnt = 10 + random.nextInt(5);
        for (int i = 1; i <= atlantiCnt; i++) {
            actionList.add(Task.Action.buildInputTextAction(900, 440, amount));
            actionList.add(Task.Action.buildSleepAction(5));
            actionList.add(Task.Action.buildMoveClickAction(950, 680));
            actionList.add(Task.Action.buildSignAction());
            actionList.add(Task.Action.buildSleepAction(3 + random.nextInt(7)));
        }
        actionList.add(Task.Action.buildOpenUrlAction("https://app.atlantisdex.xyz/mint-nft", 10));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(20));
        actionList.add(Task.Action.buildMoveClickAction(950, 720));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;





        //0.005 gas
        task = new Task(TaskConstant.Monad.AICRAFT, "vote", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://aicraft.fun/projects/fizen", 20));
        actionList.add(Task.Action.buildMoveClickAction(1050, 700));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(5));
        actionList.add(Task.Action.buildMoveClickAction(1780, 705));//connect
        actionList.add(Task.Action.buildMoveClickAction(1050, 435));//okx
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildMoveClickAction(1050, 700));//sign
        actionList.add(Task.Action.buildSleepAction(15));//claim
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(12));
        int aicraftCnt = account.quality * 10 + 1 + random.nextInt(10);
        System.out.println("aicraftCnt: " + aicraftCnt);
        while (aicraftCnt-- > 0) {
            int[] aicraftVoteYs = {200, 780};
            int x = 250 + 310 * (random.nextInt(100) % 6), y = aicraftVoteYs[random.nextInt(100) % aicraftVoteYs.length];
            actionList.add(Task.Action.buildMoveClickAction(x, y));
            actionList.add(Task.Action.buildSignAction());
            actionList.add(Task.Action.buildSignAction());
            actionList.add(Task.Action.buildSleepAction(3 + random.nextInt(15)));
        }
        task.actionList = actionList;

        //0.002 gas
        task = new Task(TaskConstant.Monad.BEBOP, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        String[] bebopUrls = {"https://bebop.xyz/trade?network=monad&sell=MON&buy=WMON",
                "https://bebop.xyz/trade?network=monad&sell=WMON&buy=MON"};
        //USDC报价一直有问题
        List<String> bebopUrlList = Arrays.asList(bebopUrls);
        Collections.shuffle(bebopUrlList);
        for (int i = 0; i < bebopUrlList.size(); i++) {
            actionList.add(Task.Action.buildOpenUrlAction(bebopUrlList.get(i), 15));
            actionList.add(Task.Action.buildInputTextAction(1000, 390, random(account, 0.005, 0.01, 4)));
            actionList.add(Task.Action.buildMoveClickAction(950, 660)); //Wrap
            actionList.add(Task.Action.buildSignAction());
            actionList.add(Task.Action.buildSleepAction(5));
        }
        task.actionList = actionList;



        //0.003gas 这个要研究一下 830w融资
        task = new Task(TaskConstant.Monad.SHMONAD, "stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.01, 0.02, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.SHMONAD, "redeem", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildMoveClickAction(1000, 350));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.001, 0.01, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        task = new Task(TaskConstant.Monad.A_PRIOR, "checkIn", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://of.apr.io/dashboard", 15));
        actionList.add(Task.Action.buildMoveClickAction(1796, 159));
        actionList.add(Task.Action.buildMoveClickAction(950, 460));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildMoveClickAction(1050, 700));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;



        //owlto
        task = new Task(TaskConstant.Monad.OWLTO, "deploy", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://owlto.finance/deploy/?chain=MonadTestnet", 15));
        actionList.add(Task.Action.buildMoveClickAction(1700, 170)); //Connect Wallet
        actionList.add(Task.Action.buildMoveClickAction(1150, 500)); //okx
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildMoveClickAction(1300, 700));
        actionList.add(Task.Action.buildSleepAction(5)); //等得比较久
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //https://contracts.mintair.xyz/ 位置老是变
//        task = new Task(TaskConstant.Monad.MINTAIR, "deploy", 0);
//        taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://contracts.mintair.xyz/", 20));
//        actionList.add(Task.Action.buildMoveClickAction(750, 530));//monad
//        actionList.add(Task.Action.buildMoveClickAction(950, 830));//switch chian 或者 deploy
//        actionList.add(Task.Action.buildSignAction());
//        task.actionList = actionList;


        //200w融资 https://www.kuru.io/markets  卡死
        //第一次
        task = new Task(TaskConstant.Monad.KURU, "kuru-lite-sawp", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        //随机选个币
        String[] kuruSwapCoin = {"0xf817257fed379853cDe0fa4F97AB987181B1E5Ea", "0xe0590015a873bf326bd645c3e1266d4db41c4e6b", "0xe1d2439b75fb9746e7bc6cb777ae10aa7f7ef9c5",
                "0x3a98250f98dd388c211206983453837c8365bdc1", "0xfe140e1dce99be9f4f15d657cd9b7bf622270c50", "0x0f0bdebf0f83cd1ee3974779bcb7315f9808c714",
                "0x34d1ae6076aee4072f54e1156d2e507dd564a355", "0x73a58b73018c1a417534232529b57b99132b13d2", "0xb2f82d0f38dc453d596ad40a37799446cc89274a",
                "0x268e4e24e0051ec27b3d27a95977e71ce6875a05", "0x07aabd925866e8353407e67c1d157836f7ad923e", "0x760afe86e5de5fa0ee542fc7b7b713e1c5425701",
                "0xabd7afa2161eb7254c0a9dbb5fe79216b7c28e03", "0x39e95286dd43f8da34cbda8e4b656da9f53ca644", "0x743cef7ccc8ac56605c8404607142e5b35efa11d",
                "0x4c10428ed0410dfb2de62fc007f7c1105ae861e9", "0x2fa2c507289be90ca50a8802f8d436d43001b521", "0x2bb4219b8e85c111613f3ee192a115676f230d35",
                "0x8507f576eb214d172012065d58cfb38a4540b0a6", "0x91d00559ecda52d8d5ea87c2ccdc8d7d2abcb684"};
        String kuruUrl = "https://www.kuru.io/swap?from=0x0000000000000000000000000000000000000000&to="+kuruSwapCoin[random.nextInt(kuruSwapCoin.length)];

        actionList.add(Task.Action.buildOpenUrlAction(kuruUrl, 15));
        actionList.add(Task.Action.buildInputTextAction(1120, 350, random(account, 0.003, 0.01, 4))); //输入mon
        actionList.add(Task.Action.buildSleepAction(random.nextInt(5) + 5));
        actionList.add(Task.Action.buildMoveClickAction(950, 700)); //swap
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(random.nextInt(5) + 2));
        task.actionList = actionList;


        //400w融资
        task = new Task(TaskConstant.Monad.KINTSU, "staking", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://kintsu.xyz/staking", 15));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.01, 0.03, 4))); //输入mon
        actionList.add(Task.Action.buildMoveClickAction(1000, 930));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.KINTSU, "unstake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://kintsu.xyz/unstaking", 15));
        actionList.add(Task.Action.buildMoveClickAction(850, 600));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(3));
        actionList.add(Task.Action.buildInputTextAction(950, 480, random(account, 0.001, 0.008, 4))); //输入mon
        actionList.add(Task.Action.buildMoveClickAction(1000, 800));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.KINTSU, "useDefi", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://kintsu.xyz/unstaking", 15));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(3));
        actionList.add(Task.Action.buildMoveClickAction(1280, 800));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //质押 https://testnet-preview.monorail.xyz/ 垃圾项目，dc才几千人
        task = new Task(TaskConstant.Monad.MONORAIL, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://testnet-preview.monorail.xyz/", 15));
        actionList.add(Task.Action.buildInputTextAction(850, 440, random(account, 0.002, 0.007, 4)));
        actionList.add(Task.Action.buildMoveClickAction(650, 590));
        actionList.add(Task.Action.buildMoveClickAction(580, 510));//verified
        actionList.add(Task.Action.buildMoveWithoutClickAction(750, 500));
        actionList.add(Task.Action.buildScrollDownAction(random.nextInt(10)));
        actionList.add(Task.Action.buildMoveClickAction(800, 500));
        actionList.add(Task.Action.buildMoveClickAction(750, 700));
        actionList.add(Task.Action.buildSleepAction(random.nextInt(7)));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.TALENTUM_STREASK, "", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://monad.talentum.id/", 20));
        actionList.add(Task.Action.buildMoveClickAction(1600, 160));//streak now!
        actionList.add(Task.Action.buildMoveClickAction(950, 790));//streak now!
        actionList.add(Task.Action.buildMoveClickAction(1666, 322));//选钱包
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(5));
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.MONAD, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://testnet.monad.xyz/", 15));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(3));
        task.actionList = actionList;

        //360w融资 https://monad.curvance.com/monad 操作很多


        //https://glacierfi.com/


        Collections.shuffle(taskList);
        int size = taskList.size();
        List<Task> doubleTaskList = new ArrayList<>();
        doubleTaskList.addAll(taskList);
        if (account.quality == 0) {
            return taskList;
        } else {
            return doubleTaskList;
        }
    }

    public static List<Task> getMonadAINFTTasks() {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList = new ArrayList<>();
        List<String> refUrls = new ArrayList<>();
        refUrls.add("https://monai.gg/nft?ref-code=WGVGJA");
        refUrls.add("https://monai.gg/nft?ref-code=8EM4H5");
        refUrls.add("https://monai.gg/nft?ref-code=ME3CNK");
        refUrls.add("https://monai.gg/nft?ref-code=QN7P0G");
        Collections.shuffle(refUrls);
        task = new Task(TaskConstant.Monad.NFT_MONAI, "week6", 1);
        taskList.add(task);
        actionList.add(Task.Action.buildOpenUrlAction(refUrls.get(0), 15));
        actionList.add(Task.Action.buildMoveClickAction(885,585));
        actionList.add(Task.Action.buildCloseWindowAction());
        actionList.add(Task.Action.buildMoveClickAction(885, 650));
        actionList.add(Task.Action.buildCloseWindowAction());
        actionList.add(Task.Action.buildMoveClickAction(885, 715));
        actionList.add(Task.Action.buildCloseWindowAction());
        actionList.add(Task.Action.buildSleepAction(30));
        actionList.add(Task.Action.buildMoveClickAction(1200, 860));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        return taskList;
    }


    static Random random = new Random();

    private static String random(Account account, double min, double max, int digit) {
        double d = max * account.quality + min + random.nextFloat() * (max - min);
        String s = String.format("%." + digit + "f", d);
        return s;
    }


}
