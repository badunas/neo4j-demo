package com.badun.neo4jdemo.domain;

import java.util.List;

/**
 * Created by Artsiom Badun.
 */
public class Relation {
    public final Type type;
    public final List<Property> properties;

    public Relation(Type type, List<Property> properties) {
        this.type = type;
        this.properties = properties;
    }

    public enum Type {
        LIKE,
        FRIEND,
        CREATED,
        SAW;
    }
}
