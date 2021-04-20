import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_doctors {
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
    public static doctors[] getDoctors() {
        doctors[] names = new doctors[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT z.*, zd.ime FROM zdravniki z INNER JOIN  zdravstveni_domovi zd ON zd.id = z.zdravstveni_dom_id ORDER BY id;");
            int st = 0;
            while (rs.next()) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT z.*, zd.ime FROM zdravniki z INNER JOIN  zdravstveni_domovi zd ON zd.id = z.zdravstveni_dom_id ORDER BY id;");

            names = new doctors[st];

            int st2 = 0;
            while (rs2.next()) {
                doctors doctor = new doctors();

                doctor.Id = rs2.getInt(1);
                doctor.Name = rs2.getString(2);
                doctor.Lastname = rs2.getString(3);
                doctor.PhoneNumber = rs2.getString(4);
                doctor.Email = rs2.getString(5);
                doctor.HealthCentreId = rs2.getInt(6);
                doctor.HealthCentre = rs2.getString(7);

                names[st2] = doctor;

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
    public static void addDoctor(doctors doctor)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "INSERT INTO zdravniki(ime, priimek,  telefon, email, zdravstveni_dom_id) VALUES ('" + doctor.Name + "', '" + doctor.Lastname + "',  '" + doctor.PhoneNumber + "', '" + doctor.Email + "', (SELECT id FROM zdravstveni_domovi WHERE ime = '" + doctor.HealthCentre + "'));" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    public static void updateDoctor(doctors doctor)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "UPDATE zdravniki Set ime = '" + doctor.Name + "', priimek = '" + doctor.Lastname + "', telefon = '" + doctor.PhoneNumber + "', email = '" + doctor.Email + "', zdravstveni_dom_id =  (SELECT id FROM zdravstveni_domovi WHERE ime = '" + doctor.HealthCentre + "') WHERE id = " + doctor.Id + ";" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    public static void deleteDoctor(doctors doctor)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass = "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM zdravniki WHERE id = " + doctor.Id + ";" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
