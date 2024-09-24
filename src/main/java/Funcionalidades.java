import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Funcionalidades {
    public static void main(String[] args) {
        /*
        * Métodos en el main:
        *   -Leer archivo csv
        *   -Leer plantilla html
        *   -Generar archivo html con la info del csv y con la estrutura de la plantilla
        *
        * Refactorizar***
        *
        */

        leerArchivo("peliculas.csv");
    }


    public static void leerArchivo(String archivo){
        var listaPeliculas = new ArrayList<Pelicula>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String cadena;
            while ((cadena = br.readLine()) != null){
                String[] trozo = cadena.split(",");
                var peli = new Pelicula();
                peli.setId(trozo[0]);
                peli.setTitulo(trozo[1]);
                peli.setAño(trozo[2]);
                peli.setDirector(trozo[3]);
                peli.setGenero(trozo[4]);
                listaPeliculas.add(peli);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
