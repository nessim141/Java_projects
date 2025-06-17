import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dictionnaire {

    static private final String URL = "jdbc:mysql://localhost:3306/dic_fr";
    static private final String USER = "nessim";
    static private final String PASSWORD = "nessim";
    static private Connection con = null;

    static {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
        } catch (SQLException e) {
            System.out.println("Problème de connexion à la base de données dic_fr : " + e.getMessage());
        }
    }

    public Boolean verifier(String mot1) throws SQLException {
        if (con == null) {
            throw new SQLException("La connexion à la base de données est null. Vérifiez vos paramètres de connexion.");
        }

        String query = "SELECT * FROM lexique WHERE mot = ?";
        try (PreparedStatement pd = con.prepareStatement(query)) {
            pd.setString(1, mot1);

            try (ResultSet result = pd.executeQuery()) {
                return result.next(); 
            }
        }
    }
}
