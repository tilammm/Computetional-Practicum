package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {
// initial values
    @FXML
    private TextField x0Field;
    @FXML
    private TextField y0Field;
    @FXML
    private TextField XMaxField;
    @FXML
    private TextField NField;

//plots
    @FXML
    private LineChart<Number, Number>  GraphPlot;
    @FXML
    private LineChart<Number, Number>  TrunctionPlot;
    @FXML
    private LineChart<Number, Number>  ApproximationPlot;


    @FXML
    public RadioButton exactLine;
    @FXML
    public RadioButton eulerLine;
    @FXML
    public RadioButton improvedEulerLine;
    @FXML
    public RadioButton rungeKuttaLine;

    private PlotSeries plotSeries;

    @FXML
    private void initialize() {
        drawPlot();
    }

    private void drawPlot (){

        GraphPlot.getYAxis().setAutoRanging(true);
        GraphPlot.getXAxis().setAutoRanging(true);

        TrunctionPlot.getYAxis().setAutoRanging(true);
        TrunctionPlot.getXAxis().setAutoRanging(true);

        ApproximationPlot.getXAxis().setAutoRanging(true);
        ApproximationPlot.getYAxis().setAutoRanging(true);

        try {
            double x0 = Double.parseDouble(x0Field.getText());
            double y0 = Double.parseDouble(y0Field.getText());
            double XMax = Double.parseDouble(XMaxField.getText());
            int N = Integer.parseInt(NField.getText());

            if (x0 > XMax) throw new NumberFormatException("x0 > X");
            if (N < 2) throw new NumberFormatException("N < 2");

            plotSeries = new PlotSeries(x0, y0, XMax, N);

            if (exactLine.isSelected())
                GraphPlot.getData().add(plotSeries.getExactPlot());
            if (eulerLine.isSelected()) {
                GraphPlot.getData().add(plotSeries.getEulerPlot());
                TrunctionPlot.getData().add(plotSeries.getEulerTruncationErrorPlot());
                ApproximationPlot.getData().add(plotSeries.getEulerApproxErrorPlot());
            }
            if (improvedEulerLine.isSelected()) {
                GraphPlot.getData().add(plotSeries.getImprovedEulerPlot());
                TrunctionPlot.getData().add(plotSeries.getImprovedEulerTruncationErrorPlot());
                ApproximationPlot.getData().add(plotSeries.getImprovedEulerApproxErrorPlot());
            }
            if (rungeKuttaLine.isSelected()) {
                GraphPlot.getData().add(plotSeries.getRungeKuttaPlot());
                TrunctionPlot.getData().add(plotSeries.getRungeKuttaTruncationErrorPlot());
                ApproximationPlot.getData().add(plotSeries.getRungeKuttaApproxErrorGPlot());
            }




        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    public void drawPlot (ActionEvent actionEvent) {
        GraphPlot.getData().clear();
        TrunctionPlot.getData().clear();
        ApproximationPlot.getData().clear();
        drawPlot();
    }

}
