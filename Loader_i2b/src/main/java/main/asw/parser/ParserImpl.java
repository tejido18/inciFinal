package main.asw.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import main.asw.agents.Agent;
import main.asw.location.LatLng;
import main.asw.repository.DBUpdate;
import main.asw.repository.RepositoryFactory;

/**
 * Created by nicolas on 3/02/17 for citizensLoader0.
 */
class ParserImpl implements Parser {

	private final static org.slf4j.Logger log = LoggerFactory.getLogger(Parser.class);

	private CellLikeDataContainer dataSource;
	private List<Agent> agents;
	private String csvdoc;

	ParserImpl(String filename) throws IOException {
		this.dataSource = new ApachePoiDataContainer(filename);
	}

	ParserImpl(String filename, String csv) throws IOException {
		this(filename);
		this.csvdoc = csv;
	}
	
	public void setCsv(String csvDoc){
		String base = "src/test/resources/";
		this.csvdoc = base + csvDoc;
	}

	@Override
	public void readList() {
		try {
			loadData();
		} catch (IOException e) {
			log.error("Error handling the file");
		}
	}

	@Override
	public void insert() {
		DBUpdate dbupdate = RepositoryFactory.getDBUpdate();
		dbupdate.insert(agents);
		dbupdate.writeReport();
	}

	private void loadData() throws IOException {
		List<Agent> agentsAux = new ArrayList<>();

		while (dataSource.nextRow()) {
			if (dataSource.getCellIntegerValue(0) == 1 || dataSource.getCellIntegerValue(0) == 2) {
				if (dataSource.getNumberOfColumns() == 5 || dataSource.getNumberOfColumns() == 4) {
					try {
						agentsAux.add(rowToAgent());
					} catch (ParseException | IllegalArgumentException e) {
						// Thrown by the Date Parser
						log.error("ParseError: Error reading line " + dataSource.toString() + " " + e.getMessage(),
								dataSource.getCurrentRow());
					}
				} else {
					log.error("ParseError: Error reading line " + dataSource.toString()
							+ " the number of columns is different than expected", dataSource.getCurrentRow());
				}
			} else if (dataSource.getCellIntegerValue(0) == 3) {
				if (dataSource.getNumberOfColumns() == 4) {
					try {
						agentsAux.add(rowToAgent());
					} catch (ParseException | IllegalArgumentException e) {
						// Thrown by the Date Parser
						log.error("ParseError: Error reading line " + dataSource.toString() + " " + e.getMessage(),
								dataSource.getCurrentRow());
					}
				} else {
					log.error("ParseError: Error reading line " + dataSource.toString()
							+ " the number of columns is different than expected", dataSource.getCurrentRow());
				}
			}
		}
		this.agents = agentsAux;

	}

	/**
	 * Convierte la fila del documento a un objeto Agent
	 * 
	 * 
	 * @return objeto agent
	 * @throws ParseException
	 */
	private Agent rowToAgent() throws ParseException {
		int kind = dataSource.getCellIntegerValue(0);
		if(!isAgentTypeCorrect(kind)){
			throw new ParseException("Kind of agent is not in the csvDoc", kind);
		}
		String name = dataSource.getCell(1);
		String email = dataSource.getCell(2);
		String id = dataSource.getCell(3);
		if (dataSource.getNumberOfColumns() == 5) {
			String[] locationstr = dataSource.getCell(4).split(",");// convert to LatLng
			LatLng location = parseLocation(locationstr);
			// if (isAgentTypeCorrect(kind)) { // is optional
			return new Agent(kind, name, email, id, location);
			// }
		} //else if (isAgentTypeCorrect(kind)) { // is optional
			return new Agent(kind, name, email, id);
		//}
		
	}

	/**
	 * This method parses the CSV file in order to make sure that the type of agent
	 * is allowed
	 * 
	 * @param kind
	 * @return true if it exists
	 */
	private boolean isAgentTypeCorrect(int kind) {
		try (BufferedReader br = new BufferedReader(new FileReader(csvdoc))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] agentType = line.split(",");
				if (kind == Integer.valueOf(agentType[0])) {
					return true;
				} else
					continue;
			}
			br.close();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author PBalbuena
	 * @param locationstr
	 * @return
	 */
	private LatLng parseLocation(String[] locationstr) {
		LatLng location = new LatLng(Double.parseDouble(locationstr[0]), Double.parseDouble(locationstr[1]));
		return location;
	}

	public List<Agent> getAgents() {
		return agents;
	}

}
