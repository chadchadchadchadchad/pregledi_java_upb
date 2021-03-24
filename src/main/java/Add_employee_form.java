import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Add_employee_form {
    private static int id_p;
    private int id_k;
    private JPanel panel;
    private JTextField name_text;
    private JTextField surname_text;
    private JTextField mobile_text;
    private JTextField email_text;
    private JTextField date_text;
    private JTextField address_text;
    private JComboBox town_combo;
    private JButton add_button;
    private static JFrame frame;

    public Add_employee_form() {
        String[] town_names = dbconnect.returntowns();

        for (String town: town_names){
            town_combo.addItem(town);
        }

        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(town_combo.getSelectedItem().toString());
                String nameofemployee = name_text.getText();
                String surnameofemployee = surname_text.getText();

                if(nameofemployee == "" || surnameofemployee == "")
                {
                    JOptionPane.showMessageDialog(null, "Employee not added, please enter name and surname");
                }

                else {
                    id_k = dbconnect.return_town_id(town_combo.getSelectedItem().toString());

                    int id_e = dbconnect.insert_employee(name_text.getText(), surname_text.getText(), mobile_text.getText(), email_text.getText(), date_text.getText(), address_text.getText(), id_k, id_p);
                    if (id_e != 0) {
                        JOptionPane.showMessageDialog(null, "Employee added");

                        Check_up_panel.main(id_p);

                        frame.dispose();
                    } else
                        JOptionPane.showMessageDialog(null, "Employee not added, try again");
                }

            }
        });
    }

    public static void main(int idp) {
        id_p = idp;
        frame = new JFrame("Add employee");
        frame.setContentPane(new Add_employee_form().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
