package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Agent;
import domain.AgentLoginData;
import services.AgentsService;

/**
 * This class acts as a controller for the POST requests
 * to query the information of an agent.
 * 
 * Adapted by Alejandro on 14/02/2018 (Agents).
 *
 */
@RestController
public class AgentsDataController {

    private final AgentsService part;

    @Autowired
    AgentsDataController(AgentsService part){
        this.part = part;
    }

    @RequestMapping(value = "/agent", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Agent> userOkJSON(@RequestBody AgentLoginData info){
        AgentResponseAction act = new AgentResponseAction(part);
        return act.execute(info);
    }

}