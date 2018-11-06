package sample;

import javafx.scene.chart.XYChart.*;

public class GraphsSeries {

    private Series<Number, Number> exactGraph;
    private Series<Number, Number> eulerGraph;
    private Series<Number, Number> improvedEulerGraph;
    private Series<Number, Number> rungeKuttaGraph;

    private Series<Number, Number> eulerApproxErrorGraph;
    private Series<Number, Number> improvedEulerApproxErrorGraph;
    private Series<Number, Number> rungeKuttaApproxErrorGraph;

    private Series<Number, Number> eulerTruncationErrorGraph;
    private Series<Number, Number> improvedEulerTruncationErrorGraph;
    private Series<Number, Number> rungeKuttaTruncationErrorGraph;

    private Graphs graphs;

    public GraphsSeries(double x0, double y0, double X, int N) {
        graphs = new Graphs(x0, y0, X, N);

        exactGraph = new Series<Number, Number>();
        exactGraph.setName("Exact");
        addPoints(graphs.getX(), graphs.getY(), exactGraph);

        eulerGraph = new Series<Number, Number>();
        eulerGraph.setName("Euler");
        addPoints(graphs.getX(), graphs.getYEuler(), eulerGraph);

        improvedEulerGraph = new Series<Number, Number>();
        improvedEulerGraph.setName("Improved euler");
        addPoints(graphs.getX(), graphs.getYImprovedEuler(), improvedEulerGraph);

        rungeKuttaGraph = new Series<Number, Number>();
        rungeKuttaGraph.setName("Runge-Kutta");
        addPoints(graphs.getX(), graphs.getYRungeKutta(), rungeKuttaGraph);

        eulerApproxErrorGraph = new Series<Number, Number>();
        eulerApproxErrorGraph.setName("Euler");
        addPoints(graphs.getX(), graphs.getApproxErrorEuler(), eulerApproxErrorGraph);

        improvedEulerApproxErrorGraph = new Series<Number, Number>();
        improvedEulerApproxErrorGraph.setName("Improved euler");
        addPoints(graphs.getX(), graphs.getApproxErrorImprovedEuler(), improvedEulerApproxErrorGraph);

        rungeKuttaApproxErrorGraph = new Series<Number, Number>();
        rungeKuttaApproxErrorGraph.setName("Runge-Kutta");
        addPoints(graphs.getX(), graphs.getApproxErrorRungeKutta(), rungeKuttaApproxErrorGraph);

        truncationErrorCompute(x0, y0, X, N);
    }

    public Series<Number, Number> getExactGraph() {
        return exactGraph;
    }

    public Series<Number, Number> getEulerGraph() {
        return eulerGraph;
    }

    public Series<Number, Number> getImprovedEulerGraph() {
        return improvedEulerGraph;
    }

    public Series<Number, Number> getRungeKuttaGraph() {
        return rungeKuttaGraph;
    }

    public Series<Number, Number> getEulerApproxErrorGraph() {
        return eulerApproxErrorGraph;
    }

    public Series<Number, Number> getImprovedEulerApproxErrorGraph() {
        return improvedEulerApproxErrorGraph;
    }

    public Series<Number, Number> getRungeKuttaApproxErrorGraph() {
        return rungeKuttaApproxErrorGraph;
    }

    public Series<Number, Number> getEulerTruncationErrorGraph() {
        return eulerTruncationErrorGraph;
    }

    public Series<Number, Number> getImprovedEulerTruncationErrorGraph() {
        return improvedEulerTruncationErrorGraph;
    }

    public Series<Number, Number> getRungeKuttaTruncationErrorGraph() {
        return rungeKuttaTruncationErrorGraph;
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

        eulerTruncationErrorGraph = new Series<Number, Number>();
        eulerTruncationErrorGraph.setName("Euler");
        addPoints(n, eulerTruncationError, eulerTruncationErrorGraph);

        improvedEulerTruncationErrorGraph = new Series<Number, Number>();
        improvedEulerTruncationErrorGraph.setName("Improved euler");
        addPoints(n, improvedEulerTruncationError, improvedEulerTruncationErrorGraph);

        rungeKuttaTruncationErrorGraph = new Series<Number, Number>();
        rungeKuttaTruncationErrorGraph.setName("Runge-Kutta");
        addPoints(n, rungeKuttaTruncationError, rungeKuttaTruncationErrorGraph);
    }

    private void addPoints(double[] x, double[] y, Series<Number, Number> graph) {
        for (int i = 0; i < x.length; i++) {
            graph.getData().add(new Data<Number, Number>(x[i], y[i]));
        }
    }

    private double max(double[] arr) {
        double ans = arr[0];
        for (double val : arr) {
            ans = Math.max(ans, val);
        }
        return ans;
    }

    private double min(double[] arr) {
        double ans = arr[0];
        for (double val : arr) {
            ans = Math.min(ans, val);
        }
        return ans;
    }
}
