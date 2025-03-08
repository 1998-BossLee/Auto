package com.web.constant;

import java.util.HashSet;

public interface Constants {

    String OPEN_URL = "openUrl";
    String MOVE_AND_CLICK = "moveAndClick";
    String INPUT_TEXT = "inputText";
    String SCROLL_DOWN = "scrollDown";
    String SLEEP = "sleep";

    HashSet<String> DP_ACCOUNTS = new HashSet<>() {
        {
            add("ads-1");
            add("ads-2");
            add("ads-4");
            add("ads-5");
            add("ads-6");
            add("hub-41");
            add("hub-42");
            add("hub-43");
            add("hub-44");
            add("hub-45");
        }
    };

}
