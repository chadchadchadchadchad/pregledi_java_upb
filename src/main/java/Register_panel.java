import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Register_panel {
    private JTextField name_text;
    private JTextField email_text;
    private JTextField pass_text;
    private JTextField app_pass_text;
    private JButton register_button;
    private JButton add_logo_button;
    private static JFrame frame;
    private JPanel panelReg;
    private JLabel logo_label;


    public Register_panel() {
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_text.toString();
                String email = email_text.toString();
                String pass = pass_text.toString();
                String appPass = app_pass_text.toString();

            }
        });
        add_logo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fc;

                fc = new JFileChooser();
                fc.showOpenDialog(fc);

                String path = fc.getSelectedFile().getPath();
                ImageIcon icon = new ImageIcon(path);

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

                logo_label.setIcon(imageIcon);

                System.out.println(fc);

                fc.toString();

            }
        });
    }


    public static void main(String[] args) {
        frame = new JFrame("Frame");
        frame.setContentPane(new Register_panel().panelReg);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500,600);
        frame.setVisible(true);
    }
}
