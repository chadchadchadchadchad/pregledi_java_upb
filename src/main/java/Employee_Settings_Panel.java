import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.List;
import java.util.ArrayList;

public class Employee_Settings_Panel {
    private static int id_k;
    private static int id_p;
    private JPanel panel;
    private JTextField name_text;
    private JTextField surname_text;
    private JTextField mobile_text;
    private JTextField email_text;
    private JTextField date_text;
    private JTextField address_text;
    private JComboBox town_combo;
    private JButton save_button;
    private JButton back_button;
    private JButton remove_employee_button;
    private static JFrame frame;
    private DefaultListModel list = new DefaultListModel();
    //private List<employees> employee = new ArrayList<>();
    employees employee;

    public void update()
    {
        town_combo.removeAllItems();

        String[] town_names = database_change_employee_settings.returntowns();

        for (String town: town_names){
            town_combo.addItem(town);
        }

        employee = database_change_employee_settings.returnworkers(id_k);
        System.out.println(id_k);
        town_combo.setSelectedItem(null);

    }

    public Employee_Settings_Panel() {
        update();

        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                database_change_employee_settings.updateworkers(id_k, name_text.getText(), surname_text.getText(), address_text.getText(), mobile_text.getText(),
                        email_text.getText(), date_text.getText(), town_combo.getSelectedItem().toString());
            }
        });
        panel.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                JComponent component = (JComponent)e.getSource();

                if ((HierarchyEvent.SHOWING_CHANGED & e.getChangeFlags()) != 0
                        &&  component.isShowing())
                {
                    name_text.setText(employee.Name);
                    surname_text.setText(employee.Lastname);
                    mobile_text.setText(employee.PhoneNumber);
                    email_text.setText(employee.Email);
                    date_text.setText(employee.DateOfBirth);
                    address_text.setText(employee.Address);
                    town_combo.setSelectedItem(employee.City);

                }
            }
        });
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Check_up_panel.main(id_p);
                frame.dispose();
            }
        });
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database_change_employee_settings.updateworkers(id_k, employee.Name, employee.Lastname, employee.Address, employee.PhoneNumber,
                        employee.Email, employee.DateOfBirth, employee.City);
            }
        });
        remove_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database_change_employee_settings.removeworker(id_k);

                Check_up_panel.main(id_p);
                frame.dispose();
            }
        });
    }


    public static void main(int ide, int idp) {
        id_k = ide;
        id_p = idp;
        frame = new JFrame("Employee settings");
        frame.setContentPane(new Employee_Settings_Panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
