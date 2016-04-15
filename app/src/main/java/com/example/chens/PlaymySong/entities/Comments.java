package com.example.chens.PlaymySong.entities;

import java.util.ArrayList;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public class Comments {
    private String title;
    private String singer;
    private ArrayList<SingleComment> singleComment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public ArrayList<SingleComment> getSingleComment() {
        return singleComment;
    }

    public void addSingleComment(SingleComment singleComment) {
        this.singleComment.add(singleComment);
    }
}
