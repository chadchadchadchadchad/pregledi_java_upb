import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_graph_data {
    public static charts[] getCentres(charts charts) {
        charts[] nums = new charts[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM pregledi WHERE datum_pregleda BETWEEN '" + charts.year + "-12-01' AND '" + charts.year + 1 + "-01-01';");
            int st = 0;
            while (rs.next()) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT COUNT(*) FROM pregledi WHERE datum_pregleda BETWEEN '" + charts.year + "-12-01' AND '" + charts.year + 1 + "-01-01';");

            nums = new charts[st];

            int st2 = 0;
            while (rs2.next()) {
                charts chart = new charts();

                chart.num = rs2.getInt(1);



                nums[st2] = chart;

                st2++;
            }

            rs2.close();
            stmt2.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return nums;
    }

}
