package com.pro.recyclerview;

public class ListItem {
    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    private String head;



    private String desc;


    public ListItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }
}
