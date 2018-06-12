package main.asw.report;

import java.util.List;

import main.asw.agents.Agent;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
public interface ReportWriter {

    /**
     * Writes a reports in txt word and pdf of the different agents.
     *
     * @param agents whose information we need to report.
     */
    void writeReport(List<Agent> agents);
}
