package writers;


import objetos.*;

import java.io.File;
import java.util.List;
import jakarta.xml.bind.*;

/**
 * Clase que gestiona la transformación de la información a XML
 */
public class WriterXML {
    AgencyList agencyList = new AgencyList();
    LaunchList launchList = new LaunchList();
    LocationList locationList = new LocationList();
    MissionList missionList = new MissionList();
    RocketList rocketList = new RocketList();
    Agency agencyData;
    Launch launchData;
    Location locationData;
    Mission missionData;
    Rocket rocketData;
    JAXBContext jaxbContext;
    Marshaller jaxbMarshaller;

    public WriterXML (List<Agency> agencyList, List<Launch> launchList, List<Location> locationList, List<Mission> missionList, List<Rocket> rocketList) {
        try {
            jaxbContext = JAXBContext.newInstance(Agency.class);

            for (Agency agency : agencyList) {
                agencyData = new Agency(agency.getAgency_name(),agency.getAgency_type(),agency.getAgency_abbreviation(),agency.getAgency_administration(),agency.getAgency_founded(),agency.getAgency_launchers(),agency.getAgency_spacecraft(),agency.getAgency_country(),agency.getAgency_description());
                agencyList.add(agencyData);
            }

            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(agencyList, new File("out/agency.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            jaxbContext = JAXBContext.newInstance(Launch.class);

            for (Launch launch : launchList) {
                launchData = new Launch(launch.getLaunch_title(),launch.getLaunch_status(),launch.getLaunch_date(),launch.getRocket_name(),launch.getAgency_name(),launch.getLocation_name());
                launchList.add(launchData);
            }

            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(launchList, new File("out/launch.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            jaxbContext = JAXBContext.newInstance(Location.class);

            for (Location location : locationList) {
                locationData = new Location(location.getLaunch_name(),location.getLocation_name(),location.getLocation_location(),location.getRockets_launched());
                locationList.add(locationData);
            }

            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(locationList, new File("out/location.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            jaxbContext = JAXBContext.newInstance(Mission.class);

            for (Mission mission : missionList) {
                missionData = new Mission(mission.getRocket_name(),mission.getMission_name(), mission.getMission_type(), mission.getMission_launch_cost(),mission.getMission_description());
                missionList.add(missionData);
            }

            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(missionList, new File("out/mission.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            jaxbContext = JAXBContext.newInstance(Rocket.class);

            for (Rocket rocket : rocketList) {
                rocketData = new Rocket(rocket.getAgency_name(),rocket.getRocket_name(),rocket.getRocket_family(),rocket.getRocket_length(),rocket.getRocket_diameter(),rocket.getRocket_launch_mass(),rocket.getRocket_low_earth_orbit_capacity(),rocket.getRocket_description());
                rocketList.add(rocketData);
            }

            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(missionList, new File("out/rocket.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    /*
    static void launchToXMLJAXB(LaunchList launches_list) throws JAXBException {
        try {
            // Creamos un fichero XML donde se guardará la salida del marshalling
            File file = new File("launches.xml");

            // Creamos un JAXBContext para la clase LaunchList
            JAXBContext jaxbContext = JAXBContext.newInstance(LaunchList.class);

            // Creamos un Marshaller utilizando el JAXBContext
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Indentamos el contenido del fichero XML para que sea más legible
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshalling (conversión de la lista de objetos Launch en un fichero XML)
            jaxbMarshaller.marshal(launches_list, file);

            // Mostramos un mensaje de éxito
            System.out.println("La lista de objetos Launch se ha marshalizado en el fichero launches.xml");
        } catch (JAXBException e) {
            // Mostramos un mensaje de error en caso de que se produzca una excepción
            System.out.println("Error al marshalizar la lista de objetos Launch: " + e.getMessage());
        }
    }

    public static void launchJAXBTest(List<Launch> launches_list) {

        LaunchList launchList = new LaunchList();
        try {
            JAXBContext jaxbContext = JAXBContext. newInstance(LaunchList.class);
            Launch tmpLaunch;

            for (Launch launch : launches_list) {
                tmpLaunch = new Launch(launch.getLaunch_title(), launch.getLaunch_status(), launch.getLaunch_date(), launch.getRocket_name(), launch.getAgency_name(), launch.getLocation_name());
                launchList.add(tmpLaunch);
            }

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(launches_list, new File("out/launch.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    } */
}

