import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class database_change_employee_settings {
    public static employees returnworkers(int delavec_id)
    {
        employees employee = new employees();

        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);


            System.out.println( "SELECT * FROM delavci WHERE delavec_id = " + delavec_id + ";" );


            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT d.ime, d.priimek, d.naslov, d.telefon, d.email, d.datum_roj , k.ime FROM delavci d INNER JOIN kraji k ON k.id = d.kraj_id WHERE d.id = " + delavec_id + ";" );


            while ( rs.next() ) {
                employee.Name = rs.getString(1);
                employee.Lastname = rs.getString(2);
                employee.Address = rs.getString(3);
                employee.PhoneNumber = rs.getString(4);
                employee.Email = rs.getString(5);
                String date = rs.getString(6);
                employee.City = rs.getString(7);

                String[] novdatum = date.split(" ");
                employee.DateOfBirth = novdatum[0];


            }


            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return employee;
    }
    public static String[] returntowns()
    {
        String[] names = new String[100];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM kraji;" );
            int st = 0;
            while ( rs.next() ) {
                st++;
            }

            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "SELECT * FROM kraji;" );
            names = new String[st];

            int st2 = 0;
            while ( rs2.next() ) {
                String  name = rs2.getString("ime");

                names[st2] = name;

                st2++;
            }

            rs2.close();
            stmt2.close();

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return names;
    }

    public static int updateworkers(int id, String name, String lastname, String adress, String phoneNum, String email, String date, String town){

        int id_g = 0;

        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT edit_employee(" + id + ", '" + name + "', '" + lastname + "', '" + phoneNum + "'," +
                    "'" + email + "', '" + date + "', '" + adress + "', '" + town + "');");

            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return id_g;
    }


    public static void removeworker(int id){
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeQuery( "SELECT remove_employee(" + id + ")" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
