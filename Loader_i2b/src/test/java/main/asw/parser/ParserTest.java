package main.asw.parser;

import static junit.framework.TestCase.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import main.asw.agents.Agent;

/**
 * Created by nicolas on 15/02/17.
 */
public class ParserTest {

    private final static String BASE_PATH = "src/test/resources/";

    //pruebas xls
    private final static String TEST_OK_FILE_NAME = "pruebaAgentes.xls";
    private final static String TEST_NO_FOUND_FILE = "noExiste";
    private static final String TEST_LESS_LINES = "lessLines.xls";
    private static final String TEST_MORE_LINES = "moreLines.xls";
    
    //pruebas csv
    private static final String TEST_CSV_NAME = "pruebaTipos.csv";
	private static final String TEST_CSV_WRONGKINDS = "pruebaTiposMal.csv";

    private ParserImpl parser;

    //DONT KNOW WHY ITS COMMENTED SO I LEAVE IT
//    private static final String MONGO_HOST = "localhost";
//    private static final int MONGO_PORT = 27017;
//
//    private MongodExecutable mongodExe;
//    private MongodProcess mongod;
//    private MongoClient mongoClient;
//
//    /**
//     * Deploys an in-memory database for simple testing
//     *
//     * @throws Exception if any problem occurs trying to launch the DB
//     */
//    private void setupDb() throws Exception {
//        MongodStarter runtime = MongodStarter.getDefaultInstance();
//        mongodExe = runtime.prepare(new MongodConfig(Version.V2_0_5, MONGO_PORT, Network.localhostIsIPv6()));
//        mongod = mongodExe.start();
//        mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
//    }
//
//    @After
//    public void tearDownDb() throws Exception {
//        if (mongod != null) {
//            mongod.stop();
//            mongodExe.stop();
//        }
//    }

    @Test
    public void testAgentTypeInCorrect() throws IOException{
    	parser = new ParserImpl(BASE_PATH + TEST_OK_FILE_NAME, TEST_CSV_WRONGKINDS);
    	parser.readList();
    	assertEquals(6, parser.getAgents().size());
    }
    
    @Test
    public void testAgentTypeInCorrectAll() throws IOException{
    	parser = new ParserImpl(BASE_PATH + TEST_MORE_LINES, TEST_CSV_WRONGKINDS);
    	parser.readList();
    	assertEquals(0, parser.getAgents().size());
    }


    @Test(expected = IOException.class)
    public void testNoFoundFile() throws IOException {
        parser = ParserFactory.getParser(BASE_PATH + TEST_NO_FOUND_FILE);
    }

    @Test
    public void testMoreLines() throws IOException, ParseException {
        parser = ParserFactory.getParser(BASE_PATH + TEST_MORE_LINES, TEST_CSV_NAME);
        parser.readList();
        assertEquals(0, parser.getAgents().size());
    }

    @Test
    public void testLessLines() throws IOException, ParseException {
        parser = ParserFactory.getParser(BASE_PATH + TEST_LESS_LINES);
        parser.readList();
        assertEquals(0, parser.getAgents().size());
    }
    
    @Test
    public void testWithCsvAllOk() throws IOException, ParseException {
         parser = ParserFactory.getParser(BASE_PATH + TEST_OK_FILE_NAME, TEST_CSV_NAME);

         String baseName = "Agent";
         String baseEmail = "prueba";
         parser.readList();
         assertEquals(18, parser.getAgents().size());
         for (int i = 0; i < parser.getAgents().size(); i++) {
             String index = (i + 1 < 10) ? "0" + (i + 1) : (i + 1) + "";
             Agent user = parser.getAgents().get(i);
             assertEquals(baseName + index, user.getName());
             assertEquals(baseEmail + index + "@prueba.es", user.getEmail());
         }
    }
}
