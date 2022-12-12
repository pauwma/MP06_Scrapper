package writers;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import objetos.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase que gestiona la transformación de la información a CSV
 */
public interface WriterCSV {
    LaunchList launches_list = new LaunchList();
    static void launchToCSV(List<Launch> launches_list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter("out/launch.csv")) {
            ColumnPositionMappingStrategy mappingStrategy =  new ColumnPositionMappingStrategy();
             mappingStrategy.setType(Launch.class);

            String[] columns = { "launch_title", "launch_status", "launch_date", "rocket_name", "agency_name", "location_name" };
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"launch_title\",\"launch_status\",\"launch_date\",\"rocket_name\",\"agency_name\",\"location_name\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(launches_list);
        }
    }
    static void missionToCSV(List<Mission> mission_list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter("out/mission.csv")) {
            ColumnPositionMappingStrategy mappingStrategy =  new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Mission.class);

            String[] columns = { "rocket_name", "mission_name", "mission_type", "mission_launch_cost", "mission_description" };
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"rocket_name\",\"mission_name\",\"mission_type\",\"mission_launch_cost\",\"mission_description\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(mission_list);
        }
    }
    static void rocketToCSV(List<Rocket> rocket_list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter("out/rocket.csv")) {
            ColumnPositionMappingStrategy mappingStrategy =  new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Rocket.class);

            String[] columns = { "agency_name", "rocket_name", "rocket_family", "rocket_length", "rocket_diameter", "rocket_launch_mass", "rocket_low_earth_orbit_capacity", "rocket_description" };
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"agency_name\",\"rocket_name\",\"rocket_family\",\"rocket_length\",\"rocket_diameter\",\"rocket_launch_mass\",\"rocket_low_earth_orbit_capacity\",\"rocket_description\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(rocket_list);
        }
    }
    static void agencyToCSV(List<Agency> agency_list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter("out/agency.csv")) {
            ColumnPositionMappingStrategy mappingStrategy =  new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Agency.class);

            String[] columns = { "agency_name","agency_type","agency_abbreviation","agency_administration","agency_founded","agency_country","agency_spacecraft","agency_launchers","agency_description" };
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"agency_name\",\"agency_type\",\"agency_abbreviation\",\"agency_administration\",\"agency_founded\",\"agency_country\",\"agency_spacecraft\",\"agency_launchers\",\"agency_description\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(agency_list);
        }
    }
    static void locationToCSV(List<Location> locations_list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter("out/location.csv")) {
            ColumnPositionMappingStrategy mappingStrategy =  new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Location.class);

            String[] columns = { "launch_name", "location_name", "location_location", "rockets_launched"};
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"launch_name\",\"location_name\",\"location_location\",\"rockets_launched\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(locations_list);
        }
    }
}