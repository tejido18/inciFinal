package repositories;

import domain.Agent;

/**
 * Created by Nicolás on 14/02/2017.
 * Adapted by Alejandro on 14/02/2018 (Agents).
 * 
 * Interface service for the database. Current implementation uses Spring Boot Data MongoDB API
 * <a href="https://spring.io/guides/gs/accessing-data-mongodb/">link here</a>
 */
public interface Database {

    void updateInfo(Agent agent);

    Agent getAgent(String username);

}
