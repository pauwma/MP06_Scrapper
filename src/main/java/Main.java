import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Clase Main
 *
 * @author  pauwma
 */

public class Main {
  public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
    Scrapper scrapper = new Scrapper();
    scrapper.getPosts();
  }
}