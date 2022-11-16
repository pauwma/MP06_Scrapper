import java.io.IOException;

/**
 * Clase Main
 *
 * @author  pauwma
 */

public class Main {
  public static void main(String[] args) throws IOException {
    Scrapper scrapper = new Scrapper();
    scrapper.getPosts();
  }
}