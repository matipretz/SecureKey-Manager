package paq;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene al constructor del objeto Contrasena.
 *
 */
public class Usuario {
    private String nombreUsuario;
    private String contrasenaUsuario;
    private List<Contrasena> contrasenas = new ArrayList<Contrasena>();

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombreUsuario     El nombre de usuario.
     * @param contrasenaUsuario La contraseña del usuario.
     */
    public Usuario(String nombreUsuario, String contrasenaUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombreUsuario     El nombre de usuario.
     * @param contrasenaUsuario La contraseña del usuario.
     * @param contrasenas       Las contraseñas asociadas al usuario.
     */
    public Usuario(String nombreUsuario, String contrasenaUsuario, List<Contrasena> contrasenas) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.contrasenas = contrasenas;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario a establecer.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenaUsuario La contraseña del usuario a establecer.
     */
    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     * Obtiene las contraseñas asociadas al usuario.
     *
     * @return La lista de contraseñas asociadas al usuario.
     */
    public List<Contrasena> getContrasenas() {
        return contrasenas;
    }

    /**
     * Agrega una contraseña a la lista de contraseñas del usuario.
     *
     * @param contrasena La contraseña a agregar.
     */
    public void setContrasena(Contrasena contrasena) {
        contrasenas.add(contrasena);
    }

    /**
     * Lista todas las contraseñas asociadas al usuario.
     */
    public void listarContrasenas() {
        Varios.limpiar();
        System.out.println("Cargando...");
        Varios.pausa(500);
        Varios.limpiar();
        for (int i = 0; i < getContrasenas().size(); i++) {
            System.out.println("ID: " + i + "\nNOMBRE: " + getContrasenas().get(i).getNombre() + "\nCONTRASENA: "
                    + getContrasenas().get(i).getContrasena() + "\n");
        }
    }
}
