import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    public static List<Film> films = new ArrayList<>();
    public static List<Seance> seances = new ArrayList<>();
    static String sql =  "select name from user";
    static String url = "jdbc:mysql://localhost:3306/cenima";
    static String username = "root";
    static String password = "230223";
    private static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url , username , password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        boolean running = true;



        System.out.println("=============================================");
        System.out.println("   SYSTÈME DE GESTION DE CINÉMA (JDBC)   ");
        System.out.println("=============================================");

        while (running) {
            afficherMenuPrincipal();
            int choix = lireChoix();

            switch (choix) {
                case 1:
                    menuGestionFilms();
                    break;
                case 2:
                    menuGestionSeances();
                    break;
                case 3:
                    menuGestionSpectateurs();
                    break;
                case 4:
                    menuGestionTickets();
                    break;
                case 0:
                    System.out.println("Fermeture de l'application...");
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
    }


    private static void afficherMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gestion des Films");
        System.out.println("2. Gestion des Séances");
        System.out.println("3. Gestion des Spectateurs");
        System.out.println("4. Gestion des Tickets");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }


    private static void menuGestionFilms() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION DES FILMS ---");
            System.out.println("1. Ajouter un film");
            System.out.println("2. Lister tous les films");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireChoix();
            switch (choix) {
                case 1:
                    System.out.println(">> Création d'un nouveau film...");
                    films.add(new Film(con));
                    break;
                case 2:
                    System.out.println(">> Liste des films disponible :");
                    for (Film f : films){
                        f.getFilm();
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void menuGestionSeances() throws Exception {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION DES SÉANCES ---");
            System.out.println("1. Planifier une séance ");
            System.out.println("2. Afficher les séances disponibles");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireChoix();
            switch (choix) {
                case 1:
                    System.out.println(">> Planification d'une séance...");
                    seances.add(new Seance(con));
                    break;
                case 2:
                    System.out.println(">> Liste des séances...");
                    for (Seance s : seances){
                        s.getSeance();
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void menuGestionSpectateurs() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION DES SPECTATEURS ---");
            System.out.println("1. Inscrire un spectateur");
            System.out.println("2. Lister les spectateurs");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireChoix();
            switch (choix) {
                case 1:
                    System.out.println(">> Inscription d'un spectateur...");

                    break;
                case 2:
                    System.out.println(">> Liste des spectateurs...");

                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void menuGestionTickets() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION DES TICKETS ---");
            System.out.println("1. Réserver un ticket (Vendre)");
            System.out.println("2. Voir les tickets d'un spectateur");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireChoix();
            switch (choix) {
                case 1:
                    System.out.println(">> Nouvelle réservation...");

                    break;
                case 2:
                    System.out.println(">> Historique des tickets...");

                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static int lireChoix() {
        try {
            int choix = Integer.parseInt(scanner.nextLine());
            return choix;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}