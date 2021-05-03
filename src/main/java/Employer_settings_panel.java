import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Employer_settings_panel {
    private JPanel panel;
    private JTextField name_text;
    private JTextField email_text;
    private JTextField pass_text;
    private JTextField passRep_text;
    private JComboBox place_combo;
    private JButton changeLogoButton;
    private JButton saveChangesButton;
    private JLabel logo_label;
    private JButton deleteCompanyButton;
    private static JFrame frame;
    public String fileName = "";
    private static int id_p;
    private companies company = new companies();

    public void update_combo()
    {
        companies[] allTowns = database_change_employer_settings.getPlaces();
        place_combo.removeAllItems();

        for (companies item: allTowns) {
            place_combo.addItem(item.Place);
        }
    }

    public Employer_settings_panel() {
        update_combo();
        company = database_change_employer_settings.getEmployer(id_p);
        name_text.setText(company.Name);
        email_text.setText(company.Email);
        place_combo.setSelectedItem(company.Place);

        ImageIcon iconLogo = new ImageIcon("uploads/" + company.Logo + "");
        logo_label.setIcon(iconLogo);


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1 = pass_text.getText();
                String p2 = passRep_text.getText();
                if(p1.equals(p2)) {
                    company.Name = name_text.getText();
                    company.Email = email_text.getText();
                    company.Pass = getString(pass_text.getText());
                    company.Logo = fileName;
                    company.Place = place_combo.getSelectedItem().toString();

                    database_change_employer_settings.updateCompany(company);
                    JOptionPane.showMessageDialog(null, "Saved changes successfully.");
                    Check_up_panel.main(id_p);
                    frame.dispose();
                }

                else{
                    JOptionPane.showMessageDialog(null, "Passwords must match.");
                }
            }
        });
        deleteCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.OK_CANCEL_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Beware, this action will permanently delete your company account and all its data.", "WARNING!", dialogButton);
                if(dialogResult == 0) {
                    JOptionPane.showMessageDialog(null, "It was nice doing business with you, Goodbye.");
                    database_change_employer_settings.deleteCompany(company);
                    System.exit(1);
                } else {

                }
            }
        });
        changeLogoButton.addActionListener(new ActionListener() {
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
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Check_up_panel.main(id_p);
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

    public static void main(int idp) {
        id_p = idp;
        frame = new JFrame("Employer settings");
        frame.setContentPane(new Employer_settings_panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
