import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeerJson {
    public static void main(String[] args) {

        // Opción 1 con org.json
        List<Lenguaje> lenguajesJson = new ArrayList<>();
        try {
            String data = new String(Files.readString(Paths.get("Unidad01-Ficheros/Ejemplos/ejemplo.json")));
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {

                String str = jsonArray.get(i).toString();
                JSONObject objeto = new JSONObject(str);
                Lenguaje lenguaje = new Lenguaje();
                lenguaje.setNombre(objeto.getString("nombre"));
                lenguaje.setCompilado(objeto.getBoolean("compilado"));
                lenguaje.setSueldo(objeto.getInt("sueldo"));

                JSONArray programadoresJsonArray = objeto.getJSONArray("programadores");
                String[] programadores = new String[programadoresJsonArray.length()];
                for(int j = 0; j < programadores.length; j++) {
                    programadores[j]=programadoresJsonArray.optString(j);
                }
                lenguaje.setProgramadores(programadores);

                lenguajesJson.add(lenguaje);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Con org.json");
        lenguajesJson.forEach(System.out::println);


        // Opción 2 con com.google.gson
        List<Lenguaje> lenguajesGson = new ArrayList<>();
        try {
            String data = new String(Files.readString(Paths.get("Unidad01-Ficheros/Ejemplos/ejemplo.json")));
            Gson gson = new Gson();
            lenguajesGson = Arrays.stream(gson.fromJson(data, Lenguaje[].class)).toList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Con com.google.gson");
        lenguajesGson.forEach(System.out::println);


        // Opción 3 con com.fasterxml.jackson - databind, core y annotations
        List<Lenguaje> lenguajeJackson = new ArrayList<>();
        try {
            String data = new String(Files.readString(Paths.get("Unidad01-Ficheros/Ejemplos/ejemplo.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
            lenguajeJackson = Arrays.stream(objectMapper.readValue(data, Lenguaje[].class)).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Con com.fasterxml.jackson");
        lenguajeJackson.forEach(System.out::println);
    }
}
