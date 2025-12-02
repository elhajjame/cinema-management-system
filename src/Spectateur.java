import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Spectateur {

    private int id;
    private String nom;
    private String email;

    public Spectateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public Spectateur(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // JDBC

    private static final String url = "jdbc:mysql://localhost:3306/cinema";
    private static final String user = "was_sim";
    private static final String password = "133";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Ajouter un spectateur
    public static void ajouterSpectateur(Spectateur s) {
        String sql = "INSERT INTO Spectateur (nom, email) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getEmail());
            stmt.executeUpdate();

            System.out.println("Spectateur ajouté : " + s.getNom());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}