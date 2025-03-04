import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * @author: liyangjin
 * @create: 2025-03-04 16:45
 * @Description:
 */
public class MouseUtil {


    static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.err.println("创建机器人失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        findCoordinate();
    }

    private static void findCoordinate() throws Exception {
        System.out.println("请在 3 秒内将鼠标移动到目标位置...");
        TimeUnit.SECONDS.sleep(3); // 等待 3 秒

        // 获取鼠标位置
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();

        // 打印鼠标的 x 和 y 坐标
        System.out.println("鼠标当前位置: X=" + point.x + ", Y=" + point.y);
    }

    public static void move(Robot robot, int targetX, int targetY) throws Exception {
        Point currentPos = MouseInfo.getPointerInfo().getLocation();
        // 设置移动步长
        int step = 10;

        // 计算移动的总距离
        double distance = currentPos.distance(targetX, targetY);
        // 计算移动的总步数
        int totalSteps = (int) (distance / step);
        // 计算每步移动的增量
        double deltaX = (targetX - currentPos.x) / (double) totalSteps;
        double deltaY = (targetY - currentPos.y) / (double) totalSteps;

        // 模拟缓慢移动
        for (int i = 0; i < totalSteps; i++) {
            currentPos.translate((int) deltaX, (int) deltaY);
            robot.mouseMove(currentPos.x, currentPos.y);
            Thread.sleep(10); // 控制移动的速度，单位为毫秒
        }
    }

    private static void moveToAndClick(Robot robot, int x, int y) throws Exception {
        move(robot, x, y);
        //robot.mouseMove(x, y);
        robot.delay(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static void openUrl(Robot robot, String url, int x, int y) throws Exception {

    }

    private static void pressDelete(Robot robot, int x, int y) {
        int times = 10;
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            robot.delay(100); // 等待一段时间，可以调整这个值以控制按键速度
        }
    }


    private static void typeString(Robot robot, String input) {
        for (char c : input.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            robot.delay(50);
        }
    }




}
