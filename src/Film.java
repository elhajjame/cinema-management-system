import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class Film {
    private int filmId;
    private String duree ;
    private String titr ;
    private String categorie;
    private String getId = "select id from film where titr = ? and duree = ? and categorie = ?";
    private String sqlInsert = "insert into film ( titr , duree , categorie) values(?, ? ,?);";
    private String getCategorieSql = "Select categorie from film where id = ?";
    private String updateCategorie = "update film set categorie = ? where id = ?";
    private String updateTitre = "update film set categorie = ? where id = ?";
    Scanner scanner = new Scanner(System.in);

    public Film(Connection con) throws SQLException {
        System.out.print("Entrez le titre: ");
        this.titr = scanner.nextLine();
        System.out.print("Entrez la duree: ");
        this.duree = scanner.nextLine();
        System.out.print("Entrez le categorie: ");
        this.categorie = scanner.nextLine();
        PreparedStatement ps = con.prepareStatement(sqlInsert);
        ps.setString(1,titr);
        ps.setString(2,duree);
        ps.setString(3,categorie);
        ps.executeUpdate();
        PreparedStatement ps2 = con.prepareStatement(getId);
        ps2.setString(1,titr);
        ps2.setString(2,duree);
        ps2.setString(3,categorie);
        try (ResultSet rs2 = ps2.executeQuery()) {
            if (rs2.next()) {
                int newId = rs2.getInt("id");
                this.setFilmId(newId);
            }
        }
       }

    public String getCategorie(Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(getCategorieSql);
        st.setInt(1,filmId);
        ResultSet result = st.executeQuery();
        result.next();
        String resu = result.getString("categorie");
        return resu;
    }

    public void setCategorie( Connection con ,String categorie) throws SQLException {
        PreparedStatement ps = con.prepareStatement(updateCategorie);
        ps.setString(1, categorie);
        ps.setInt(2,filmId);
        ps.executeUpdate();
    }

    public String getTitre() {
        return titr;
    }

    public void setTitre(Connection con ,String titre) throws SQLException {
        PreparedStatement ps = con.prepareStatement(updateTitre);
        ps.setString(1, titre);
        ps.setInt(2,filmId);
        ps.executeUpdate();
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void getFilm(){
        System.out.println("Film titre: " + this.titr + " - Film duree: " + this.duree + " - Film categorie: " + this.categorie);
    }
    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", duree='" + duree + '\'' +
                ", titr='" + titr + '\'' +
                ", categorie='" + categorie +
                '}';
    }
}
