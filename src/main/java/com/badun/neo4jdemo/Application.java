package com.badun.neo4jdemo;

import asg.cliche.Command;
import asg.cliche.ShellFactory;
import com.badun.neo4jdemo.domain.Label;
import com.badun.neo4jdemo.service.Generator;
import com.badun.neo4jdemo.service.NodeService;
import com.badun.neo4jdemo.util.RandomUtil;
import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.graph.GrNode;
import iot.jcypher.query.JcQuery;
import iot.jcypher.query.JcQueryResult;
import iot.jcypher.query.api.IClause;
import iot.jcypher.query.factories.clause.CREATE;
import iot.jcypher.query.factories.clause.MATCH;
import iot.jcypher.query.factories.clause.RETURN;
import iot.jcypher.query.result.JcError;
import iot.jcypher.query.values.JcNode;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Artsiom Badun.
 */
public class Application {

    private IDBAccess dbAccess;
    private NodeService nodeService;
    private Generator generator;

    public static void main(String[] args) throws IOException {
        Application application = new Application();
        application.init();
        ShellFactory
                .createConsoleShell("neo4j-demo", "---=== Neo4j Demo Application ===---", application)
                .commandLoop();
    }

    public String init() {
        if (dbAccess == null) {
            Properties props = new Properties();
            props.setProperty(DBProperties.SERVER_ROOT_URI, "http://localhost:7474");
            props.setProperty(DBProperties.DATABASE_DIR, "/Users/badun/Documents/Neo4j/default.graphdb");
            dbAccess = DBAccessFactory.createDBAccess(DBType.REMOTE, props, "neo4j", "113024_01");
            nodeService = new NodeService(dbAccess);
            return "DB accessed.";
        } else {
            return "You already have access to DB.";
        }
    }

    @Command
    public String findJsonByUsid(String usid) {
        JcNode user = new JcNode("user");
        JcQuery query = new JcQuery();
        query.setClauses(new IClause[] {
                MATCH.node(user).property("usid").value(usid),
                RETURN.value(user)
        });
        JcQueryResult result = dbAccess.execute(query);
        handleDbError(result);
        return result.getJsonResult().toString();
    }

    @Command
    public String findByUsid(String usid) {
        JcNode user = new JcNode("user");
        user.label(Label.USER.val());
        JcQuery query = new JcQuery();
        query.setClauses(new IClause[] {
                MATCH.node(user).property("usid").value(usid),
                RETURN.value(user)
        });
        JcQueryResult result = dbAccess.execute(query);
        handleDbError(result);
        List<GrNode> userNode = result.resultOf(user);
        GrNode grNode = userNode.get(0);
        return (String) grNode.getProperty("usid").getValue();
    }

    @Command
    public String insertVideo() {
        return nodeService.insertVideo();
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
