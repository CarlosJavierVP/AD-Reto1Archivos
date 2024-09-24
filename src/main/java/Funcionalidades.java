import java.io.*;
import java.util.ArrayList;


/**
 * Clase Funcionalidades
 * @author Carlos Javier Valenzuela
 */
public class Funcionalidades {

    /**
     * Método main
     * @param args donde se ejecutan las funcionalidades de la app
     */
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
        leerPlantilla("plantilla.html");
    }

    /**
     * Método leerArchivo
     * @param archivo ruta para leer documento
     */
    public static void leerArchivo(String archivo){
        var listaPeliculas = new ArrayList<Pelicula>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String cadena;
            while ((cadena = br.readLine()) != null){
                String[] trozo = cadena.split(",");
                var peli = new Pelicula();
                peli.setId(Integer.parseInt(trozo[0]));
                peli.setTitulo(trozo[1]);
                peli.setAño(Integer.parseInt(trozo[2]));
                peli.setDirector(trozo[3]);
                peli.setGenero(trozo[4]);
                listaPeliculas.add(peli);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Pelicula peli : listaPeliculas){
            System.out.println(peli);
        }

    }

    /**
     * Método carpetaSalida para crear un directorio
     * @param ruta nombre/ruta del directorio que se va a crear
     */
    public static void carpetaSalida(String ruta){
        // Genera una carpeta llamada "salida"
        File f = new File(ruta);
        if (!f.exists()){
            String[] trozos = ruta.split("/");
            String directorio ="";
            for (int i = 0; i < trozos.length -1; i++){
                directorio += trozos[i]+"/";
            }
            f = new File(directorio);
            f.mkdirs();
        }
    }

    /**
     * Método leerPlantilla para procesar un documento HTML
     * @param html ruta de la plantilla
     */
    public static void leerPlantilla(String html){
        carpetaSalida("Salida");

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(html))){
            String linea;
            while ((linea = br.readLine()) != null ){
                sb.append(linea).append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sb);

    }




}
