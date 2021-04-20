import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class Places_panel {
    private JTextField place_name_text;
    private JTextField place_num_text;
    private JButton updateButton;
    private JButton addButton;
    private JButton deleteButton;
    private static JFrame frame;
    private JPanel panel;
    private JScrollPane places_scroll;
    private DefaultListModel list = new DefaultListModel();
    private JList places_list = new JList();
    private places plac = new places();
    private String[] arrTown;
    private String townNum;
    private static int id_p;


    public void update_list()
    {
        places[] allTowns = database_places.getPlaces();
        list.removeAllElements();

        for (places item: allTowns) {
            list.addElement(item.Name + "  " + item.PostalNum);
        }
        places_list.setModel(list);
        places_scroll.setViewportView(places_list);
    }

    public Places_panel() {
        panel.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                update_list();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plac.Name = place_name_text.getText();
                plac.PostalNum= place_num_text.getText();
                database_places.addPlace(plac);
                update_list();
                place_name_text.setText("");
                place_num_text.setText("");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database_places.updatePlace(place_name_text.getText(), place_num_text.getText(), plac.Name, plac.PostalNum);
                update_list();
                place_name_text.setText("");
                place_num_text.setText("");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database_places.removePlace(plac);
                update_list();
                place_name_text.setText("");
                place_num_text.setText("");
            }
        });
        places_list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    townNum = (String) places_list.getSelectedValue();
                    if(places_list.getSelectedValue() == null)
                        System.out.println("Boli me kurac");
                    else {
                        arrTown = townNum.split("  ");
                        plac.Name = arrTown[0];
                        plac.PostalNum = arrTown[1];

                        place_name_text.setText(plac.Name);
                        place_num_text.setText(plac.PostalNum);
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
        frame = new JFrame("Places");
        frame.setContentPane(new Places_panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
