package com.badun.neo4jdemo.service;

import com.badun.neo4jdemo.domain.Label;
import com.badun.neo4jdemo.util.RandomUtil;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.query.JcQuery;
import iot.jcypher.query.JcQueryResult;
import iot.jcypher.query.api.IClause;
import iot.jcypher.query.factories.clause.CREATE;
import iot.jcypher.query.factories.clause.CREATE_INDEX;
import iot.jcypher.query.result.JcError;
import iot.jcypher.query.values.JcNode;

/**
 * Created by Artsiom Badun.
 */
public class NodeService {
    public static final String USID = "usid";

    private final IDBAccess dbAccess;

    public NodeService(IDBAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    public String insertVideo() {
        String videoUsid = RandomUtil.getRandomVideoUsid();
        JcNode video = new JcNode("video");
        JcQuery query = new JcQuery();
        query.setClauses(new IClause[]{
                CREATE.node(video).label(Label.VIDEO.val()).property(USID).value(videoUsid)
        });
        execute(query);
        return videoUsid;
    }

    public String createIndexes(Label label, String property) {
        JcQuery query = new JcQuery();
        query.setClauses(new IClause[]{
                CREATE_INDEX.onLabel(label.val()).forProperty(property)
        });
        execute(query);
        return "OK";
    }


    // ------------- INTERNAL -------------

    private JcQueryResult execute(JcQuery query) {
        JcQueryResult result = dbAccess.execute(query);
        handleDbError(result);
        return result;
    }

    private void handleDbError(JcQueryResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(
                    result.getDBErrors().stream()
                            .map(JcError::getMessage)
                            .reduce("", (msg1, msg2) -> msg1 + " " + msg2));
        }
    }
}
