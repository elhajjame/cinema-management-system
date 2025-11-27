import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Ticket {
    private int ticketId;
    static int count = 1;
    private double prix;
    private Seance seance;
    private Spectator spectator;


    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Spectator getSpectator() {
        return spectator;
    }

    public void setSpectator(Spectator spectator) {
        this.spectator = spectator;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }


    public void saveToDatabase(Connection con) throws SQLException{
        String sql = "insert into Ticket (ticketId, prix, seanceId, spectatorId) values (?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ticketId);
        ps.setDouble(2, prix);
        ps.setInt(3, seance.getSeanceId());
        ps.setInt(4, spectator.getSpectatorId());
        ps.executeUpdate();
        ps.close();
    }
}
