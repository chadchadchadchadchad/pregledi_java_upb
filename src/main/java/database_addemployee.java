import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_addemployee {

    public static int insert_employee(String imed, String priimekd, String phone, String email, String date, String address, int id_k, int id_p)
    {
        int id = 0;

        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT add_employee('"+ imed +"', '"+ priimekd +"', '"+ phone +"', '"+ email +"', '"+ date +"', '"+ address +"', "+ id_k +", "+ id_p +");");
            while ( rs.next() ) {
                int  idp = rs.getInt(1);

                System.out.println( "ID = " + idp );

                id = idp;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return id;
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


}
