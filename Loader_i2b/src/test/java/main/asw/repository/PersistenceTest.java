   package main.asw.repository;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.asw.agents.Agent;

/**
 * Created by MIGUEL on 21/02/2017.
 */
public class PersistenceTest {

    private DBUpdate dbUpdate;
    private static MongoClient mongoClient;
    private static MongoDatabase db;
    private static MongoCollection<Document> coll;
    private List<Agent> agents;
    private long oldCount;
    private long newCount;

    @Before
    public void setUp(){
        dbUpdate = RepositoryFactory.getDBUpdate();
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("aswdb");
        coll = db.getCollection("agents");
        agents = new ArrayList<>();
    }

    @After
    public void tearDown(){
        for (int i = 0; i < agents.size(); i++){
            Document query = new Document("agentId", agents.get(i).getId());
            coll.deleteMany(query);
        }
    }

    /**
     * Tests the insertion of users in the DB by means of our persistence layer.
     * Checks that we cannot add two users with the same userId.
     */
    @Test
    public void testInsert(){
        insertUsers();
        //Only the non-repeated users should be in the database. We tried to insert one duplicated
        assertTrue(newCount == oldCount+agents.size()-1);

        Document query = new Document("agentId", agents.get(3).getId());
        assertEquals((coll.count(query)), 1);
    }

    /**
     * Adds users to the database by means of our persistence layer
     */
    private void insertUsers(){
        oldCount = coll.count();

        agents.add(new Agent(1,
        		"Miguel",
                "mg@email.com",
                "66863955B")
        );

        agents.add(new Agent(1,
        		"Jorge",
                "jl@email.com",
                "37165071S")
        );

        agents.add(new Agent(1,
        		"Nicolás",
                "np@email.com",
                "94875755L")
        );

        agents.add(new Agent(1,
        		"Pablo",
                "pg@email.com",
                "46402573G")
        );

        //Same userId
        agents.add(new Agent(1,
        		"Pablo",
                "pg@email.com",
                "46402573G")
        );

        dbUpdate.insert(agents);

        newCount = coll.count();
    }
}
