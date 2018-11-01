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

        exactPlot.setName("Exact");
        addPoints(graphs.getX(), graphs.getY(), exactPlot);

        eulerPlot.setName("Euler");
        addPoints(graphs.getX(), graphs.getYEuler(), eulerPlot );

        improvedEulerPlot.setName("Improved Euler");
        addPoints(graphs.getX(), graphs.getYImprovedEuler(), improvedEulerPlot );

        rungeKuttaPlot.setName("Runge-Kutta");
        addPoints(graphs.getX(), graphs.getYRungeKutta(), rungeKuttaPlot);

        eulerApproxErrorPlot.setName("Euler");
        addPoints(graphs.getX(), graphs.getApproxErrorEuler(), eulerApproxErrorPlot);

        improvedEulerApproxErrorPlot.setName("Improved Euler");
        addPoints(graphs.getX(), graphs.getApproxErrorEuler(), improvedEulerPlot );

        rungeKuttaApproxErrorGPlot.setName("Runge-Kutta");
        addPoints(graphs.getX(), graphs.getApproxErrorRungeKutta(), rungeKuttaApproxErrorGPlot );

        truncationErrorCompute(x0, y0, XMax, N);

        }

    private void addPoints(double[] x, double[] y, XYChart.Series<Number, Number> graph) {
        for (int i = 0; i < x.length; i++) {
            graph.getData().add(new XYChart.Data<Number, Number>(x[i], y[i]));
        }
    }

    private void truncationErrorCompute(double x0, double y0, double X, int N) {
        double[] n = new double[N - 1]; // values [2; N]

        double[] eulerTruncationError = new double[N - 1];
        double[] improvedEulerTruncationError = new double[N - 1];
        double[] rungeKuttaTruncationError = new double[N - 1];

        for (int i = 0; i < N - 1; i++) {
            n[i] = i + 2;
        }

        for (int i = 0; i < n.length; i++) {
            Graphs graphs = new Graphs(x0, y0, X, (int)n[i]);
            eulerTruncationError[i] = max(graphs.getApproxErrorEuler());
            improvedEulerTruncationError[i] = max(graphs.getApproxErrorImprovedEuler());
            rungeKuttaTruncationError[i] = max(graphs.getApproxErrorRungeKutta());
        }

        eulerTruncationErrorPlot = new XYChart.Series<Number, Number>();
        eulerTruncationErrorPlot.setName("Euler");
        addPoints(n, eulerTruncationError, eulerTruncationErrorPlot);

        improvedEulerTruncationErrorPlot = new XYChart.Series<Number, Number>();
        improvedEulerTruncationErrorPlot.setName("Improved euler");
        addPoints(n, improvedEulerTruncationError, improvedEulerTruncationErrorPlot);

        rungeKuttaTruncationErrorPlot = new XYChart.Series<Number, Number>();
        rungeKuttaTruncationErrorPlot.setName("Runge-Kutta");
        addPoints(n, rungeKuttaTruncationError, rungeKuttaTruncationErrorPlot);
    }

    private double max(double[] arr) {
        double ans = arr[0];
        for (double val : arr) {
            ans = Math.max(ans, val);
        }
        return ans;
    }

    public XYChart.Series<Number, Number> getEulerPlot() {
        return eulerPlot;
    }

    public XYChart.Series<Number, Number> getExactPlot() {
        return exactPlot;
    }

    public XYChart.Series<Number, Number> getImprovedEulerPlot() {
        return improvedEulerPlot;
    }

    public XYChart.Series<Number, Number> getRungeKuttaPlot() {
        return rungeKuttaPlot;
    }

    public XYChart.Series<Number, Number> getEulerApproxErrorPlot() {
        return eulerApproxErrorPlot;
    }

    public XYChart.Series<Number, Number> getImprovedEulerApproxErrorPlot() {
        return improvedEulerApproxErrorPlot;
    }

    public XYChart.Series<Number, Number> getRungeKuttaApproxErrorGPlot() {
        return rungeKuttaApproxErrorGPlot;
    }

    public XYChart.Series<Number, Number> getEulerTruncationErrorPlot() {
        return eulerTruncationErrorPlot;
    }

    public XYChart.Series<Number, Number> getImprovedEulerTruncationErrorPlot() {
        return improvedEulerTruncationErrorPlot;
    }

    public XYChart.Series<Number, Number> getRungeKuttaTruncationErrorPlot() {
        return rungeKuttaTruncationErrorPlot;
    }
}
