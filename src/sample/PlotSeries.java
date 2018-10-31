package sample;

import javafx.scene.chart.XYChart;

public class PlotSeries {
    private XYChart.Series<Number, Number> exactPlot;
    private XYChart.Series<Number, Number> eulerPlot;
    private XYChart.Series<Number, Number> improvedEulerPlot;
    private XYChart.Series<Number, Number> rungeKuttaPlot;

    private XYChart.Series<Number, Number> eulerTruncationErrorPlot;
    private XYChart.Series<Number, Number> improvedEulerTruncationErrorPlot;
    private XYChart.Series<Number, Number> rungeKuttaTruncationErrorPlot;

    private XYChart.Series<Number, Number> eulerApproxErrorPlot;
    private XYChart.Series<Number, Number> improvedEulerApproxErrorPlot;
    private XYChart.Series<Number, Number> rungeKuttaApproxErrorGPlot;

    private Graphs graphs;

    public PlotSeries (double x0, double y0, double XMax, int N){
        graphs = new Graphs(x0, y0, XMax, N);

        exactPlot = new XYChart.Series<Number, Number>();
        eulerPlot = new XYChart.Series<Number, Number>();
        improvedEulerPlot = new XYChart.Series<Number, Number>();
        rungeKuttaPlot = new XYChart.Series<Number, Number>();

        eulerTruncationErrorPlot = new XYChart.Series<Number, Number>();
        improvedEulerTruncationErrorPlot = new XYChart.Series<Number, Number>();
        rungeKuttaTruncationErrorPlot = new XYChart.Series<Number, Number>();

        eulerApproxErrorPlot = new XYChart.Series<Number, Number>();
        improvedEulerApproxErrorPlot = new XYChart.Series<Number, Number>();
        rungeKuttaApproxErrorGPlot = new XYChart.Series<Number, Number>();

        }


}
