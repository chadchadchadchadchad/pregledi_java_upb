import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_check_up_panel {
    public static String[] returndoctors(int zd_id)
    {
        String[] names = new String[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM zdravniki WHERE zdravstveni_dom_id = " + zd_id + ";" );
            int st = 0;
            while ( rs.next() ) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "SELECT * FROM zdravniki WHERE zdravstveni_dom_id = " + zd_id + ";" );

            names = new String[st];

            int st2 = 0;
            while ( rs2.next() ) {
                String  name = rs2.getString("ime");
                String surname = rs2.getString("priimek");

                String fullname = name + " " + surname;

                System.out.println( "Fullname = " + fullname );

                names[st2] = fullname;

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

    public static employees[] returnworkers(int podjetje_id)
    {
        employees[] names = new employees[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            System.out.println( "SELECT * FROM delavci WHERE podjetje_id = " + podjetje_id + ";" );

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM delavci WHERE podjetje_id = " + podjetje_id + ";" );
            int st = 0;
            while ( rs.next() ) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "SELECT * FROM delavci WHERE podjetje_id = " + podjetje_id + ";" );

            names = new employees[st];

            int st2 = 0;
            while ( rs2.next() ) {
                employees a = new employees();

                a.id = rs2.getInt("id");
                a.Name = rs2.getString("ime");
                a.Lastname = rs2.getString("priimek");
                a.Address = rs2.getString("naslov");

                names[st2] = a;

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



    public static String[] returnhealhcenters()
    {
        String[] names = new String[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ime FROM zdravstveni_domovi;" );

            int st = 0;
            while ( rs.next() ) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "SELECT ime FROM zdravstveni_domovi;" );

            names = new String[st];

            int st2 = 0;
            while ( rs2.next() ) {
                String  name = rs2.getString("ime");

                names[st2] = name;

                st2++;
            }

            rs2.close();
            stmt2.close();

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return names;
    }

    public static Integer returnzdid(String imezd)
    {
        int id = 0;
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id FROM zdravstveni_domovi WHERE ime = '" + imezd + "';" );
            while ( rs.next() ) {
                int  idp = rs.getInt("id");

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

    public static Integer returndoctorid(String imed, String priimekd, int id_zd)
    {
        int id = 0;
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id FROM zdravniki WHERE ime = '" + imed + "' AND priimek = '" + priimekd + "' AND zdravstveni_dom_id = "+ id_zd +";" );
            while ( rs.next() ) {
                int  idp = rs.getInt("id");

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
    public static void add_to_checkups(String datum, int zd_id, int doc_id, int del_id)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeQuery( "SELECT add_check_up('"+ datum +"', "+ zd_id +", "+ doc_id +", "+ del_id +");" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
