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
    private DefaultListModel list = new DefaultListModel();
    private String[] workers;
    private static JFrame frame;

    public void update()
    {
        list.clear();

        workers = dbconnect.returnworkers(id_p);

        for (String name: workers) {
            worker_combo.addItem(name);
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

                id_worker = dbconnect.returnworkerid(name_surname[0], name_surname[1], id_p);
                System.out.println("ID: " + id_worker);

                healthcenter_list.setModel(list);
            }
        });

        button_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_check.setEnabled(false);
                healthcenter_list.setEnabled(false);

                worker_combo.setSelectedItem(null);
            }
        });

        /*list_check.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                button_check.setEnabled(true);
            }
        });*/
        healthcenter_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                button_check.setEnabled(true);
            }
        });
        add_em_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add_employee_form.main(id_p);
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
