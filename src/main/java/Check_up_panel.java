import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Check_up_panel {

    private JComboBox worker_combo;
    private JButton button_check;
    private JPanel panel;
    private JList list_check;
    private static int id_p;
    private int id_worker;

    public Check_up_panel() {
        String[] workers = dbconnect.returnworkers(id_p);
        DefaultListModel list = new DefaultListModel();

        System.out.println( "IDP = " + id_p );

        System.out.println( "NAME = " + workers[0] );


        for (String name: workers) {
            worker_combo.addItem(name);
            list.addElement(name);
        }

        worker_combo.setSelectedItem(null);

        worker_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list_check.setEnabled(true);
                String name = workers[worker_combo.getSelectedIndex()];

                String[]name_surname = name.split(" ");

                id_worker = dbconnect.returnworkerid(name_surname[0], name_surname[1], id_p);
                System.out.println("ID: " + id_worker);

                list_check.setModel(list);
            }
        });

        button_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_check.setEnabled(false);
                list_check.setEnabled(false);

                worker_combo.setSelectedItem(null);
            }
        });

        /*list_check.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                button_check.setEnabled(true);
            }
        });*/
        list_check.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                button_check.setEnabled(true);
            }
        });
    }

    public static void main(int idp) {
        id_p = idp;
        JFrame frame = new JFrame("Checkup neki al neki");
        frame.setContentPane(new Check_up_panel().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
