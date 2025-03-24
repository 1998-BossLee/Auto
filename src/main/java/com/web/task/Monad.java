package com.web.task;

import com.web.constant.TaskConstant;
import com.web.model.Account;
import com.web.model.Task;
import com.web.util.ToolUtil;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:01
 * @Description:
 */
public class Monad {

    public static List<Task> getAllTask(Account account) {
        List<Task> taskList = new ArrayList<>();
//        taskList.addAll(Monad.getTalentumVisitTasks(account));
        taskList.addAll(Monad.getRandomTasks(account));
        taskList.addAll(Monad.getDailyTasks(account));
        taskList.addAll(Monad.getTestTasks(account));
        taskList.addAll(Monad.getMonadAINFTTasks());
        taskList.addAll(Monad.getNonceTasks(account));
        return taskList;
    }

    //大号和小号，交互金额不一样
    public static List<Task> getDailyTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;

        //打开网址-随便找个地方对焦-往下滚动-对焦钱包输入框-输入钱包地址-真人识别-点击领取
        task = new Task(TaskConstant.Monad.FAUCET, "", 0);
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
        task = new Task(TaskConstant.Monad.FAUCET_MORKIE, "", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 10));
        actionList.add(Task.Action.buildOpenUrlAction("https://faucet.morkie.xyz/monad#google_vignette", 15));
        actionList.add(Task.Action.buildMoveClickAction(950, 570));
        actionList.add(Task.Action.buildInputTextAction(950, 570, account.evm));
        for (int i = 1; i <= 3; i++) {
            actionList.add(Task.Action.buildMoveClickAction(950, 640));
            actionList.add(Task.Action.buildSleepAction(10));
        }
        task.actionList = actionList;


        //六连击
        task = new Task(TaskConstant.Monad.FAUCET_TALENTUM, "", 0);
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
        for (int i = 1; i <= 3; i++) {
            actionList.add(Task.Action.buildSleepAction(5 * 60 + 10));
            actionList.add(Task.Action.buildMoveClickAction(950, 665));
        }
        task.actionList = actionList;


        //monadAI领水 https://monai.gg/faucet 0.125/24h
        //10POL的NFT https://faucet.nerzo.xyz/ 0.15/24h
        return taskList;
    }


    //只能做一次的任务，例如NFT
    public static List<Task> getNonceTasks(Account account) {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList;
//        //暂时没水，不做
//        String s = "2739,2797,2745,2750,2760,2779,2812,2813,2857,2838,2778";
//        List<String> talentumNFTTaskIds = Arrays.asList(s.split(","));
//        Collections.shuffle(talentumNFTTaskIds);
//        talentumNFTTaskIds = talentumNFTTaskIds.subList(0, random.nextInt(talentumNFTTaskIds.size() / 2));
//        for (String talentumNFTTaskId : talentumNFTTaskIds) {
//            String url = "https://monad.talentum.id/tasks/task/" + talentumNFTTaskId;
//            task = new Task(TaskConstant.Monad.NFT_TALENTUM, talentumNFTTaskId, 1);
//            taskList.add(task);
//            actionList = new ArrayList<>();
//            //【start work】-【Mint】-【sign】-【verify】-【claim reward】
//            actionList.add(Task.Action.buildOpenUrlAction(url, 15));
//            actionList.add(Task.Action.buildMoveClickAction(1511, 932));
//            actionList.add(Task.Action.buildSleepAction(15));
//            actionList.add(Task.Action.buildMoveClickAction(744, 815));
//            actionList.add(Task.Action.buildSignAction());
//            actionList.add(Task.Action.buildSleepAction(15));
//            actionList.add(Task.Action.buildMoveClickAction(1049, 815));
//            actionList.add(Task.Action.buildSleepAction(10));
//            actionList.add(Task.Action.buildMoveClickAction(1511, 932));
//            actionList.add(Task.Action.buildSleepAction(10));
//            task.actionList = actionList;
//        }

//        task = new Task(TaskConstant.Monad.NFT_MAGICEDEN, "1", 1);
//        taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://magiceden.us/mint-terminal/monad-testnet/0x3b46f41179ea285b386a2182e6168937ed52a458", 15));
//        actionList.add(Task.Action.buildMoveClickAction(1200, 900));
//        actionList.add(Task.Action.buildSignAction());
//        task.actionList = actionList;


//        //morkie假任务系列
//        //https://morkie.xyz/nads
//        List<String> morkieNFTs = Arrays.asList("nads", "monad", "monhog");
//        Collections.shuffle(morkieNFTs);
//        morkieNFTs = morkieNFTs.subList(0, 1);
//        for (String morkieNFT : morkieNFTs) {
//            task = new Task(TaskConstant.Monad.NFT_MORKIE, morkieNFT, 1);
//            taskList.add(task);
//            actionList = new ArrayList<>();
//            actionList.add(Task.Action.buildOpenUrlAction("https://morkie.xyz/" + morkieNFT, 15));
//            actionList.add(Task.Action.buildMoveClickAction(1800, 200));
//            actionList.add(Task.Action.buildScrollDownAction(3));
//            actionList.add(Task.Action.buildMoveClickAction(1505, 760));//
//            actionList.add(Task.Action.buildCloseWindowAction());
//            actionList.add(Task.Action.buildMoveClickAction(1505, 816));
//            actionList.add(Task.Action.buildCloseWindowAction());
//            actionList.add(Task.Action.buildMoveClickAction(1505, 872));
//            actionList.add(Task.Action.buildCloseWindowAction());
//            actionList.add(Task.Action.buildMoveClickAction(1505, 930));
//            actionList.add(Task.Action.buildCloseWindowAction());
//            actionList.add(Task.Action.buildSleepAction(15));
//            actionList.add(Task.Action.buildMoveClickAction(1200, 620));//Mint NFT
//            actionList.add(Task.Action.buildSignAction());
//            task.actionList = actionList;
//        }
//
        List<String> nerzoNFTs = Arrays.asList("unlocked", "monad", "monadking", "monadian", "senera");
        Collections.shuffle(nerzoNFTs);
        nerzoNFTs = nerzoNFTs.subList(0, 1);
        for (String nerzoNFT : nerzoNFTs) {
            task = new Task(TaskConstant.Monad.NFT_NERZO, nerzoNFT, 1);
            taskList.add(task);
            actionList = new ArrayList<>();
            actionList.add(Task.Action.buildOpenUrlAction("https://nerzo.xyz/" + nerzoNFT, 15));
            //两种位置
            if (nerzoNFT.equals("unlocked") || nerzoNFT.equals("monad")) {

            } else {

            }
            actionList.add(Task.Action.buildMoveClickAction(1620, 570));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1620, 610));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1620, 650));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildMoveClickAction(1620, 690));
            actionList.add(Task.Action.buildCloseWindowAction());
            actionList.add(Task.Action.buildSleepAction(15));
            actionList.add(Task.Action.buildMoveClickAction(1000, 850));//Mint NFT
            actionList.add(Task.Action.buildSignAction());
            task.actionList = actionList;
        }

        return taskList;
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
            for (String taskId : taskIds) {
                int y = entry.getValue();
                task = new Task(TaskConstant.Monad.VISIT_TALENTUM, taskId, 0);
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

        //1000w融资
        task = new Task(TaskConstant.Monad.A_PRIOR, "stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/", 20));
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildInputTextAction(950, 414, random(account, 0.005, 0.01, 4)));
        actionList.add(Task.Action.buildSleepAction(10));//需要一点时间计算质押对应的金额
        actionList.add(Task.Action.buildMoveClickAction(950, 655));
        actionList.add(Task.Action.buildSleepAction(15));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildCancelSignAction());
        task.actionList = actionList;


        task = new Task(TaskConstant.Monad.A_PRIOR, "withdrawals-request", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/withdrawals?tab=request", 20));
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildInputTextAction(950, 500, random(account, 0.001, 0.005, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 750));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.A_PRIOR, "withdrawals-claim", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://stake.apr.io/withdrawals?tab=claim", 20));
        actionList.add(Task.Action.buildMoveClickAction(660, 444));//打勾
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//同意
        actionList.add(Task.Action.buildMoveClickAction(660, 490));
        actionList.add(Task.Action.buildMoveClickAction(950, 670));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //注意换币过程会弹出来价格变动 + 随机选币
        task = new Task(TaskConstant.Monad.BEAN, "swap", 0);
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
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //美国，加拿大IP不能用
//        task = new Task("monad-8", "magma-stake", 0);
//        //taskList.add(task);
//        actionList = new ArrayList<>();
//        actionList.add(Task.Action.buildOpenUrlAction("https://www.magmastaking.xyz/", 15));
//        actionList.add(Task.Action.buildInputTextAction(0, 0, random(account, 0.001, 0.005, 4)));
//        actionList.add(Task.Action.buildMoveClickAction(0, 0));
//        actionList.add(Task.Action.buildSignAction());
//        task.actionList = actionList;


        //0.005 gas
        task = new Task(TaskConstant.Monad.AICRAFT, "vote", 0);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://aicraft.fun/projects/fizen", 25));
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
        task = new Task(TaskConstant.Monad.BEBOP, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://bebop.xyz/trade?network=monad&sell=MON&buy=WMON", 15)); //选好mon和wmon了
        actionList.add(Task.Action.buildInputTextAction(1000, 360, random(account, 0.001, 0.07, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 635)); //Wrap
        actionList.add(Task.Action.buildSleepAction(5)); //等得比较久
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;
        //反操作 https://bebop.xyz/trade?network=monad&sell=WMON&buy=MON


        //clober gas太贵做不起 https://alpha.clober.io/earn?chain=10143


        //0.003gas 这个要研究一下 830w融资
        task = new Task(TaskConstant.Monad.SHMONAD, "stake", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.005, 0.01, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        task = new Task(TaskConstant.Monad.SHMONAD, "redeem", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.shmonad.xyz/", 15));
        actionList.add(Task.Action.buildMoveClickAction(1000, 350));
        actionList.add(Task.Action.buildInputTextAction(950, 610, random(account, 0.001, 0.005, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 700));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;

        //https://monad-test.kinza.finance/#/details/MON 还有其他任务
        task = new Task(TaskConstant.Monad.KINZA, "supply", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://monad-test.kinza.finance/#/details/MON", 15)); //选好mon和wmon了
        actionList.add(Task.Action.buildMoveClickAction(1550, 750));
        actionList.add(Task.Action.buildInputTextAction(800, 410, random(account, 0.01, 0.03, 4)));
        actionList.add(Task.Action.buildMoveClickAction(950, 675));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(25)); //等得比较久
        task.actionList = actionList;


        //owlto
        task = new Task(TaskConstant.Monad.OWLTO, "deploy", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://owlto.finance/deploy/?chain=MonadTestnet", 20));
        actionList.add(Task.Action.buildMoveClickAction(1300, 730));
        actionList.add(Task.Action.buildSleepAction(10)); //等得比较久
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //https://contracts.mintair.xyz/ 以后og也可以部署
        task = new Task(TaskConstant.Monad.MINTAIR, "deploy", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://contracts.mintair.xyz/", 20));
        actionList.add(Task.Action.buildMoveClickAction(875, 575));
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//switch chian
        actionList.add(Task.Action.buildMoveClickAction(950, 780));//deploy
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildCancelSignAction());//如果是二连击，会弹出来2个，关掉一个。
        actionList.add(Task.Action.buildSignAction());//稳妥起见最后再确定一下
        task.actionList = actionList;


        //200w融资 https://www.kuru.io/markets  卡死
        task = new Task(TaskConstant.Monad.KURU, "kuru-lite-sawp", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://www.kuru.io/swap?from=0x0000000000000000000000000000000000000000&to=0xf817257fed379853cDe0fa4F97AB987181B1E5Ea", 25));
        int[] kuruSwapCoinYs = {530, 600, 670, 740};
        if (!ToolUtil.randomBoolean(5)) {
            //4/5的概率进来选其他币，因此外面的USDC也有1/5的概率被选到
            actionList.add(Task.Action.buildMoveClickAction(800, 515));
            int kuruSwapCoinY = kuruSwapCoinYs[random.nextInt(4)];
            actionList.add(Task.Action.buildMoveClickAction(950, kuruSwapCoinY));//选币
        }
        actionList.add(Task.Action.buildInputTextAction(950, 330, random(account, 0.03, 0.07, 4))); //输入mon
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildMoveClickAction(950, 660));
        actionList.add(Task.Action.buildSignAction());
        actionList.add(Task.Action.buildSleepAction(10));
        task.actionList = actionList;
        //TODO 过一段时间再把币换回来

        //400w融资
        task = new Task(TaskConstant.Monad.KINSU, "staking", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://kintsu.xyz/staking", 20));
        actionList.add(Task.Action.buildInputTextAction(950, 670, random(account, 0.02, 0.1, 4))); //输入mon
        actionList.add(Task.Action.buildMoveClickAction(950, 950));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;
        // TODO 换回来，巨亏 https://pandaria.lfj.gg/monad-testnet/swap?outputCurrency=MON&inputCurrency=0x07AabD925866E8353407E67C1D157836f7Ad923e

        //质押 https://testnet-preview.monorail.xyz/
        task = new Task(TaskConstant.Monad.MONORAIL, "swap", 0);
        taskList.add(task);
        actionList = new ArrayList<>();
        actionList.add(Task.Action.buildOpenUrlAction("https://testnet-preview.monorail.xyz/", 20));
        actionList.add(Task.Action.buildInputTextAction(850, 440, random(account, 0.02, 0.07, 4)));
        if (!ToolUtil.randomBoolean(3)) {
            actionList.add(Task.Action.buildMoveClickAction(630, 570));
            int[] cionYs = {580, 650};
            actionList.add(Task.Action.buildMoveClickAction(800, cionYs[random.nextInt(cionYs.length)]));
        }
        actionList.add(Task.Action.buildSleepAction(10));
        actionList.add(Task.Action.buildMoveClickAction(750, 670));
        actionList.add(Task.Action.buildSignAction());
        task.actionList = actionList;


        //360w融资 https://monad.curvance.com/monad 操作很多


        Collections.shuffle(taskList);
        int size = taskList.size();
        size = random.nextInt(taskList.size() / 2 + 2);
        System.out.println("Monad.getRandomTasks size=" + size);
        return taskList.subList(0, size);
    }

    public static List<Task> getMonadAINFTTasks() {
        List<Task> taskList = new ArrayList<>();
        Task task;
        List<Task.Action> actionList = new ArrayList<>();
//        List<String> refUrls = new ArrayList<>();
//        refUrls.add("https://monai.gg/nft?ref-code=WGVGJA");
//        refUrls.add("https://monai.gg/nft?ref-code=8EM4H5");
//        refUrls.add("https://monai.gg/nft?ref-code=ME3CNK");
//        Collections.shuffle(refUrls);
//        task = new Task("monad-12", "monai-nft-week1", 1);
//        taskList.add(task);
//        actionList.add(Task.Action.buildOpenUrlAction(refUrls.get(0), 15)); //选好mon和wmon了
//        actionList.add(Task.Action.buildMoveClickAction(885,585));
//        actionList.add(Task.Action.buildCloseWindowAction());
//        actionList.add(Task.Action.buildMoveClickAction(885, 650));
//        actionList.add(Task.Action.buildCloseWindowAction());
//        actionList.add(Task.Action.buildMoveClickAction(885, 715));
//        actionList.add(Task.Action.buildCloseWindowAction());
//        actionList.add(Task.Action.buildSleepAction(30));
//        actionList.add(Task.Action.buildMoveClickAction(1200, 860));
//        actionList.add(Task.Action.buildSignAction());
//        task.actionList = actionList;


        return taskList;
    }

    public static List<Task> getManualOperationTasks() {

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


}
