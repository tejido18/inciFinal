package main.asw.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import main.asw.agents.Agent;


/**
 * @author Pineirin
 * @since 14/02/2017.
 */
class MyPdfWriter implements ReportWriter {

    private final static Logger log = LoggerFactory.getLogger(DocxWriter.class);

    @Override
    public void writeReport(List<Agent> agents) {
        Document document = null;
        FileOutputStream fileOutputStream = null;
        for (Agent agent: agents) {
            try {
                fileOutputStream = new FileOutputStream("Generated/GeneratedPdf/" + agent.getId() + ".pdf");
                document = new Document();
                PdfWriter.getInstance(document, fileOutputStream);
                document.open();
                addText(agent, document);
                log.info("Exported user with userId = " + agent.getId()+ " correctly to PDF format");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                if (document != null) {
                    document.close();
                }
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * Auxiliar method that adds all the text to the pdf.
     *
     * @param agent     the agent whose information we want to print.
     * @param document the document that we want to generate.
     * @throws DocumentException throws a exception you aren't able to write in the document.
     */
    private void addText(Agent agent, Document document) throws DocumentException {
        document.add(new Paragraph("Greetings: " + agent.getName() + "."));
        document.add(new Paragraph("This is your personal information that we have received: "));
        document.add(new Paragraph("Type of Agent: " + agent.getAgentKind()+ "."));
        document.add(new Paragraph("Email: " + agent.getEmail() + "."));
        document.add(new Paragraph("Location: "+agent.getLocation()));
        document.add(new Paragraph());
        document.add(new Paragraph("Your user name is your id: " + agent.getId() + "."));
        document.add(new Paragraph("Your password is: " + agent.getPasswordUnencripted()));
    }
}
