import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

public class Main_panel {
    private JButton button1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField email_text;
    private JTextField geslo_text;
    private JButton register_show;
    private static JFrame frame;


    public Main_panel() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = email_text.getText();
                String pass = geslo_text.getText();

                pass = getString(pass);

                int idp = dbconnect.returncompanyid(mail, pass);

                if(idp == 0) {
                    JOptionPane.showMessageDialog(null, "Login failed");
                }
                else {
                    frame.dispose();
                    Check_up_panel.main(idp);
                }
            }
        });
        register_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register_panel.main();
                frame.dispose();
            }
        });
    }

    private String getString(String pass) {
        String algorithm = "SHA1";
        byte[] plainText = pass.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            md.reset();
            md.update(plainText);
            byte[] encodedPassword = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }
            pass = sb.toString();

        } catch (Exception f) {
            f.printStackTrace();
        }
        return pass;
    }

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new Main_panel().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500,600);
        frame.setVisible(true);
    }
}
