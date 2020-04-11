/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lineas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import static java.lang.Math.*;
import java.util.LinkedList;
import javafx.util.Pair;

/**
 *
 * @author rodrigo
 */
public class Coordenadas {

    static double sumatoria(double x, int iter) {
        if (iter == 0) {
            return 0.0;
        }
        double suma = 0.0, parcial, coef = 3, argu = 3;
        for (int i = 0; i < iter; i++) {
            parcial = ((1 / coef) * sin(argu * x));
            suma = suma + parcial;
            coef = coef + 2;
            argu = argu + 2;
        }
        return suma;
    }

    static int coordenada(double y) {
        double conver = ((300 * (y + 1.4)) / 2.8) + 150;
        double coor = 150 - conver;
        return (int) coor;
    }

    public static void main(String args[]) {
        LinkedList<LinkedList<Pair>> coord = new LinkedList<>();
        double tamano = PI / 400, y, x;
        int cx, cy;
        for (int j = 0; j < 10; j++) {
            LinkedList<Pair> l = new LinkedList<>();
            cx = 0;
            for (int i = 0; i <= 800; i++) {
                x = ((-PI) + (tamano * i));
                if (x > 3.141592653589793) {
                    x = 3.141592653589793;
                }
                if (x > -0.001 && x < 0.001) {
                    x = 0.0;
                }
                y = (4 / PI) * ((sin(x)) + sumatoria(x, j));
                if (y > -0.001 && y < 0.001) {
                    y = 0.0;
                }
                cx++;
                cy = coordenada(y);
                l.add(new Pair<>(x, y));
                System.out.println();
            }
            coord.add(l);
        }
        GraficadorFourier lin = new GraficadorFourier(coord);
    }
}
