import javax.swing.*;
import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.util.Locale;

public class Graph_panel {
    private JPanel panel;
    private static JFrame frame;


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
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.setValue(80, "prva", "druga");
    dataset.setValue(45, "prva", "druga2");
    dataset.setValue(86, "prva", "druga3");
    dataset.setValue(30, "prva", "druga4");
    dataset.setValue(12, "prva", "druga5");
    dataset.setValue(65, "prva", "druga6");

    JFreeChart chart = ChartFactory.createBarChart("Title", "Category", "Value", dataset);
    CategoryPlot p = chart.getCategoryPlot();
    p.setRangeGridlinePaint(Color.black);
    ChartFrame frame = new ChartFrame("Results", chart);
    frame.pack();
    frame.setVisible(true);

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
