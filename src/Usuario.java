import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private String contrasenaUsuario;
    private List<Contrasenas> contrasenas = new ArrayList<Contrasenas>();

    Usuario(String nombreUsuario, String contrasenaUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
    }

    Usuario(String nombreUsuario, String contrasenaUsuario, List<Contrasenas> contrasenas) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.contrasenas = contrasenas;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public List<Contrasenas> getContrasenas() {
        return contrasenas;
    }

    public void setContrasena(Contrasenas contrasena) {
        contrasenas.add(contrasena);
    }

    public void listarContrasenas() {
        Varios.limpiar();
        System.out.println("Cargando...");        
        Varios.pausa(500);
        Varios.limpiar();
        for (int i = 0; i < getContrasenas().size(); i++) {
            System.out.println("ID: " + i + "\nNOMBRE: " + getContrasenas().get(i).getNombre() + "\nCONTRASENA: " + getContrasenas().get(i).getContrasena()+"\n");
        }
    }
}