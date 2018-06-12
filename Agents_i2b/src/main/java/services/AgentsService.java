package services;

import java.io.IOException;
import java.util.List;

import domain.Agent;

/**
 * Created by Nicolás on 14/02/2017.
 * Adapted by Alejandro on 14/02/2018 (Agents).
 * 
 * Façade for the business layer implementation
 */

public interface AgentsService {

    /**
     * Given the data of an agent, checks if there's such an agent, and if the password matches
     * @param username The login user name for the agent
     * @param password The password given on the credentials
     * @param kind Kind name of the agent.
     * @return The proper agent if the agent exists and the password and kind match. Null otherwise.
     */
    public Agent getAgent(String username, String password, String kind);

    /**
     * Updates the password for the given agent.
     * @param agent The given agent
     * @param newPassword The new password
     */
    public void updateInfo(Agent agent, String newPassword);

    /**
     * Returns the list of available agent kind names.
     * @return List Available agent kind names given the current master file.
     * @throws IOException 
     */
    public List<String> getAgentKindNames() throws IOException;
}
