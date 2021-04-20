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
    private JTabbedPane tabbed_page;
    private JList check_list;
    private JComboBox employee_combo;
    private JButton remove_check;
    private JButton edit_check;
    private JButton emp_sett_button;
    private JButton places_button;
    private JButton accountSettingsButton;
    private static int id_p;
    private int id_worker;
    private int id_zd;
    private int id_doc;
    private DefaultListModel list = new DefaultListModel();
    private DefaultListModel list_checkups = new DefaultListModel();
    private employees[] workers;
    private String[] healthcenters;
    private checkups[] checkups_list;
    private String[] doctors;
    private static JFrame frame;
    private int st = 0;

    public void update()
    {
        worker_combo.removeAllItems();
        employee_combo.removeAllItems();

        workers = database_check_up_panel.returnworkers(id_p);

        healthcenters = database_check_up_panel.returnhealhcenters();

        for (employees emp: workers) {
            String name = emp.Name + " " + emp.Lastname;

            worker_combo.addItem(name);
            employee_combo.addItem(name);
        }

        worker_combo.setSelectedItem(null);
        employee_combo.setSelectedItem(null);

        update_list();
    }

    public void update_list()
    {
        list.removeAllElements();

        for (String name: healthcenters) {
            list.addElement(name);
        }
    }

    public void update_list_check()
    {
        list_checkups.removeAllElements();

        for (checkups a: checkups_list) {
            String name = "Datum: " + a.Datum_pregleda + " Zdravstveni dom: " + a.Zd_name + " Zdravnik: " + a.Doc_name;
            list_checkups.addElement(name);
        }
    }

    public Check_up_panel() {
        update();

        worker_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                healthcenter_list.setEnabled(true);
                emp_sett_button.setEnabled(true);

                update_list();
                id_worker = workers[worker_combo.getSelectedIndex()].id;

                System.out.println("ID: " + id_worker);

                healthcenter_list.setModel(list);
                st = 0;
            }
        });

        button_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(date_text.getText().isBlank() == false) {
                    database_check_up_panel.add_to_checkups(date_text.getText(), id_zd, id_doc, id_worker);

                    date_text.setText("");
                    button_check.setEnabled(false);
                    healthcenter_list.setEnabled(false);
                    healthcenter_list.clearSelection();

                    doctor_combo.setEnabled(false);

                    doctor_combo.removeAllItems();

                    JOptionPane.showMessageDialog(null, "Check up added");
                }
                else
                    JOptionPane.showMessageDialog(null, "Date not in correct format");
            }
        });
        healthcenter_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //doctor_combo.removeAllItems();
                if(healthcenter_list.isSelectionEmpty() == false) {
                    id_zd = database_check_up_panel.returnzdid(healthcenter_list.getSelectedValue().toString());
                    doctors = database_check_up_panel.returndoctors(id_zd);

                    System.out.println(doctors[0]);

                    if (st == 0) {
                        for (String d_name : doctors) {
                            doctor_combo.addItem(d_name);
                        }

                        doctor_combo.setEnabled(true);
                        st++;
                    }
                }
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
                if(doctor_combo.isEnabled()) {
                    String name = doctors[doctor_combo.getSelectedIndex()];

                    String[] name_surname = name.split(" ");

                    id_doc = database_check_up_panel.returndoctorid(name_surname[0], name_surname[1], id_zd);

                    button_check.setEnabled(true);
                }
            }
        });
        check_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                edit_check.setEnabled(true);
                remove_check.setEnabled(true);
            }
        });
        employee_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id_worker = workers[employee_combo.getSelectedIndex()].id;

                checkups_list = database_checkcheckup.returncheckups(id_worker);

                update_list_check();

                check_list.setModel(list_checkups);
            }
        });
        emp_sett_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee_Settings_Panel.main(id_worker, id_p);
                frame.dispose();
            }
        });
        remove_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int checkid = checkups_list[check_list.getSelectedIndex()].id;

                database_checkcheckup.delete_checkup(checkid);

                checkups_list = database_checkcheckup.returncheckups(id_worker);

                update_list_check();

                check_list.setModel(list_checkups);

                JOptionPane.showMessageDialog(null, "Check up removed");
            }
        });
        places_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Places_panel.main();
            }
        });
        accountSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employer_settings_panel.main(id_p);
                frame.dispose();
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
