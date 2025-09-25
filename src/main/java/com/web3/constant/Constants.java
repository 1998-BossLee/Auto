package com.web3.constant;

import java.util.HashSet;

public interface Constants {

    String OPEN_URL = "openUrl";
    String MOVE = "move";
    String MOVE_AND_CLICK = "moveAndClick";
    String INPUT_TEXT = "inputText";
    String INPUT_TEXT_ENTER = "inputTextEnter";
    String SCROLL_DOWN = "scrollDown";
    String SLEEP = "sleep";
    String SIGN = "sign";
    String CANCEL_SIGN = "cancelSign";
    String CLOSE_WINDOW = "closeWindow";
    String REFRESH = "refresh";
    String META_MASK_SIGN = "metaMaskSign";
    String CANCEL_META_MASK_SIGN = "cancelMetaMaskSign";

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

    interface TaskType {
        int DAILY = 0;
        int NONCE = 1;
    }


}
