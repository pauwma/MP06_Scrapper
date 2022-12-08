package writers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import objetos.Launch;
import objetos.LaunchList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.List;

public interface WriterXML {

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

    static void launchToXML(List<Launch> launches_list) {
        try {
            // Creamos un fichero XML donde se guardará la salida del marshalling
            File file = new File("out/launches.xml");

            // Creamos un DocumentBuilderFactory y un DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Creamos un documento XML vacío
            Document doc = dBuilder.newDocument();

            // Creamos un elemento "launches" que será el elemento raíz del documento XML
            Element launchesElement = doc.createElement("launches");
            doc.appendChild(launchesElement);

            // Para cada objeto Launch en la lista, creamos un elemento "launch" en el documento XML
            for (Launch launch : launches_list) {
                Element launchElement = doc.createElement("launch");

                // Creamos un elemento "launch_title" dentro del elemento "launch"
                Element launchTitleElement = doc.createElement("launch_title");
                launchTitleElement.setTextContent(launch.getLaunch_title());
                launchElement.appendChild(launchTitleElement);
                // Creamos un elemento "launch_status" dentro del elemento "launch"
                Element launchStatusElement = doc.createElement("launch_status");
                launchStatusElement.setTextContent(launch.getLaunch_status());
                launchElement.appendChild(launchStatusElement);

                // Creamos un elemento "launch_date" dentro del elemento "launch"
                Element launchDateElement = doc.createElement("launch_date");
                launchDateElement.setTextContent(launch.getLaunch_date());
                launchElement.appendChild(launchDateElement);

                // Creamos un elemento "rocket_name" dentro del elemento "launch"
                Element rocketNameElement = doc.createElement("rocket_name");
                rocketNameElement.setTextContent(launch.getRocket_name());
                launchElement.appendChild(rocketNameElement);

                /// Creamos un elemento "agency_name" dentro del elemento "launch"
                Element agencyNameElement = doc.createElement("agency_name");
                agencyNameElement.setTextContent(launch.getAgency_name());
                launchElement.appendChild(agencyNameElement);

                /// Creamos un elemento "location_name" dentro del elemento "launch"
                Element locationNameElement = doc.createElement("location_name");
                locationNameElement.setTextContent(launch.getLocation_name());
                launchElement.appendChild(locationNameElement);

                /// Añadimos el elemento "launch" al elemento "launches"
                launchesElement.appendChild(launchElement);

            }
        } catch (ParserConfigurationException e) {
            System.out.println("MAL MAL MAL MAL");
            throw new RuntimeException(e);
        }
    }
}