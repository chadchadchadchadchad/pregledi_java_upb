import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_manage_health_centre {
    public static healthCentres[] getPlaces() {
        healthCentres[] names = new healthCentres[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, ime FROM kraji ORDER BY id;");
            int st = 0;
            while (rs.next()) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT id, ime FROM kraji ORDER BY id;");

            names = new healthCentres[st];

            int st2 = 0;
            while (rs2.next()) {
                healthCentres centre = new healthCentres();

                centre.PlaceID = rs2.getInt(1);
                centre.Place = rs2.getString(2);

                names[st2] = centre;

                st2++;
            }
            System.out.println(names[2]);

            rs2.close();
            stmt2.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return names;
    }
    public static healthCentres[] getCentres() {
        healthCentres[] names = new healthCentres[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT z.*, k.ime FROM zdravstveni_domovi z INNER JOIN  kraji k ON k.id = z.kraj_id ORDER BY id;");
            int st = 0;
            while (rs.next()) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT z.*, k.ime FROM zdravstveni_domovi z INNER JOIN  kraji k ON k.id = z.kraj_id ORDER BY id;");

            names = new healthCentres[st];

            int st2 = 0;
            while (rs2.next()) {
                healthCentres centre = new healthCentres();

                centre.Id = rs2.getInt(1);
                centre.Adress = rs2.getString(2);
                centre.PhoneNum = rs2.getString(3);
                centre.PlaceID = rs2.getInt(4);
                centre.Name = rs2.getString(5);
                centre.Place = rs2.getString(6);

                names[st2] = centre;

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

        return names;
    }
    public static void addCentre(healthCentres centre)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "INSERT INTO zdravstveni_domovi(naslov, telefon, kraj_id, ime) VALUES ('" + centre.Adress + "', '" + centre.PhoneNum + "', (SELECT id FROM kraji WHERE ime = '" + centre.Place + "'), '" + centre.Name + "')" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    public static void updateCentre(healthCentres centre)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "UPDATE zdravstveni_domovi Set naslov = '" + centre.Adress + "', telefon = '" + centre.PhoneNum + "', kraj_id =  (SELECT id FROM kraji WHERE ime = '" + centre.Place + "'), ime = '" + centre.Name + "' WHERE id = " + centre.Id + ";" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    public static void deleteCentre(int idc)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM zdravstveni_domovi WHERE id = " + idc + ";" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
