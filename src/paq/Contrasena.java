package paq;

/**
 * Esta clase contiene al constructor del objeto Contrasena.
 *
 */
public class Contrasena {
    private String id;
    private String nombre;
    private String contrasena;

    /**
     * Constructor de la clase Contrasena.
     *
     * @param id         El identificador de la contraseña.
     * @param nombre     El nombre de la contraseña.
     * @param contrasena La contraseña.
     */
    public Contrasena(String id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el identificador de la contraseña.
     *
     * @return El identificador de la contraseña.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la contraseña.
     *
     * @param id El identificador a establecer.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la contraseña.
     *
     * @return El nombre de la contraseña.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la contraseña.
     *
     * @param nombre El nombre de la contraseña a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña.
     *
     * @return La contraseña.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña.
     *
     * @param contrasena La contraseña a establecer.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
