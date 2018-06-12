package repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Agent;

@Repository
public class MongoDatabase implements Database{
	
	@Autowired
	private AgentsRepository users;

    @Override
	public void updateInfo(Agent user) {
		users.save(user);
	}

	@Override
	public Agent getAgent(String username) {
		return users.findByUsername(username);
	}

}
