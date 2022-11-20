package writers;

import objetos.Launch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriterCSV {
    public void launchToCSV(List<Launch> launches_list, String CSV_Header) throws IOException {
        PrintWriter writer = new PrintWriter("out/launch.csv");
        writer.println(CSV_Header);

        for (Launch launch : launches_list) {
            writer.println(launch.toString());
        }

        writer.close();
    }
}
