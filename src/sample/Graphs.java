package sample;

import javafx.scene.chart.XYChart;

import static java.lang.Math.*;

public class Graphs {
    private double[] X;
    private double[] Y;
    private double[] YEuler;
    private double[] YImprovedEuler;
    private double[] YRungeKutta;
    private double[] approxErrorEuler;
    private double[] approxErrorImprovedEuler;
    private double[] approxErrorRungeKutta;

    public Graphs (double x0, double y0, double xMax, int N){
        double h = (xMax - x0) / (N - 1);
        X = new double[N];
        X[0] = x0;
        for (int i = 1; i < N; i++) {
            X[i] = X[i - 1] + h;
        }
        computeExact(y0, h);
        computeEuler(h, y0);
        computeImprovedEuler(h, y0);
        computeRungeKutta(h, y0);
    }


    public double function (double x, double y){
        return ((-2)*y + 4 * x);
    }

    public void computeExact (double y0, double h){

        double C = (y0 - 2*X[0] + 1)*Math.exp(2*X[0]);
        Y = new double[X.length];
        // y = 2x + 1 + C*(e^-2x)

        for (int i =0; i < X.length; i++) {
            Y[i]=2*X[i] - 1 + C * Math.exp(-2*X[i]);

        }
    }

    public void computeEuler (double h, double y){
        YEuler = new double[X.length];
        approxErrorEuler = new double[X.length];
        YEuler[0] = y;
        approxErrorEuler[0] = 0;
        for (int i = 1; i < X.length; i++){
            YEuler [i] = YEuler[i-1] + h * function(X[i-1], YEuler[i-1]);
            approxErrorEuler[i] = abs(YEuler[i] - Y[i]);
        }
    }

    public void computeImprovedEuler (double h, double y){
        YImprovedEuler = new double[X.length];
        approxErrorImprovedEuler = new double[X.length];
        YImprovedEuler[0] = y;
        approxErrorImprovedEuler[0] = 0;
        for (int i = 1; i < X.length; i++){
            double xBuff = X[i-1] + h/2;
            double yBuff = YImprovedEuler[i-1] + h/2 * function(X[i-1], YImprovedEuler[i-1]);
            YImprovedEuler[i] = YImprovedEuler[i-1] + h * function(xBuff, yBuff);
            approxErrorImprovedEuler[i] = abs(YImprovedEuler[i] - Y[i]);
        }
    }

    public void computeRungeKutta(double h, double y){
        YRungeKutta = new double[X.length];
        approxErrorRungeKutta = new double[X.length];
        YRungeKutta[0] = y;
        approxErrorRungeKutta[0] = 0;
        for (int i = 1; i < X.length; i++){
            double koef1 = function( X[i-1], YRungeKutta[i-1]);
            double koef2 = function( X[i-1] + h/2, YRungeKutta[i-1] + h/2*koef1);
            double koef3 = function(X[i-1]+h/2, YRungeKutta[i-1]+h/2*koef2);
            double koef4 = function( X[i-1] + h, YRungeKutta[i-1]+ h*koef3);
            YRungeKutta[i] = YRungeKutta[i-1] + h/6*(koef1 + 2 * koef2 + 2*koef3 + koef4);
            approxErrorRungeKutta[i] = abs(YRungeKutta[i] - Y[i]);
        }

    }

    public double[] getApproxErrorEuler() {
        return approxErrorEuler;
    }

    public double[] getApproxErrorImprovedEuler() {
        return approxErrorImprovedEuler;
    }

    public double[] getApproxErrorRungeKutta() {
        return approxErrorRungeKutta;
    }

    public double[] getY() {
        return Y;
    }

    public double[] getYEuler() {
        return YEuler;
    }

    public double[] getYImprovedEuler() {
        return YImprovedEuler;
    }

    public double[] getYRungeKutta() {
        return YRungeKutta;
    }

    public double[] getX() {
        return X;
    }
}
