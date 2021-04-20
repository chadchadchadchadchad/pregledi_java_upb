import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_change_employer_settings {
    public static companies[] getPlaces() {
        companies[] names = new companies[1];
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

            names = new companies[st];

            int st2 = 0;
            while (rs2.next()) {
                companies company = new companies();

                company.Id = rs2.getInt(1);
                company.Place = rs2.getString(2);

                names[st2] = company;

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
    public static companies getEmployer(int id_p)
    {
        companies company = new companies();

        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);


            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT p.id, p.ime, p.email, p.geslo, p.logo, k.ime FROM podjetja p INNER JOIN kraji k ON k.id = p.kraj_id WHERE p.id = " + id_p + ";" );


            while ( rs.next() ) {
                company.Id = rs.getInt(1);
                company.Name = rs.getString(2);
                company.Email = rs.getString(3);
                company.Pass = rs.getString(4);
                company.Logo = rs.getString(5);
                company.Place = rs.getString(6);

            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return company;
    }
    public static void updateCompany(companies company){
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "UPDATE podjetja SET ime = '" + company.Name + "', email = '" + company.Email + "', geslo = '" + company.Pass + "', logo = '" + company.Logo + "', kraj_id = (SELECT id FROM kraji WHERE ime = '" + company.Place + "') WHERE id = " + company.Id + ";");

            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
    public static void deleteCompany(companies company){
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM podjetja WHERE id = " + company.Id + ";" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
