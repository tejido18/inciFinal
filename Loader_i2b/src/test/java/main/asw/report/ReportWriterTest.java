package main.asw.report;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import main.asw.agents.Agent;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
public class ReportWriterTest {

    private final static Logger log = LoggerFactory.getLogger(ReportWriterTest.class);

    @Test
    public void testTxtWriter() {

        File dir = new File("Generated/GeneratedTxt");
        dir.mkdirs();

        List<Agent> users = new ArrayList<>();
        users.add(new Agent(1, "Pablo", "pablo@gmail.com", "53520961F"));

        ReportWriter textWriter = ReportFactory.createTxtWriter();
        textWriter.writeReport(users);

        File file = new File("Generated/GeneratedTxt/53520961F.txt");

        assertEquals(true, file.exists());

        String[] lines = readerTxt(file);
        assertTrue(lines[0].contains("Greetings: Pablo."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[3].contains("Your password is: "));

        assertEquals(true, file.delete());

        dir.delete();
    }


    @Test
    public void testDocxWriter() {

        File dir = new File("Generated/GeneratedDocx");
        dir.mkdirs();

        List<Agent> users = new ArrayList<>();
        users.add(new Agent(1, "Pablo", "pablo@gmail.com", "53520961F"));

        ReportWriter docxWriter = ReportFactory.createDocxWriter();
        docxWriter.writeReport(users);

        File file = new File("Generated/GeneratedDocx/53520961F.docx");

        assertEquals(true, file.exists());

        String[] lines = readerDocx(file);
        assertTrue(lines[1].contains("Greetings: Pablo."));
        assertTrue(lines[2].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));

        assertEquals(true, file.delete());

        dir.delete();
    }


    @Test
    public void testPdfWriter() {

        File dir = new File("Generated/GeneratedPdf");
        dir.mkdirs();

        List<Agent> users = new ArrayList<>();
        users.add(new Agent(1, "Pablo", "pablo@gmail.com", "53520961F"));

        ReportWriter pdfWriter = ReportFactory.createPdfWriter();
        pdfWriter.writeReport(users);

        File file = new File("Generated/GeneratedPdf/53520961F.pdf");

        assertEquals(true, file.exists());

        String filename1 = "Generated/GeneratedPdf/53520961F.pdf";

        String[] lines = readerPdf(filename1);
        assertTrue(lines[0].contains("Greetings: Pablo."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[6].contains("Your password is: "));

        assertEquals(true, file.delete());

        dir.delete();
    }

    @Test
    public void testReportWriter() {

        File dir = new File("Generated/GeneratedTxt");
        dir.mkdirs();
        File dir2 = new File("Generated/GeneratedDocx");
        dir2.mkdirs();
        File dir3 = new File("Generated/GeneratedPdf");
        dir3.mkdirs();

        List<Agent> users = new ArrayList<>();

        users.add(new Agent(1, "Pablo", "pablo@gmail.com", "53520961F"));

        ReportWriter textWriter = ReportFactory.createTxtWriter();
        ReportWriter docxWriter = ReportFactory.createDocxWriter();
        ReportWriter pdfWriter = ReportFactory.createPdfWriter();
        textWriter.writeReport(users);
        docxWriter.writeReport(users);
        pdfWriter.writeReport(users);

        File file = new File("Generated/GeneratedTxt/53520961F.txt");

        assertEquals(true, file.exists());

        String[] lines = readerTxt(file);
        assertTrue(lines[0].contains("Greetings: Pablo."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[3].contains("Your password is: "));


        lines = readerPdf("Generated/GeneratedPdf/53520961F.pdf");
        assertTrue(lines[0].contains("Greetings: Pablo."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[6].contains("Your password is: "));


        assertEquals(true, file.delete());

        new File("Generated/GeneratedTxt").delete();
        new File("Generated/GeneratedDocx").delete();
        new File("Generated/GeneratedPdf").delete();
        new File("Generated").delete();
    }


    private String[] readerTxt(File file) {
        String[] lines = new String[9];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();

            int i = 0;
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
                lines[i] = sb.toString();
                i++;
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if( bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return lines;
    }

    private String[] readerDocx(File file) {
        String[] lines = new String[9];
        FileInputStream fileInputStream = null;
        XWPFDocument document = null;
        try {
            fileInputStream = new FileInputStream(file);
            document = new XWPFDocument(fileInputStream);

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
               lines = paragraph.getText().split("\n");
            }
            
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }finally {
            try {
            	document.close();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return lines;
    }

    private String[] readerPdf(String filename1) {
        PdfReader reader = null;
        String[] lines = new String[9];

        try {

            reader = new PdfReader(filename1);

            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            lines = textFromPage.split("\n");

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }finally {
            if( reader != null)
                reader.close();
        }

        return lines;
    }

}