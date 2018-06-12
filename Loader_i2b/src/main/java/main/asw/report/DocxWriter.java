package main.asw.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.asw.agents.Agent;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
class DocxWriter implements ReportWriter {

    private final static Logger log = LoggerFactory.getLogger(DocxWriter.class);

    @SuppressWarnings({ "resource", "unused" })
	@Override
    public void writeReport(List<Agent> agents) {
        FileOutputStream outputStream = null;
        for (Agent agent: agents) {
            try {
                outputStream = new FileOutputStream("Generated/GeneratedDocx/" + agent.getId() + ".docx");
                XWPFDocument document = new XWPFDocument();
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setAlignment(ParagraphAlignment.LEFT);
                addTitle(agent, paragraph);
                XWPFRun run2 = addText(agent, paragraph);
                document.write(outputStream);
                log.info("Exported user with userId = " + agent.getId() + " correctly to DOCX format");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * Auxiliar method that creates the head of the document.
     *
     * @param user      the user whose report we're creating.
     * @param paragraph the text of the head of the document.
     */
    private void addTitle(Agent agent, XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        //    run.setBold(true);
        run.setFontSize(14);
        addLine(run, "Greetings: " + agent.getName() + ".");
    }

    /**
     * Auxiliar method that creates the body of the document.
     *
     * @param user      the user whose report we're creating.
     * @param paragraph the text of the body of the document.
     */
    private XWPFRun addText(Agent agent, XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setBold(false);
        run.setFontSize(12);
        addLine(run, "Greetings: " + agent.getName() + ".");
        addLine(run, "This is your personal information that we have received: ");
        addLine(run, "Type of Agent: " + agent.getAgentKind()+ ".");
        addLine(run, "Email: " + agent.getEmail() + ".");
        addLine(run, "Location: "+agent.getLocation());
        run.addBreak();
        addLine(run, "Your user name is your id: " + agent.getId() + ".");
        addLine(run, "Your password is: " + agent.getPasswordUnencripted());
        return run;
    }

    /**
     * Auxiliar method that Adds a line of text and a line jump.
     *
     * @param run  the XWPF run.
     * @param line the text we want to in the line.
     */
    private void addLine(XWPFRun run, String line) {
        run.setText(line);
        run.addBreak();
    }
}
