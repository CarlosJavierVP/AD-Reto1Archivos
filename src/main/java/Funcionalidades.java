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

        generarArchivosHTML();

    }
    /**
     * Método leerArchivo
     * @param archivo ruta para leer documento
     * @return listaPeliculas
     */
    public static ArrayList<Pelicula> leerArchivo(String archivo){
        var listaPeliculas = new ArrayList<Pelicula>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String cadena;
            while ((cadena = br.readLine()) != null){
                // cada línea se trocea para asignar al objeto película su correspondiente atributo y se añade a la lista de películas
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

        return listaPeliculas;
    }

    /**
     * Método carpetaSalida para crear un directorio
     * @param ruta nombre/ruta del directorio que se va a crear
     * @return File
     */
    public static File carpetaSalida(String ruta){
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

        f = new File(ruta);

        return f;
    }

    /**
     * Método leerPlantilla para procesar un documento HTML
     * @param html ruta de la plantilla
     * @return StringBuilder para leer el archivo HTML
     */
    public static StringBuilder leerPlantilla(String html){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(html))){
            String linea;
            // cada línea del archivo HTML añadirla en el String y a su vez incorporarla en el StringBuilder en cada iteración
            while ((linea = br.readLine()) != null ){
                sb.append(linea).append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb;
    }

    /**
     * Método generarArchivosHTML
     */
    public static void generarArchivosHTML(){

        //Se llama al método leerArchivo que devuelve una lista, y por cada película se generando un archivo HTML
        for (Pelicula peli : leerArchivo("peliculas.csv")){
            String ruta = "Salida/";
            String nombreHTML = peli.getId() + " - " + peli.getTitulo()+".html";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaSalida(ruta+nombreHTML)))){
                // Se pasa a String la plantilla de HTML (que es StringBuilder) y se reemplaza con el método replace los valores que queremos sustituir por los atributos de cada película
                String nuevoArchivo = leerPlantilla("plantilla.html").toString();
                nuevoArchivo = nuevoArchivo.replace("%%2%%", peli.getTitulo());
                nuevoArchivo = nuevoArchivo.replace("%%3%%", String.valueOf(peli.getAño()));
                nuevoArchivo = nuevoArchivo.replace("%%4%%", peli.getDirector());
                nuevoArchivo = nuevoArchivo.replace("%%5%%", peli.getGenero());

                bw.write(nuevoArchivo);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }



}
