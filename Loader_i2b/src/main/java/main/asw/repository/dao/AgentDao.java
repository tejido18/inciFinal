package main.asw.repository.dao;

import main.asw.agents.Agent;

/**
 * 
 * @author Sergio Faya Fernandez
 *
 */
public interface AgentDao {

	/**
	 * Saves a given agent in the database
	 *
	 * @param agent Agent to be saved
	 * @return true if the agent could be saved false otherwise (if the agent already
	 *         exists in the DB)
	 */
	boolean saveAgent(Agent agent);

	void setMongoHost(String arg);
}
