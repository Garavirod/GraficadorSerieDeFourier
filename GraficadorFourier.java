/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lineas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rodrigo
 */
public class GraficadorFourier extends JFrame {

    JButton btn;
    Graphics gra;
    Lienzo cen;
    double angulo;
    LinkedList<LinkedList<Pair>> listaCord;

    public GraficadorFourier(LinkedList<LinkedList<Pair>> L) {
        super("Fourier");
        this.listaCord = L;
        cen = new Lienzo();
        cen.repaint();
        add(cen);        
        btn = new JButton("Paint Lienas");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Hilo().start();
            }
        });
        add(btn, "South");
        setLocation(200, 250);
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class Lienzo extends JPanel {
        Graphics gl;
        public void pintarLienzo(){
            gl.setColor(Color.green);
            this.setBackground(Color.black);
            gl.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
            gl.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
            //Señal cuadrada
            gl.setColor(Color.blue);
            gl.drawLine(0, 400, this.getWidth()/2, 400);
            gl.drawLine(800, this.getHeight() / 4, this.getWidth()/2, this.getHeight() / 4);
            gl.drawLine(this.getWidth()/2, this.getHeight() / 2, this.getWidth()/2, this.getHeight() / 4);
            gl.drawLine(this.getWidth()/2,getHeight()/2,getWidth()/2,400);    
        }
        
        public void paint(Graphics g) {
            super.paintComponent(g);
            gl = g;
            pintarLienzo();
            gra = getGraphics();            
        }
    }

    public class Hilo extends Thread {

        public Color colorA() {
            Random rd = new Random();
            int r = rd.nextInt(256);
            int g = rd.nextInt(256);
            int b = rd.nextInt(256);
            return new Color(r, g, b);
        }

        private double coord_x(double x) {
            double real_x = x + getWidth() / 2;
            return real_x;
        }

        private double coord_y(double y) {
            double real_y = -y + getHeight() / 2;
            return (real_y);
        }

        public void despinta(LinkedList<Pair> lp) {
            gra.setColor(Color.black);
            for (int i = 0; i <= lp.size() - 2; i++) {
                double x_1 = (double) lp.get(i).getKey() * 130;
                double y_1 = (double) lp.get(i).getValue() * 130;
                double x_2 = (double) lp.get(i + 1).getKey() * 130;
                double y_2 = (double) lp.get(i + 1).getValue() * 130;
                gra.drawLine((int) coord_x(x_1), (int) coord_y(y_1), (int) coord_x(x_2), (int) coord_y(y_2));
                try {
                    Thread.sleep((long) 2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GraficadorFourier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }

        public void pinta(LinkedList<Pair> lp) {
            gra.setColor(Color.PINK);
            for (int i = 0; i <= lp.size() - 2; i++) {
                double x_1 = (double) lp.get(i).getKey() * 130;
                double y_1 = (double) lp.get(i).getValue() * 130;
                double x_2 = (double) lp.get(i + 1).getKey() * 130;
                double y_2 = (double) lp.get(i + 1).getValue() * 130;
                gra.drawLine((int) coord_x(x_1), (int) coord_y(y_1), (int) coord_x(x_2), (int) coord_y(y_2));
                try {
                    Thread.sleep((long) 1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GraficadorFourier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void grafica() {            
            for (int k = 0; k < listaCord.size(); k++) {
                LinkedList<Pair> lp = listaCord.get(k);
                try {
                    pinta(lp);
                    Thread.sleep((long) 15);
                    despinta(lp);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GraficadorFourier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void run() {
            System.out.println("HILO EJECUTADO");
            grafica();
        }
    }
    
}
