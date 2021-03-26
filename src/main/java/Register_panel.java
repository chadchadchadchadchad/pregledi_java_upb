import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;

public class Register_panel {
    private JTextField name_text;
    private JTextField email_text;
    private JButton register_button;
    private JButton add_logo_button;
    private static JFrame frame;
    private JPanel panelReg;
    private JLabel logo_label;
    private JComboBox company_box;
    private JPasswordField pass_text;
    private JPasswordField app_pass_text;

    public String fileName = "";


    public Register_panel() {
            String[] town_names = database_addemployee.returntowns();

            for (String town: town_names){
                company_box.addItem(town);
            }
            company_box.setSelectedItem(null);
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_text.getText();
                String email = email_text.getText();
                String pass = pass_text.getText();
                String appPass = app_pass_text.getText();
                String place = company_box.getToolTipText();
                String olpas = pass;

                pass = getString(pass);

                if (olpas.equals(appPass) && name.isBlank() == false && email.isBlank() == false && pass.isBlank() == false) {
                    database_registration.registerCompany(name, email, pass, fileName);
                    frame.dispose();
                    Main_panel.main(new String[] { "a", "b" });
                }

                else {
                    JOptionPane.showMessageDialog(null, "Please re-check your credentials");
                    JOptionPane.showMessageDialog(null, place);

                }

            }

        });
        add_logo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fc;

                fc = new JFileChooser();
                fc.showOpenDialog(fc);

                String path = fc.getSelectedFile().getPath();

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));


                fileName = fc.getSelectedFile().getName();

                BufferedImage bi = new BufferedImage(
                        imageIcon.getIconWidth(),
                        imageIcon.getIconHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                // paint the Icon to the BufferedImage.
                imageIcon.paintIcon(null, g, 0,0);
                g.dispose();


                File file = null;
                try {
                    file = new File("uploads", fileName);
                    FileWriter writer = new FileWriter(file);
                    writer.write(500);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    ImageIO.write(bi, "png", file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                logo_label.setIcon(imageIcon);

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


    public static void main() {
        frame = new JFrame("Frame");
        frame.setContentPane(new Register_panel().panelReg);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500,600);
        frame.setVisible(true);
    }
}
