package main.asw.repository;

import main.asw.repository.dao.AgentDao;
import main.asw.repository.dao.AgentDaoImpl;

/**
 * Created by Sergio Faya Fernandez
 */
public class PersistenceFactory {

    private static AgentDao agentDao = new AgentDaoImpl();

    public static AgentDao getAgentDao() {
        return agentDao;
    }

}
