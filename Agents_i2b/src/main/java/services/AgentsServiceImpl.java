package services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Agent;
import repositories.Database;
import repositories.MasterFileParser;
import util.JasyptEncryptor;

/**
 * Created by Nicolás on 14/02/2017.
 * Adapted by Alejandro on 14/02/2018 (Agents).
 */
@Service
public class AgentsServiceImpl implements AgentsService {

    private final Database dat;
    private final JasyptEncryptor encryptor = new JasyptEncryptor();
    private final MasterFileParser parser;

    @Autowired
    AgentsServiceImpl(Database dat, MasterFileParser parser) {
        this.dat = dat;
        this.parser = parser;
    }

    @Override
    public Agent getAgent(String username, String password, String kind) {
        Agent user = dat.getAgent(username);
        if(user != null && encryptor.checkPassword(password, user.getPassword())
        		&& kind.equals(user.getKind()))
        			return user;
        else return null;
    }

    @Override
    public void updateInfo(Agent agent, String newPassword) {
	    	//It is not necessary, done by the domain class itself.
	    	agent.setPassword(newPassword);
	    	dat.updateInfo(agent);
    }

	@Override
	public List<String> getAgentKindNames() throws IOException {
		return parser.getKindNames();
	}
}
