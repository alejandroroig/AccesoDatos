import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;

@XmlRootElement(name="calendar")
@XmlAccessorType(XmlAccessType.FIELD)
public class Calendario {
    @XmlElement(name="race")
    private ArrayList<Carrera> carreras = null;

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }
}
