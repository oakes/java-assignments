package com.theironyard;

/**
 * Created by zach on 10/28/15.
 */
public class Message {
    int id;
    int replyId;
    String username;
    String text;

    public Message() {

    }

    public Message(int id, int replyId, String username, String text) {
        this.id = id;
        this.replyId = replyId;
        this.username = username;
        this.text = text;
    }
}
