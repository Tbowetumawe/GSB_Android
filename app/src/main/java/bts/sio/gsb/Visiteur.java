package bts.sio.gsb;

public class Visiteur {

    private static int id = Integer.parseInt(null);
    private static String nom = null;
    private static String prenom = null;
    private static String login = null;
    private static String password = null;

    public Visiteur(int id,String nom, String prenom, String login, String password   ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Visiteur.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Visiteur.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Visiteur.prenom = prenom;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Visiteur.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Visiteur.password = password;
    }
}

