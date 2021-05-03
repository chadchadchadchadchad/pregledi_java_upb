import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graph_panel {
    private JPanel panel;
    private JTextField textField1;
    private JButton showChartButton;
    private JTextField yeartext2;
    private static JFrame frame;
    private charts chart = new charts();
    private  int nums;


public Graph_panel() {

    /*XYDataset ds = createDataset();
    JFreeChart chart = ChartFactory.createXYLineChart("Test Chart","x", "y", ds, PlotOrientation.VERTICAL, true, true,false);

    ChartFrame frame = new ChartFrame("Results", chart);
    frame.pack();
    frame.setVisible(true);
}

    private XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

        ds.addSeries("series1", data);

        return ds;
    }*/



    showChartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int yearS = Integer.parseInt(textField1.getText());
            int year2 = Integer.parseInt(yeartext2.getText());


            nums = database_graph_data.getNumOfChecks(chart);

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(nums, "nek value", yearS);


            JFreeChart chart = ChartFactory.createBarChart("Number of checkups", "Year", "Number of checkups", dataset);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);

            ChartFrame frame = new ChartFrame("Results", chart);
            frame.pack();
            frame.setVisible(true);
        }
    });
    textField1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(textField1.getText().length() == 4)
                showChartButton.setEnabled(true);
        }
    });
}


    public static void main() {
        frame = new JFrame("Employee checkup graph");
        frame.setContentPane(new Graph_panel().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(900,600);
        frame.setVisible(true);
    }
}
