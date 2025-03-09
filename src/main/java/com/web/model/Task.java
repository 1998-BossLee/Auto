package com.web.model;

import java.util.List;

import static com.web.constant.Constants.*;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:13
 * @Description:
 */
public class Task {

    public String id;
    public String name;
    public int type; // 0-日常 1-一次性
    public List<Action> actionList;

    public static final class Action {
        //openUrl moveAndClick inputText scrollDown
        public String op;
        public String text;
        public int x;
        public int y;
        //滚动的高度 睡眠时间 等等
        public int h;

        public Action(String op, String text, int x, int y, int h) {
            this.op = op;
            this.text = text;
            this.x = x;
            this.y = y;
            this.h = h;

            //网址打开写死
            if (OPEN_URL.equals(op)) {
                this.x = 1067;
                this.y = 85;
            }
        }

        public Action() {

        }

        public static Action buildOpenUrlAction(String url, int second) {
            Action action = new Action();
            action.op = OPEN_URL;
            action.text = url;
            action.h = second;
            return action;
        }

        public static Action buildMoveClickAction(int x, int y) {
            Action action = new Action();
            action.op = MOVE_AND_CLICK;
            action.x = x;
            action.y = y;
            return action;
        }

        public static Action buildInputTextAction(int x, int y, String text) {
            Action action = new Action();
            action.op = INPUT_TEXT;
            action.x = x;
            action.y = y;
            action.text = text;
            return action;
        }

        public static Action buildScrollDownAction(int h) {
            Action action = new Action();
            action.op = SCROLL_DOWN;
            action.h = h;
            return action;
        }

        public static Action buildSleepAction(int second) {
            Action action = new Action();
            action.op = SLEEP;
            action.h = second;
            return action;
        }

        public static Action buildSignAction() {
            Action action = new Action();
            action.op = SIGN;
            return action;
        }

    }

    public Task(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }


}
