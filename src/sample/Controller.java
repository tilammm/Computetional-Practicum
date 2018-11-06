package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Controller {

    /**
     * Options to on/off particular graph
     */
    @FXML
    public RadioButton rBtnExact;
    @FXML
    public RadioButton rBtnEuler;
    @FXML
    public RadioButton rBtnImprovedEuler;
    @FXML
    public RadioButton rBtnRungeKutta;

    /**
     * Input data
     */
    @FXML
    private TextField fieldX0;
    @FXML
    private TextField fieldY0;
    @FXML
    private TextField fieldX;
    @FXML
    private TextField fieldN;

    /**
     * Graphs
     */
    @FXML
    private LineChart<Number, Number> lineChartGraphs;
    @FXML
    private LineChart<Number, Number> lineChartApproxErrorGraphs;
    @FXML
    private LineChart<Number, Number> lineChartTruncationErrorGraphs;

    /**
     * Series of all graphs
     */
    private GraphsSeries graphsSeries;

    @FXML
    private Rectangle rectangle;
    private double pressedX;
    private double pressedY;


    @FXML
    private void initialize() {
        drawGraphs();

        rectangle.setHeight(0);
        rectangle.setWidth(0);

    }

    private void setAutoRanges() {
        lineChartGraphs.getYAxis().setAutoRanging(true);
        lineChartGraphs.getXAxis().setAutoRanging(true);
        lineChartApproxErrorGraphs.getYAxis().setAutoRanging(true);
        lineChartApproxErrorGraphs.getXAxis().setAutoRanging(true);
        lineChartTruncationErrorGraphs.getYAxis().setAutoRanging(true);
        lineChartTruncationErrorGraphs.getXAxis().setAutoRanging(true);
    }

    private void drawGraphs() {
        setAutoRanges();
        try {
            double x0 = Double.parseDouble(fieldX0.getText());
            double y0 = Double.parseDouble(fieldY0.getText());
            double X = Double.parseDouble(fieldX.getText());
            int N = Integer.parseInt(fieldN.getText());

            if (x0 > X) throw new NumberFormatException("x0 > X");
            if (N < 2) throw new NumberFormatException("N < 2");

            graphsSeries = new GraphsSeries(x0, y0, X, N);

            lineChartGraphs.getData().clear();
            lineChartApproxErrorGraphs.getData().clear();
            lineChartTruncationErrorGraphs.getData().clear();

            if (rBtnExact.isSelected())
                lineChartGraphs.getData().add(graphsSeries.getExactGraph());
            if (rBtnEuler.isSelected()) {
                lineChartGraphs.getData().add(graphsSeries.getEulerGraph());
                lineChartApproxErrorGraphs.getData().add(graphsSeries.getEulerApproxErrorGraph());
                lineChartTruncationErrorGraphs.getData().add(graphsSeries.getEulerTruncationErrorGraph());
            }
            if (rBtnImprovedEuler.isSelected()) {
                lineChartGraphs.getData().add(graphsSeries.getImprovedEulerGraph());
                lineChartApproxErrorGraphs.getData().add(graphsSeries.getImprovedEulerApproxErrorGraph());
                lineChartTruncationErrorGraphs.getData().add(graphsSeries.getImprovedEulerTruncationErrorGraph());
            }
            if (rBtnRungeKutta.isSelected()) {
                lineChartGraphs.getData().add(graphsSeries.getRungeKuttaGraph());
                lineChartApproxErrorGraphs.getData().add(graphsSeries.getRungeKuttaApproxErrorGraph());
                lineChartTruncationErrorGraphs.getData().add(graphsSeries.getRungeKuttaTruncationErrorGraph());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void drawGraphs(ActionEvent actionEvent) {
        drawGraphs();
    }

    public void showExactGraph(ActionEvent actionEvent) {
        setAutoRanges();
        if (rBtnExact.isSelected()) {
            if (lineChartGraphs.getData().contains(graphsSeries.getExactGraph())) return;
            lineChartGraphs.getData().add(graphsSeries.getExactGraph());
        } else {
            if (!lineChartGraphs.getData().contains(graphsSeries.getExactGraph())) return;
            lineChartGraphs.getData().remove(graphsSeries.getExactGraph());
        }
    }

    public void showEulerGraph(ActionEvent actionEvent) {
        setAutoRanges();
        if (rBtnEuler.isSelected()) {
            lineChartGraphs.getData().add(graphsSeries.getEulerGraph());
            lineChartApproxErrorGraphs.getData().add(graphsSeries.getEulerApproxErrorGraph());
            lineChartTruncationErrorGraphs.getData().add(graphsSeries.getEulerTruncationErrorGraph());
        } else {
            lineChartGraphs.getData().remove(graphsSeries.getEulerGraph());
            lineChartApproxErrorGraphs.getData().remove(graphsSeries.getEulerApproxErrorGraph());
            lineChartTruncationErrorGraphs.getData().remove(graphsSeries.getEulerTruncationErrorGraph());
        }
    }

    public void showImprovedEulerGraph(ActionEvent actionEvent) {
        setAutoRanges();
        if (rBtnImprovedEuler.isSelected()) {
            lineChartGraphs.getData().add(graphsSeries.getImprovedEulerGraph());
            lineChartApproxErrorGraphs.getData().add(graphsSeries.getImprovedEulerApproxErrorGraph());
            lineChartTruncationErrorGraphs.getData().add(graphsSeries.getImprovedEulerTruncationErrorGraph());
        } else {
            lineChartGraphs.getData().remove(graphsSeries.getImprovedEulerGraph());
            lineChartApproxErrorGraphs.getData().remove(graphsSeries.getImprovedEulerApproxErrorGraph());
            lineChartTruncationErrorGraphs.getData().remove(graphsSeries.getImprovedEulerTruncationErrorGraph());
        }
    }

    public void showRungeKuttaGraph(ActionEvent actionEvent) {
        setAutoRanges();
        if (rBtnRungeKutta.isSelected()) {
            lineChartGraphs.getData().add(graphsSeries.getRungeKuttaGraph());
            lineChartApproxErrorGraphs.getData().add(graphsSeries.getRungeKuttaApproxErrorGraph());
            lineChartTruncationErrorGraphs.getData().add(graphsSeries.getRungeKuttaTruncationErrorGraph());
        } else {
            lineChartGraphs.getData().remove(graphsSeries.getRungeKuttaGraph());
            lineChartApproxErrorGraphs.getData().remove(graphsSeries.getRungeKuttaApproxErrorGraph());
            lineChartTruncationErrorGraphs.getData().remove(graphsSeries.getRungeKuttaTruncationErrorGraph());
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {
        pressedX = mouseEvent.getX();
        pressedY = mouseEvent.getY();
        rectangle.setX(pressedX);
        rectangle.setY(pressedY);
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        rectangle.setX(Math.min(pressedX, mouseEvent.getX()));
        rectangle.setY(Math.min(pressedY, mouseEvent.getY()));
        rectangle.setWidth(Math.abs(mouseEvent.getX() - pressedX));
        rectangle.setHeight(Math.abs(mouseEvent.getY() - pressedY));
    }

    public void mouseReleasedGraphs(MouseEvent mouseEvent) {
        lineChartZoom(lineChartGraphs, mouseEvent);
    }

    public void mouseReleasedApproxError(MouseEvent mouseEvent) {
        lineChartZoom(lineChartApproxErrorGraphs, mouseEvent);
    }

    public void mouseReleasedTruncError(MouseEvent mouseEvent) {
        lineChartZoom(lineChartTruncationErrorGraphs, mouseEvent);
    }

    private void lineChartZoom(LineChart<Number, Number> lineChart, MouseEvent mouseEvent) {
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        rectangle.setWidth(0);
        rectangle.setHeight(0);

        double minX = Math.min(pressedX, mouseEvent.getX()) - 55;
        double maxX = Math.max(pressedX, mouseEvent.getX()) - 55;
        double minY = Math.min(pressedY, mouseEvent.getY()) - 34;
        double maxY = Math.max(pressedY, mouseEvent.getY()) - 34;

        double xLowerBound = xAxis.getLowerBound() + (xAxis.getUpperBound() - xAxis.getLowerBound())
                * (minX / xAxis.getWidth());
        double xUpperBound = xAxis.getUpperBound() - (xAxis.getUpperBound() - xAxis.getLowerBound())
                * (1 - maxX / xAxis.getWidth());
        double yLowerBound = yAxis.getLowerBound() + (yAxis.getUpperBound() - yAxis.getLowerBound())
                * (1 - maxY / yAxis.getHeight());
        double yUpperBound = yAxis.getUpperBound() - (yAxis.getUpperBound() - yAxis.getLowerBound())
                * (minY / yAxis.getHeight());

        xAxis.setLowerBound(xLowerBound);
        xAxis.setUpperBound(xUpperBound);
        yAxis.setLowerBound(yLowerBound);
        yAxis.setUpperBound(yUpperBound);
    }
}
