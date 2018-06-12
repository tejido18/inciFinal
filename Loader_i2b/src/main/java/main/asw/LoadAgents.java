package main.asw;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.asw.parser.Parser;
import main.asw.parser.ParserFactory;
import main.asw.repository.PersistenceFactory;

/**
 * Refactored by Sergio Faya Fernandez (changed user by agents)
 *
 * @author Labra
 * @author MIGUEL
 */

public class LoadAgents {
	
	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(LoadAgents.class);
	
	public static void main(String... args) {
		//csv not provided
        if (args.length == 2) {
            try {
                PersistenceFactory.getAgentDao().setMongoHost(args[1]);
                Parser parser = ParserFactory.getParser(args[0]);
                System.out.println("Archivos aceptados (xls), leyendo...");
                parser.readList();
                parser.insert();
                System.out.println("Operaciones finalizadas");
            } catch (IOException e) {
                printUsage();
            }
        } else {
            printUsage();
        }
        //csv with kinds provided
        if (args.length == 3) { 
            try {
            	String csv = args[2];
                PersistenceFactory.getAgentDao().setMongoHost(args[1]);
                Parser parser = ParserFactory.getParser(args[0], csv);
                System.out.println("Archivos aceptados (xls y csv), leyendo...");
                parser.readList();
                parser.insert();
                System.out.println("Operaciones finalizadas");
            } catch (IOException e) {
                printUsage();
            }
        } else {
            printUsage();
            throw new IllegalArgumentException();
        }
    }

    private static void printUsage() {
        System.out.println(
                "Invalid parameters. You must only have:\n" +
                "\t <xls path> \t <mongo host>"
        );
    }
}
