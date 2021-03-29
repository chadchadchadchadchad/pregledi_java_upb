import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Check_up_panel {

    private JComboBox worker_combo;
    private JButton button_check;
    private JPanel panel;
    private JList healthcenter_list;
    private JButton add_em_button;
    private JTextField date_text;
    private JComboBox doctor_combo;
    private static int id_p;
    private int id_worker;
    private int id_zd;
    private int id_doc;
    private DefaultListModel list = new DefaultListModel();
    private String[] workers;
    private String[] healthcenters;
    private String[] doctors;
    private static JFrame frame;

    public void update()
    {
        worker_combo.removeAllItems();
        list.clear();

        workers = database_check_up_panel.returnworkers(id_p);

        healthcenters = database_check_up_panel.returnhealhcenters();

        for (String name: workers) {
            worker_combo.addItem(name);
        }

        for (String name: healthcenters) {
            list.addElement(name);
        }

        worker_combo.setSelectedItem(null);
    }

    public Check_up_panel() {
        update();

        worker_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                healthcenter_list.setEnabled(true);
                String name = workers[worker_combo.getSelectedIndex()];

                String[]name_surname = name.split(" ");

                id_worker = database_check_up_panel.returnworkerid(name_surname[0], name_surname[1], id_p);
                System.out.println("ID: " + id_worker);

                healthcenter_list.setModel(list);
            }
        });

        button_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                button_check.setEnabled(false);
                healthcenter_list.setEnabled(false);
                doctor_combo.setEnabled(false);
                doctor_combo.setSelectedItem(null);
                worker_combo.setSelectedItem(null);
                doctor_combo.removeAllItems();
            }
        });
        healthcenter_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //doctor_combo.removeAllItems();

                id_zd = database_check_up_panel.returnzdid(healthcenter_list.getSelectedValue().toString());
                doctors = database_check_up_panel.returndoctors(id_zd);

                System.out.println(doctors[0]);

                for (String d_name: doctors) {
                    doctor_combo.addItem(d_name);
                }

                doctor_combo.setEnabled(true);
            }
        });
        add_em_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add_employee_form.main(id_p);
                frame.dispose();
            }
        });
        doctor_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = doctors[doctor_combo.getSelectedIndex()];

                String[]name_surname = name.split(" ");

                id_doc = database_check_up_panel.returndoctorid(name_surname[0], name_surname[1], id_zd);

                button_check.setEnabled(true);
            }
        });
    }

    public static void main(int idp) {
        id_p = idp;
        frame = new JFrame("Add employees to checkup");
        frame.setContentPane(new Check_up_panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
