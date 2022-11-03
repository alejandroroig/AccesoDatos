import java.util.Arrays;

public class Lenguaje {
    private String nombre;
    private int sueldo;
    private boolean compilado;
    private String[] programadores;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isCompilado() {
        return compilado;
    }

    public void setCompilado(boolean compilado) {
        this.compilado = compilado;
    }

    public String[] getProgramadores() {
        return programadores;
    }

    public void setProgramadores(String[] programadores) {
        this.programadores = programadores;
    }

    @Override
    public String toString() {
        return "Lenguaje{" +
                "nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                ", compilado=" + compilado +
                ", programadores=" + Arrays.toString(programadores) +
                '}';
    }
}
