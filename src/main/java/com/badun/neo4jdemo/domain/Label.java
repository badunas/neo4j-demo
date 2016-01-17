package com.badun.neo4jdemo.domain;

import java.util.Date;

/**
 * Created by Artsiom Badun.
 */
public enum  Label {
    USER("User"),
    VIDEO("Video"),
    POST("Post"),
    COMMENT("Comment");

    private String val;

    Label(String val) {
        this.val = val;
    }

    public String val() {
        return val;
    }

    public static Label fromUsid(String usid) {
        if (usid.contains("user")) {
            return USER;
        } else if (usid.contains("video")) {
            return VIDEO;
        } else if (usid.contains("post")) {
            return POST;
        } else if (usid.contains("comm")) {
            return COMMENT;
        } else {
            throw new IllegalArgumentException("usid = " + usid);
        }
    }
}
