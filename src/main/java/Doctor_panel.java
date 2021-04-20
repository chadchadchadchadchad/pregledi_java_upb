import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Doctor_panel {
    private JTextField name_text;
    private JTextField lastName_text;
    private JTextField phone_text;
    private JTextField email_text;
    private JButton addButton;
    private JPanel panel;
    private JList doctors_list;
    private JButton updateButton;
    private JButton deleteButton;
    private JComboBox centre_combo;
    private JScrollPane doctors_scroll;
    private static int id_p;
    private static JFrame frame;
    private DefaultListModel list = new DefaultListModel();
    private doctors[] allDoctors;
    private String[] arrTown;
    private String townNum;
    private doctors doctor = new doctors();


    public void update_list()
    {
        allDoctors = database_doctors.getDoctors();
        list.removeAllElements();

        for (doctors item: allDoctors) {
            list.addElement(item.Name + "  " + item.Lastname + "  " + item.HealthCentre);
        }
        doctors_list.setModel(list);
        doctors_scroll.setViewportView(doctors_list);
    }

    public void update_combo()
    {
        healthCentres[] allCentres = database_doctors.getCentres();
        centre_combo.removeAllItems();

        for (healthCentres item: allCentres) {
            centre_combo.addItem(item.Name);
        }
    }

    public Doctor_panel() {
        update_combo();
        update_list();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor.Name = name_text.getText();
                doctor.Lastname = lastName_text.getText();
                doctor.PhoneNumber = phone_text.getText();
                doctor.Email = email_text.getText();
                doctor.HealthCentre = centre_combo.getSelectedItem().toString();
                database_doctors.addDoctor(doctor);
                update_list();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor.Name = name_text.getText();
                doctor.Lastname = lastName_text.getText();
                doctor.PhoneNumber = phone_text.getText();
                doctor.Email = email_text.getText();
                doctor.HealthCentre = centre_combo.getSelectedItem().toString();
                database_doctors.updateDoctor(doctor);
                update_list();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database_doctors.deleteDoctor(doctor);
                update_list();
            }
        });
        doctors_list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    townNum = (String) doctors_list.getSelectedValue();
                    if(doctors_list.getSelectedValue() == null)
                        System.out.println("Boli me kurac");
                    else {
                        doctor = allDoctors[doctors_list.getSelectedIndex()];

                        name_text.setText(doctor.Name);
                        lastName_text.setText(doctor.Lastname);
                        phone_text.setText(doctor.PhoneNumber);
                        email_text.setText(doctor.Email);
                        centre_combo.setSelectedItem(doctor.HealthCentre);
                    }

                }
            }
        });
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Check_up_panel.main(id_p);
            }
        });
    }


    public static void main(int idp) {
        id_p = idp;
        frame = new JFrame("Add employees to checkup");
        frame.setContentPane(new Doctor_panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
