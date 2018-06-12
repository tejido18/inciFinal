package main.asw.repository.daoTest;

import org.bson.BsonDocument;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.asw.agents.Agent;
import main.asw.repository.PersistenceFactory;
import main.asw.repository.dao.AgentDao;

public class DaoTest {

	private Agent ag1;
	private Agent ag2;
	private AgentDao dao;
	
	private MongoClient mongoClient = new MongoClient("localhost", 27017);
    private MongoDatabase db = mongoClient.getDatabase("aswdb");
    private MongoCollection<Document> coll = db.getCollection("agents");

	@Before
	public void setUp() {
		ag1 = new Agent(1, "Agent", "agent@gmail.com", "05936542N");
		ag2 = new Agent(1, "Agent2", "agent2@gmail.com", "05936542N");
	}
	
	@Test
	public void testConstructor() {
		dao = PersistenceFactory.getAgentDao();
	}

	@Test
	public void testSaveAgentOk() {
		dao = PersistenceFactory.getAgentDao();
		boolean result = dao.saveAgent(ag1);
		Assert.assertTrue(result);
		
		db.getCollection("agents").deleteMany(new BsonDocument());
	}

	@Test
	public void testSaveAgentNotOk() {
		dao = PersistenceFactory.getAgentDao();
		dao.saveAgent(ag1);
		boolean result = dao.saveAgent(ag2);
		Assert.assertFalse(result);

		db.getCollection("agents").deleteMany(new BsonDocument());
	}
}
