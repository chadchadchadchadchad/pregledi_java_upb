import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_panel {
    private JButton button1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField email_text;
    private JTextField geslo_text;
    private static JFrame frame;


    public Main_panel() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = email_text.getText();
                String pass = geslo_text.getText();

                int idp = dbconnect.returncompanyid(mail, pass);

                if(idp == 0) {
                    System.out.println("Nedela");
                }
                else {
                    System.out.println("Dela");
                    frame.dispose();
                    Check_up_panel.main(idp);
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Frame");
        frame.setContentPane(new Main_panel().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500,600);
        frame.setVisible(true);
    }
}
