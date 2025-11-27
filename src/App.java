import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {



        String sql =  "select name from user";
        String url = "jdbc:mysql://localhost:3306/cenima";
        String username = "root";
        String password = "230223";
        Connection con = DriverManager.getConnection(url , username , password);
        Film film1 = new Film(con , "4h" ,"dfd" , "moooo");
        System.out.println(film1.getCategorie(con));
        System.out.println(film1.getFilmId());

        Seance s = new Seance(con , 1040,"52" , "183" , 1);

        System.out.println(s.getSeanceId());



    }
}
