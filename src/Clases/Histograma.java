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

public class Histograma extends javax.swing.JDialog {
    public String title;

    public Histograma(String title, double [] vector) {
        this.title=title;
        JPanel chartPanel = crearPanel(title, vector);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        super.setContentPane(chartPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

    private IntervalXYDataset crearDataset( double [] vector) {
        HistogramDataset dataset = new HistogramDataset();
<<<<<<< HEAD
        dataset.addSeries("Intensidad", vector, 16);
      
=======
        dataset.addSeries("Intensidad", vector, 26);
>>>>>>> master
        return dataset;
}

    private JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(this.title, "Intensidad", "Cantida de Pixeles", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
       // renderer.setDrawBarOutline(false);
  
return chart;
}
    public JPanel crearPanel(String name, double [] vector) {
        JFreeChart chart = crearChart(crearDataset(vector));
        return new ChartPanel(chart);
}
}

 