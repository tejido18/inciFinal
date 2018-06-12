package main.asw.repository;

import java.util.List;

import main.asw.agents.Agent;

public interface DBUpdate {

    /**
     * Inserts each one of the given agents into the database
     * @param agents
     */
    void insert(List<Agent> agents);

    /**
     * Generates the reports for the agents
     */
    void writeReport();

}
