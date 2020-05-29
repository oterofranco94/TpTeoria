package Clases;



import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;

public class Histograma extends ApplicationFrame {

    public Histograma(String title, double [] vector) {
        
        super(title);
        JPanel chartPanel = crearPanel(title, vector);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        super.setContentPane(chartPanel);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    
       
}

    private static IntervalXYDataset crearDataset( double [] vector) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Intensidad", vector, 26);
        
  return dataset;
}

    private static JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(null, "Intensidad", "Cantida de Pixeles", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
       // renderer.setDrawBarOutline(false);
  
return chart;
}
    public static JPanel crearPanel(String name, double [] vector) {
        JFreeChart chart = crearChart(crearDataset(vector));
        return new ChartPanel(chart);
}
}

 