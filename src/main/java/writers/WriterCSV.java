package writers;

import objetos.Launch;
import objetos.Location;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface  WriterCSV {
    static void launchToCSV(List<Launch> launches_list) throws IOException {
        PrintWriter writer = new PrintWriter("out/launch.csv");
        writer.println("\"launch_name\",\"launch_status\",\"launch_date\",\"launch_time\",\"rocket_name\",\"agency_name\",\"location_name\"");
        for (Launch launch : launches_list) {
            writer.println(launch.toString());
        }
        writer.close();
    }

    static void locationToCSV(List<Location> locations_list) throws IOException {
        PrintWriter writer = new PrintWriter("out/location.csv");
        writer.println("\"launch_name\",\"location_name\",\"location_location\",\"rockets_launched\"");
        for (Location location : locations_list) {
            writer.println(location.toString());
        }
        writer.close();
    }
}
