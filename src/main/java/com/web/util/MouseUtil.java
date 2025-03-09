package com.web.util;

import com.alibaba.fastjson.JSONObject;
import com.web.model.Task;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static com.web.constant.Constants.*;

/**
 * @author:
 * @create: 2025-03-04 17:11
 * @Description: 鼠标移动与模拟键盘操作工具类
 */
public class MouseUtil {
    static Robot robot;
    static JSONObject config;

    static {
        try {
            robot = new Robot();
            config = FileUtil.readConfig();
        } catch (Exception e) {
            System.err.println("MouseUtil static init error");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws Exception {
        findCoordinate();
        robot.delay(1000);
        //robot.mouseWheel(-20);
//        int x = 1067, y = 85;
//        moveToAndClick( x, y);
//        String url = "https://cloud.google.com/application/web3/faucet/ethereum/sepolia";
//        openUrl( url);
    }

    // 用于测试，获取当前鼠标坐标
    private static void findCoordinate() throws Exception {
        System.out.println("请在 5 秒内将鼠标移动到目标位置...");
        TimeUnit.SECONDS.sleep(5);
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        System.out.println("鼠标当前位置: X=" + point.x + ", Y=" + point.y);
    }

    /**
     * 平滑移动鼠标到指定位置
     */
    public static void move(int targetX, int targetY) throws Exception {
        Point currentPos = MouseInfo.getPointerInfo().getLocation();
        int step = 10;
        double distance = currentPos.distance(targetX, targetY);
        int totalSteps = (int) (distance / step);

        // 如果已经非常接近目标，则直接移动过去，避免除零
        if (totalSteps <= 0) {
            robot.mouseMove(targetX, targetY);
            return;
        }

        double deltaX = (targetX - currentPos.x) / (double) totalSteps;
        double deltaY = (targetY - currentPos.y) / (double) totalSteps;

        // 模拟缓慢移动
        for (int i = 0; i < totalSteps; i++) {
            currentPos.translate((int) deltaX, (int) deltaY);
            robot.mouseMove(currentPos.x, currentPos.y);
            Thread.sleep(10);
        }
        // 确保最后到达目标位置
        robot.mouseMove(targetX, targetY);
    }

    // 移动鼠标到指定位置并左键点击
    private static void moveToAndClick(Task.Action action) throws Exception {
        move(action.x, action.y);
        robot.delay(200);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * 移动到网址输入框位置，对焦，删除原 URL，输入新的 URL 并按回车
     */
    public static void openUrl(Task.Action action) throws Exception {
        action.x = config.getInteger("urlX");
        action.y = config.getInteger("urlY");
        moveToAndClick(action);
        pressDelete(robot);
        typeString(robot, action.text);
        // 模拟按下回车键
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    // 删除文本（全选后连续按 DELETE 键）
    private static void pressDelete(Robot robot) {
        // 全选文本
        pressSelectAll(robot);
        // 删除选中的文本
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);
    }


    // 全选文本（Ctrl+A 或 Command+A）
    private static void pressSelectAll(Robot robot) {
        // 检测操作系统
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows 系统
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } else if (os.contains("mac")) {
            // macOS 系统
            robot.keyPress(KeyEvent.VK_META); // Command 键
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_META);
        }
    }

    // 输入文本（注意：此方法简单处理字符，对于部分特殊字符可能需要额外处理）
    private static void typeString(Robot robot, String input) {
        //switchToEnglishInputMethod(robot);
        for (char c : input.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                continue;
            }

            // 针对大写字母，按住 Shift 键
            boolean isUpperCase = Character.isUpperCase(c);
            if (isUpperCase) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            // 特殊字符处理
            if (c == ':') {
                robot.keyPress(KeyEvent.VK_SHIFT); // 冒号需要按住 Shift
                robot.keyPress(KeyEvent.VK_SEMICOLON); // 输入分号
                robot.keyRelease(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            } else {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }

            if (isUpperCase) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }

            robot.delay(50);
        }
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);
    }

    public static void scrollDown(int scrollAmount) {
        // 模拟鼠标滚轮，scrollAmount 为负值表示向下滚动，正值表示向上滚动
        robot.mouseWheel(scrollAmount);
    }

    // 切换到英文输入法
    private static void switchToEnglishInputMethod(Robot robot) {
        // 检测操作系统
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows 系统，使用 Alt + Shift 切换输入法
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_ALT);
        } else if (os.contains("mac")) {
            // macOS 系统，使用 Control + Space 切换输入法
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    public static void executeAction(Task.Action action) throws Exception {
        robot.delay(1000);
        switch (action.op) {
            case OPEN_URL:
                openUrl(action);
                break;
            case MOVE_AND_CLICK:
                moveToAndClick(action);
                break;
            case INPUT_TEXT:
                moveToAndClick(action);
                typeString(robot, action.text);
                break;
            case SCROLL_DOWN:
                scrollDown(action.h);
            case SLEEP:
                robot.delay(action.h);
            case SIGN:
                action.x = config.getInteger("signX");
                action.y = config.getInteger("signY");
                robot.delay(5000);
                moveToAndClick(action);
                break;
        }
    }

}
