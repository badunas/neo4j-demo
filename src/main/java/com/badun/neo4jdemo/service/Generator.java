package com.badun.neo4jdemo.service;

import com.badun.neo4jdemo.domain.Label;
import iot.jcypher.query.factories.clause.CREATE_INDEX;
import iot.jcypher.query.factories.clause.USING;
import iot.jcypher.query.values.JcNode;

/**
 * Created by Artsiom Badun.
 */
public class Generator {

    /**
     * 0) Create index for video usid;
     * 0) Create 100000 videos;
     * 1) Create user;
     * 2) Create post for this user;
     * 3) Make him friend of previous 10 random users, and they randomly like or read his post;
     * 4) After 50 users create a video;
     * 5) Every user will like random 10 films and saw 20 videos;
     */

    private final NodeService nodeService;

    public Generator(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void generate() {
        createIndexes();
    }

    private void createIndexes() {

    }
}
