package services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Agent;
import repositories.AgentsRepository;
import repositories.Database;

@Service
public class InsertSampleDataService {
	
	@Autowired
	Database database;
	
	@Autowired
	AgentsRepository agentsRepository;
	
	@PostConstruct
	public void init() {
		agentsRepository.deleteAll();
		
		Agent agent1 = new Agent("Paco", "pacoo11@gmail.com", "123456", "pacoo", "Person");
		Agent agent2 = new Agent("Carmen", "carmeen2@gmail.com", "123456", "carmeen", "Entity");
		Agent agent3 = new Agent("Son", "david_son1@naver.co.kr", "pass123", "sonny", "Person");
		
		database.updateInfo(agent1);
		database.updateInfo(agent2);
		database.updateInfo(agent3);
	}

}
