import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@XmlRootElement(name="race")
@XmlAccessorType(XmlAccessType.FIELD)
public class Carrera {
    @XmlAttribute(name="round")
    private int ronda;
    @XmlElement(name="gpname")
    private String nombre;
    @XmlElement(name="country")
    private String pais;
    @XmlElement(name="city")
    private String ciudad;
    @XmlElement(name="circuitname")
    private String circuito;
    @XmlElement(name="racedate")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fecha;

    public Carrera() {}

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Nº " + ronda
        + ": GP de " + nombre
        + ". En el " + circuito
                + " en " + ciudad +  " (" + pais
                + ") el " + fecha.format(DateTimeFormatter.ofPattern("dd MMM yyyy"/*, new Locale("es", "ES")*/));
    }
}
