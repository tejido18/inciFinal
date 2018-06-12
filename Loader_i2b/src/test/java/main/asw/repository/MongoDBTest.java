package main.asw.repository;

import static junit.framework.TestCase.assertEquals;

import org.bson.BsonDocument;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.asw.agents.Agent;

/**
 * Created by MIGUEL on 16/02/2017.
 * <p>
 * This class runs tests against a temporal database (in-memory)
 */
public class MongoDBTest {

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;
//    private static final String IN_MEM_CONNECTION_URL = MONGO_HOST + ":" + MONGO_PORT;

//    private MongodExecutable mongodExe;
//    private MongodProcess mongod;
    private MongoClient mongoClient;

    /**
     * Deploys an in-memory database for simple testing
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        //MongodStarter runtime = MongodStarter.getDefaultInstance();
        //mongodExe = runtime.prepare(new MongodConfig(Version.V2_0_5, MONGO_PORT, Network.localhostIsIPv6()));
        //mongod = mongodExe.start();
        mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
    }

    /**
     * Shuts down the in-memory DB
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        /*if (mongod != null) {
            mongod.stop();
            mongodExe.stop();
        }*/
    }

    /**
     * Tests user insertion on DB
     */
    @Test
    public void testUserInsertion() {
        MongoDatabase db = mongoClient.getDatabase("testMongo");
        db.getCollection("agents").deleteMany(new BsonDocument());
        MongoCollection<Document> coll = db.getCollection("agents");
        Agent agent = new Agent(0,"Person","person@gmail.com","05936542N");
        Document doc = new Document("name", agent.getName())
        		.append("agentKind", agent.getAgentKind())
                .append("agentId", agent.getId())
        		.append("email", agent.getEmail())
        		.append("location", agent.getLocation())
                .append("password", agent.getPassword());
        coll.insertOne(doc);

        assertEquals(1, coll.count());
        assertEquals("Person", coll.find().first().get("name"));
        assertEquals("person@gmail.com", coll.find().first().get("email"));
        assertEquals("05936542N", coll.find().first().get("agentId"));
        assertEquals(null, coll.find().first().get("location"));
        assertEquals(doc.toJson(), coll.find().first().toJson());

        db.getCollection("agents").deleteMany(new BsonDocument());
    }

}
