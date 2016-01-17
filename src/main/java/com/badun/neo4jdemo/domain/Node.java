package com.badun.neo4jdemo.domain;

import java.util.List;

/**
 * Created by Artsiom Badun.
 */
public class Node {
    public final String usid;
    public final Label label;
    public final List<Property> properties;

    public Node(String usid, List<Property> properties) {
        this.usid = usid;
        this.label = Label.fromUsid(usid);
        this.properties = properties;
    }
}
