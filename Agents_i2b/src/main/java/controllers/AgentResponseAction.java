package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import domain.Agent;
import domain.AgentLoginData;
import services.AgentsService;

/**
 * Created by Nicolás on 17/02/2017.
 * Adapted by Alejandro on 14/02/2018 (Agents).
 * Class that handles the login data response. Access the service layer and recovers the agent.
 */
public class AgentResponseAction {
    private final AgentsService agentsService;

    AgentResponseAction(AgentsService part){
        this.agentsService = part;
    }

    public ResponseEntity<Agent> execute(AgentLoginData info){
        Agent agent = agentsService.getAgent(info.getLogin(), info.getPassword(), info.getKind());
        return agent == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) 
        					    : new ResponseEntity<>(agent, HttpStatus.OK);
    }
}
