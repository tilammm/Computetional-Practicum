package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

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



}
