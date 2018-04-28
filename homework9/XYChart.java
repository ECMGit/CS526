package hw9;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * this class implement the graph of comparing each sorting method running time
 * dataset combined with many series
 * each series show a line of each sorting method
 */
public class XYChart extends JComponent {
    private XYSeriesCollection dataset = new XYSeriesCollection();


    public XYChart() throws HeadlessException {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Comparing Time Complexity", // Title
                "input-size/K", // x-axis Label
                "Elapsed time/ms", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
        // add it to our application
        setLayout(new BorderLayout());
        add("North",new JScrollPane(chartPanel));
        setVisible(true);
    }


    /*
    draw a new line
     */
    public void addLine(String[]X, String[]Y){
        XYSeries series = new XYSeries(Y[0]);
        for(int i =1;i<X.length;i++){
            series.add(Double.valueOf(X[i])/1000,Double.valueOf(Y[i]));
        }
        //add this line into dataset
        this.dataset.addSeries(series);
    }
}
