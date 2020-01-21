package work1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
//참조 : http://zetcode.com/java/jfreechart/
public class LineChartEx extends JFrame {
	
	private String chartName;
	private String xName, yName;
	ArrayList<Double> yList;
	ArrayList<Double> xList;
    public LineChartEx() {
    	this(null,null,null,null,null);
    }

    public LineChartEx(String chartName, String xName, String yName,ArrayList<Double> xList,ArrayList<Double> yList) {
		this.chartName = chartName;
		this.xName = xName;
		this.yName = yName;
		this.xList = xList;
		this.yList = yList;
		initUI();
	}

	private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

        XYSeries series = new XYSeries("");
        if(xList == null) for(int i=0; i<yList.size(); i++) series.add(i,yList.get(i));
        else for(int i=0; i<xList.size(); i++) series.add(xList.get(i),yList.get(i));

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                chartName, 
                xName, 
                yName, 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(chartName,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }

    
}