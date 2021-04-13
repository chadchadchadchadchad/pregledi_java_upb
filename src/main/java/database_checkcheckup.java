import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database_checkcheckup {

    public static checkups[] returncheckups(int em_id)
    {
        checkups[] names = new checkups[1];
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            System.out.println( "SELECT p.datum_pregleda, zd.ime, doc.ime, doc. priimek FROM pregledi p INNER JOIN zdravstveni_domovi zd ON zd.id = p.zdravstveni_dom_id INNER JOIN zdravniki doc ON doc.id = p.zdravnik_id WHERE delavec_id = " + em_id + ";" );

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT p.datum_pregleda, zd.ime, doc.ime, doc. priimek FROM pregledi p INNER JOIN zdravstveni_domovi zd ON zd.id = p.zdravstveni_dom_id INNER JOIN zdravniki doc ON doc.id = p.zdravnik_id WHERE delavec_id = "+ em_id +";" );
            int st = 0;
            while ( rs.next() ) {
                st++;
            }
            rs.close();
            stmt.close();

            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "SELECT p.id, p.datum_pregleda, zd.ime, doc.ime, doc.priimek, p.zdravnik_id, p.zdravstveni_dom_id FROM pregledi p INNER JOIN zdravstveni_domovi zd ON zd.id = p.zdravstveni_dom_id INNER JOIN zdravniki doc ON doc.id = p.zdravnik_id WHERE delavec_id = "+ em_id +";" );

            names = new checkups[st];

            int st2 = 0;
            while ( rs2.next() ) {
                checkups a = new checkups();

                a.id = rs2.getInt(1);
                String datum = rs2.getString("datum_pregleda");
                a.Zd_name = rs2.getString(3);
                a.Doc_name = rs2.getString(4) + " " + rs2.getString(5);
                a.Doc_id = rs2.getInt(6);
                a.Zd_id = rs2.getInt(7);
                a.Emp_id = em_id;

                String[] novdatum = datum.split(" ");
                a.Datum_pregleda = novdatum[0];

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

    public static void delete_checkup(int id_check)
    {
        try {
            String uName = "rwnxlyblnkntlj";
            String uPass= "465c86b2ff6199771cf8e82088e23ce686b7fb951d00fae60c6ac7dc87fe9091";
            String host = "jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d89q761es01jua";

            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM pregledi WHERE id = " + id_check + ";" );
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

}
