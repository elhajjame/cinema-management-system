import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Seance {
    private int seanceId;
    private String houre;
    private String salle;
    private int max;
    private int filmId;
    private int idSpectateur;
    private String sqlInsert = "insert into seance (horair , salle , capaciteMaximale, filmId) values (? , ? , ? , ?);";
    private String getId = "select id from seance where horair = ? and salle = ? and capaciteMaximale = ?;";
    Scanner scanner = new Scanner(System.in);

    public Seance(Connection con) throws Exception {
        System.out.print("Entre le film id : ");
        this.filmId = scanner.nextInt();
        System.out.print("Entre la salle number : ");
        this.salle = scanner.nextLine();
        System.out.print("Entre le film id : ");
        this.houre = scanner.nextLine();
        System.out.print("Entre le film id : ");
        this.max = scanner.nextInt();

        try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
            ps.setString(1,houre);
            ps.setString(2,salle);
            ps.setInt(3,max);
            ps.setInt(4,filmId);
            ps.executeUpdate();
        }

        try (PreparedStatement ps2 = con.prepareStatement(getId)) {
            ps2.setString(1,houre);
            ps2.setString(2,salle);
            ps2.setInt(3,max);

            try (ResultSet rs2 = ps2.executeQuery()) {
                if (rs2.next()) {
                    int newId = rs2.getInt("id");
                    this.setSeanceId(newId);
                }
            }
        }
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getHoure() {
        return houre;
    }

    public void setHoure(String houre) {
        this.houre = houre;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(int seanceId) {
        this.seanceId = seanceId;
    }

    public int getIdSpectateur() {
        return idSpectateur;
    }

    public void setIdSpectateur(int idSpectateur) {
        this.idSpectateur = idSpectateur;
    }

    public void getSeance (){
        System.out.println("Film id : " + filmId + " - heaur: " + houre + " - maximum capacity: " + max);
    }

    @Override
    public String toString() {
        return "Seance{" +
                "seanceId=" + seanceId +
                ", houre='" + houre + '\'' +
                ", salle='" + salle + '\'' +
                ", max=" + max +
                ", filmId=" + filmId +
                ", idSpectateur=" + idSpectateur +
                '}';
    }
}
