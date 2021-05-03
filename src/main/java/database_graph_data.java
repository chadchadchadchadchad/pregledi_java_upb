import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_graph_data {
    public static int getNumOfChecks(int year) {
        int k = 0;
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            int year2 = year + 1;
            year--;
            System.out.println("SELECT COUNT(p.*) FROM pregledi p INNER JOIN delavci d ON d.id = p.delavec_id WHERE (p.datum_pregleda BETWEEN '" + year + "-12-31' AND '" + year2 + "-01-01') AND d.podjetje_id = "+ Check_up_panel.id_p +";");


            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(p.*) FROM pregledi p INNER JOIN delavci d ON d.id = p.delavec_id WHERE (p.datum_pregleda BETWEEN '" + year + "-12-31' AND '" + year2 + "-01-01') AND d.podjetje_id = "+ Check_up_panel.id_p +";");
            while (rs.next()) {
                k = rs.getInt(1);
            }
            rs.close();
            stmt.close();

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        //return nums;
        return k;
    }

}
