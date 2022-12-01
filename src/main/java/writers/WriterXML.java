package writers;

import objetos.Launch;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public interface WriterXML {

    static void launchToXML(List<Launch> launches_list) throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LaunchList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(launches_list, new File("out/launch.xml"));
        } catch (Exception e){
            System.out.println("NO HA FUNCIONAO");
        }


        /*
        for (Launch launch : launches_list){
            JAXBContext jaxbContext = JAXBContext.newInstance(Launch.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File("out/launch.xml");
            jaxbMarshaller.marshal(launch, file);
        }
        */
    }
}