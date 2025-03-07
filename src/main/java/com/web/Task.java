package com.web;

import java.util.List;

import static com.web.Constants.OPEN_URL;

/**
 * @author: liyangjin
 * @create: 2025-03-04 17:13
 * @Description:
 */
public class Task {

    public int id;
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

    }

    public Task(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }


}
