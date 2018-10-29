package sample;

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
    }


    public double function (double x, double y){
        return (y*4*cos(x) - y*tan(x));
    }

    public void computeExact (double y0, double h){
        double C = (3*sin(X[0])*pow(cos(X[0]), 2) + 1/pow(y0, 3))/pow(cos(X[0]), 3);
        Y = new double[X.length];
        for (int i= 0; i < X.length; i++) {

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
        approxErrorImprovedEuler = new double[X.length];
        YRungeKutta[0] = y;
        approxErrorRungeKutta[0] = 0;
        for (int i = 1; i < X.length; i++){
            double koef1 = function( X[i-1], YRungeKutta[i-1]);
            double koef2 = function( X[i-1] + h/2, Y[i-1] + h/2*koef1);
            double koef3 = function(X[i-1]+h/2, Y[i-1]+h/2*koef2);
            double koef4 = function( X[i-1] + h, Y[i-1]+ h*koef3);
            YRungeKutta[i] = YRungeKutta[i-1] + h/6*(koef1 + 2 * koef2 + 2*koef3 + koef4);
            approxErrorRungeKutta[i] = abs(YRungeKutta[i] - Y[i]);
        }
    }



}
