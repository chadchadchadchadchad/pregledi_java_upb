import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Health_centre_management_panel {
    private JList places_list;
    private DefaultListModel list = new DefaultListModel();
    private JPanel panel;
    private JButton addButton;
    private JTextField name_text;
    private JTextField adress_text;
    private JTextField phone_text;
    private JComboBox place_combo;
    private JScrollPane places_scroll;
    private JButton updateButton;
    private JButton deleteButton;
    private static JFrame frame;
    private static int id_p;
    private healthCentres centre = new healthCentres();
    private String[] arrTown;
    private String townNum;
    private String name;
    private String adress;
    private healthCentres[] allCenters;

    public void update_list()
    {
        allCenters = database_manage_health_centre.getCentres();
        list.removeAllElements();

        for (healthCentres item: allCenters) {
            list.addElement(item.Name + "  " + item.Place + "  " + item.Adress + "  " + item.PhoneNum);
        }
        places_list.setModel(list);
        places_scroll.setViewportView(places_list);
    }
    public void update_combo()
    {
        healthCentres[] allTowns = database_manage_health_centre.getPlaces();
        place_combo.removeAllItems();

        for (healthCentres item: allTowns) {
            place_combo.addItem(item.Place);
        }
    }

    public Health_centre_management_panel() {
        update_list();
        update_combo();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centre.Name = name_text.getText();
                centre.Adress = adress_text.getText();
                centre.PhoneNum = phone_text.getText();
                centre.Place = place_combo.getSelectedItem().toString();
                database_manage_health_centre.addCentre(centre);
                JOptionPane.showMessageDialog(null, "Centre added successfully.");
                Check_up_panel.main(id_p);
                frame.dispose();
            }
        });
        places_list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    townNum = (String) places_list.getSelectedValue();
                    if(places_list.getSelectedValue() == null)
                        System.out.println("Boli me kurac");
                    else {
                        centre = allCenters[places_list.getSelectedIndex()];

                        name_text.setText(centre.Name);
                        adress_text.setText(centre.Adress);
                        phone_text.setText(centre.PhoneNum);
                        place_combo.setSelectedItem(centre.Place);
                    }

                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centre.Name = name_text.getText();
                centre.Adress = adress_text.getText();
                centre.PhoneNum = phone_text.getText();
                centre.Place = place_combo.getSelectedItem().toString();
                database_manage_health_centre.updateCentre(centre);
                update_list();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database_manage_health_centre.deleteCentre(centre.Id);
                update_list();
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
        frame = new JFrame("Health centre management");
        frame.setContentPane(new Health_centre_management_panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
