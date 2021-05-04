import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class edit_checkups {
    private JList list1;
    private JTextField date_text;
    private JComboBox doctor_combo;
    private JButton editButton;
    private JPanel panel;
    private JLabel dat;

    private String[] healthcenters;
    private DefaultListModel list = new DefaultListModel();
    private static int id_e;
    private static int id_p;
    private static int id_check;
    private static int id_zd;
    private static int id_doc;
    private static int st = 0;
    private String[] doctors;

    private static JFrame frame;

    private checkups check = new checkups();

    public void update()
    {
        healthcenters = database_check_up_panel.returnhealhcenters();

        list.removeAllElements();

        for (String name: healthcenters) {
            list.addElement(name);
        }

        list1.setModel(list);
    }


    public edit_checkups() {
        update();

        check = database_checkcheckup.returncheckup(id_check);

        date_text.setText(check.Datum_pregleda);
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editButton.setEnabled(false);

                id_zd = database_check_up_panel.returnzdid(list1.getSelectedValue().toString());
                doctors = database_check_up_panel.returndoctors(id_zd);

                if (st == 0) {
                    for (String d_name : doctors) {
                        doctor_combo.addItem(d_name);
                    }

                    doctor_combo.setEnabled(true);
                    st++;
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = doctors[doctor_combo.getSelectedIndex()];

                String[] name_surname = name.split(" ");

                id_doc = database_check_up_panel.returndoctorid(name_surname[0], name_surname[1], id_zd);

                check.id = id_check;
                check.Datum_pregleda = date_text.getText();
                check.Zd_id = id_zd;
                check.Emp_id = id_e;
                check.Doc_id = id_doc;

                database_checkcheckup.update_checkup(check);

                Check_up_panel.main(id_p);
                frame.dispose();
            }
        });
        doctor_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editButton.setEnabled(true);
            }
        });
    }

    public static void main(int id_w, int id_c, int id_pod) {
        id_e = id_w;
        id_check = id_c;
        id_p = id_pod;
        frame = new JFrame("Add employees to checkup");
        frame.setContentPane(new edit_checkups().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
