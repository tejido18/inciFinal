package main.asw.repository;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.asw.agents.Agent;
import main.asw.report.ReportFactory;
import main.asw.report.ReportWriter;
import main.asw.repository.dao.AgentDao;

class DBUpdateImpl implements DBUpdate {

    private List<Agent> correctAgents;

    @Override
    public void insert(List<Agent> agents) {
        AgentDao agentDao = PersistenceFactory.getAgentDao();
        this.correctAgents = new ArrayList<>();
        for (Agent a: agents) {
            if(agentDao.saveAgent(a)) {
            	correctAgents.add(a);
            }
        }
    }

    @Override
    public void writeReport() {
        generateDirectories();
        ReportWriter textWriter = ReportFactory.createTxtWriter();
        ReportWriter docxWriter = ReportFactory.createDocxWriter();
        ReportWriter pdfWriter = ReportFactory.createPdfWriter();
        textWriter.writeReport(correctAgents);
        docxWriter.writeReport(correctAgents);
        pdfWriter.writeReport(correctAgents);
    }

    private void generateDirectories() {
        File dir = new File("Generated/GeneratedTxt");
        File dir2 = new File("Generated/GeneratedDocx");
        File dir3 = new File("Generated/GeneratedPdf");

        dir.mkdirs();
        dir2.mkdirs();
        dir3.mkdirs();
    }

}
