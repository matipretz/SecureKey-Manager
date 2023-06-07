package src;

public  class Jfun {
    public static void print(String msg){
        System.out.println(msg);
    }

    public static void welcome() {
        print("###\nSecureKey Manager 0.1\nCaC 4.0-23438-2023 SEM1\nAlumno: Murad Pretz Matias Martin\n33.366.158\nDocente: Rubé Gonzalo F.\nTutora:Gonzales Zoraida\n###");
    }
    public static void menu() {
        print("### MENU PRINCIPAL ###\nSeleccione una opcion:\n+1. Iniciar sesion.\n2. Crear usuario.\n3. Salir.");
        
    }

}