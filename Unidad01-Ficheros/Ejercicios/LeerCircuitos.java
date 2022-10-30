import java.nio.file.Path;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

//
// Añadir dependencias:
// jakarta.xml.bind-api 3.0.0
// jaxb-impl 3.0.0
//
public class LeerCircuitos {
    public static void main(String[] args)  {
        Path leerFichero = Path.of("Unidad01-Ficheros\\Ejercicios","formula1_2021season_calendar.xml");

        JAXBContext context;

        try {
            context = JAXBContext.newInstance(Calendario.class);
            Unmarshaller jaxbUnmarshaller  = context.createUnmarshaller();

            Calendario calendario = (Calendario) jaxbUnmarshaller.unmarshal(leerFichero.toFile());

            for(Carrera carrera: calendario.getCarreras()){
                System.out.println(carrera.toString());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

