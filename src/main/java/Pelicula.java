import lombok.Data;

import java.io.Serializable;

/**
 * Clase película
 * @Data contructor con parámetros, getters y setters
 * @author Carlos Javier Valenzuela
 */
@Data
public class Pelicula implements Serializable {
    private int id;
    private String titulo;
    private int año;
    private String director;
    private String genero;

}
