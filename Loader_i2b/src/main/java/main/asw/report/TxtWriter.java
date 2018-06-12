package main.asw.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.asw.agents.Agent;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
class TxtWriter implements ReportWriter {

	private final static Logger log = LoggerFactory.getLogger(TxtWriter.class);

	@Override
    public void writeReport(List<Agent> agents) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        StringBuilder strb = null;
        for (Agent agent: agents)
            try {
                fileWriter = new FileWriter("Generated/GeneratedTxt/" + agent.getId() + ".txt");
                strb = new StringBuilder();
                bufferedWriter = new BufferedWriter(fileWriter);
                strb.append("Greetings: " + agent.getName()+ ".\n");
                strb.append("This is your personal information that we have received: \n");
                strb.append("Type of Agent: " + agent.getAgentKind()+ ".");
                strb.append("Email: " + agent.getEmail() + ".");
                strb.append("Location: "+agent.getLocation());
                strb.append("\n");
                strb.append("Your user name is your id: " + agent.getId() + ".");
                strb.append("Your password is: " + agent.getPasswordUnencripted());
                bufferedWriter.write(strb.toString());
                
                log.info("Exported user with userId = " + agent.getId() + " correctly to TXT format");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
    }
}
