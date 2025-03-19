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
        actionList.add(Task.Action.buildMoveClickAction(1754, 327));
        actionList.add(Task.Action.buildScrollDownAction(10));
        actionList.add(Task.Action.buildInputTextAction(1300, 320, account.evm));
        for (int i = 1; i <= 4; i++) {
            actionList.add(Task.Action.buildMoveClickAction(1155, 420));
            actionList.add(Task.Action.buildSleepAction(3));
            actionList.add(Task.Action.buildMoveClickAction(1300, 500));
            actionList.add(Task.Action.buildSleepAction(20));
        }
        task.actionList = actionList;

        //打开网址-对焦钱包输入框-输入钱包地址-send
        task = new Task("monad-2", "monad-morkie领水", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 10));
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 15));
        actionList.add(Task.Action.buildMoveClickAction(950, 570));
        actionList.add(Task.Action.buildInputTextAction(950, 570, account.evm));
        for (int i = 1; i <= 4; i++) {
            actionList.add(Task.Action.buildMoveClickAction(950, 640));
            actionList.add(Task.Action.buildSleepAction(10));
        }
        task.actionList = actionList;


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
        actionList.add(Task.Action.buildSleepAction(40));
        actionList.add(Task.Action.buildMoveClickAction(950, 665));
        actionList.add(Task.Action.buildSleepAction(20));
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


    //只能做一次的任务，例如NFT
    public static List<Task> getNonceTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
        //暂时没水，不做
        String s = "2739,2797,2745,2750,2760,2779,2812,2813,2857,2838,2778";
        String[] taskIds = s.split(",");
        for (String taskId : taskIds) {
            String url = "https://monad.talentum.id/tasks/task/" + taskId;
            task = new Task("monad-5", "monad-talentum-NFT-" + taskId, 1);
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


        return taskList.subList(0, 2);
    }

    public static List<Task> getTalentumVisitTasks(Account account) {
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
        task = new Task("monad-6", "aPrior-stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/", 20));
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildInputTextAction(950, 414, random(account, 0.005, 0.01, 4)));
        actionList.add(Task.Action.buildSleepAction(10));//需要一点时间计算质押对应的金额
        actionList.add(Task.Action.buildMoveClickAction(950, 655));
        actionList.add(Task.Action.buildSleepAction(25));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildCancelSignAction());
        task.actionList = actionList;


        task = new Task("monad-6", "aPrior-withdrawals-request", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/withdrawals?tab=request", 20));
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildInputTextAction(950, 500, random(account, 0.001, 0.005, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 750));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task("monad-6", "aPrior-withdrawals-claim", 0);

        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/withdrawals?tab=claim", 20));
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildMoveClickAction(660, 490));
        actionList.add(Task.Action.buildMoveClickAction(950, 670));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //注意换币过程会弹出来价格变动 + 随机选币
        task = new Task("monad-7", "bean-swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://swap.bean.exchange/swap", 15));
        actionList.add(Task.Action.buildMoveClickAction(1666, 322));//OKX和小狐狸二选一
        actionList.add(Task.Action.buildMoveClickAction(1000, 650));//切链，如果没有也不影响
        actionList.add(Task.Action.buildInputTextAction(950, 508, random(account, 0.001, 0.005, 4)));
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


        //美国IP不能用
//        task = new Task("monad-8", "magma-stake", 0);
//        //taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://www.magmastaking.xyz/", 15));
//        actionList.add(Task.Action.buildInputTextAction(0, 0, random(account, 0.001, 0.005, 4)));
//        actionList.add(Task.Action.buildMoveClickAction(0, 0));
//        actionList.add(Task.Action.buildSignAction());
//        task.actionList = actionList;


        //0.005 gas
        task = new Task("monad-9", "aicraft-vote", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://aicraft.fun/projects/fizen", 20));
        actionList.add(Task.Action.buildMoveClickAction(1700, 300));
        actionList.add(Task.Action.buildScrollDownAction(17));
        int[] aicraftVoteYs = {190, 750};
        int x = 250 + 310 * (random.nextInt(100) % 6), y = aicraftVoteYs[random.nextInt(100) % aicraftVoteYs.length];
        System.out.println("x=" + x + " y=" + y);
        actionList.add(Task.Action.buildMoveClickAction(x, y));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(5));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        //0.002 gas
        task = new Task("monad-9", "bebop-swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://bebop.xyz/trade?network=monad&sell=MON&buy=WMON", 15)); //选好mon和wmon了
        actionList.add(Task.Action.buildInputTextAction(1000, 360, random(account, 0.001, 0.07, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 635)); //Wrap
        actionList.add(Task.Action.buildSleepAction(15)); //等得比较久
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;
        //反操作 https://bebop.xyz/trade?network=monad&sell=WMON&buy=MON


        //clober gas太贵做不起 https://alpha.clober.io/earn?chain=10143

        //0.003gas 这个要研究一下
        task = new Task("monad-10", "shmonad-stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.005, 0.01, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task("monad-10", "shmonad-redeem", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildMoveClickAction(1000, 350));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.001, 0.005, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        //https://monad-test.kinza.finance/#/details/MON 还有其他任务
        task = new Task("monad-11", "kinza-supply", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://monad-test.kinza.finance/#/details/MON", 15)); //选好mon和wmon了
        actionList.add(Task.Action.buildMoveClickAction(1550, 750));
        actionList.add(Task.Action.buildInputTextAction(800, 410, random(account, 0.01, 0.03, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 675));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(25)); //等得比较久
        task.actionList = actionList;


        Collections.shuffle(taskList);
        return taskList.subList(0, 2);
    }

    public static List<Task> getMonadAINFTTasks() {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList = new ArrayList<>();
        List<String> refUrls = new ArrayList<>();
        refUrls.add("https://monai.gg/nft?ref-code=WGVGJA");
        refUrls.add("https://monai.gg/nft?ref-code=8EM4H5");
        refUrls.add("https://monai.gg/nft?ref-code=ME3CNK");
        Collections.shuffle(refUrls);
        task = new Task("monad-12", "monai-nft-week1", 1);
        taskList.add(task);
        actionList.add(Task.Action.buildOpenUrlAction(refUrls.get(0), 15)); //选好mon和wmon了
        actionList.add(Task.Action.buildMoveClickAction(885,585));
        actionList.add(Task.Action.buildCloseWindowAction());
        actionList.add(Task.Action.buildMoveClickAction(885, 650));
        actionList.add(Task.Action.buildCloseWindowAction());
        actionList.add(Task.Action.buildMoveClickAction(885, 715));
        actionList.add(Task.Action.buildCloseWindowAction());
        actionList.add(Task.Action.buildSleepAction(30));
        actionList.add(Task.Action.buildMoveClickAction(1200, 860));
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;
        return taskList;
    }

    public static List<Task> getManualOperationTasks() {
        //owlto 部署合约，手动一次，必须小狐狸
        //https://owlto.finance/deploy/?chain=MonadTestnet
        //https://contracts.mintair.xyz/ 以后og也可以部署
        return null;
    }

    public static List<Task> getTestTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;

        return taskList;
    }

    static Random random = new Random();

    private static String random(Account account, double min, double max, int digit) {
        double d = max * account.quality + min + random.nextFloat() * (max - min);
        String s = String.format("%." + digit + "f", d);
        System.out.println("random " + s);
        return s;
    }

    //x分之一的概率
    private static boolean randomBoolean(int x) {
        return random.nextInt(x) == 0;
    }



}
