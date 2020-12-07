package com.company;

import com.company.pixel_lines.BresenhamLineDrawer;
import com.company.pixel_lines.DDALineDrawer;
import com.company.pixel_lines.WuLineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class DrawPanel extends JPanel implements MouseMotionListener {
    private Point2D position = new Point(0, 0);

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gr = bi.createGraphics();
        gr.setColor(Color.white);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.black);

        PixelDrawer pd = new GraphicsPixelDrawer(gr);
        System.out.println("Выберите алгоритм отрисовки\n 1) Алгоритм Брезенхэма\n 2) Алгоритм Ву\n 3) Алгоритм DDA");

        //LineDrawer ld = new DDALineDrawer(pd);
        LineDrawer ld = new WuLineDrawer(pd);
        //LineDrawer ld = new BresenhamLineDrawer(pd);
        drawAll(ld);


        g.drawImage(bi, 0, 0, null);
        gr.dispose();
    }

    private void drawAll(LineDrawer ld) {
        drawSnowFlake(ld, 200, 200, 100, 50);
        ld.drawLine(getWidth() / 2, getHeight() / 2, (int) position.getX(), (int) position.getY());
    }


    public static void drawSnowFlake(LineDrawer ld, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double a = da * i;
            double x1 = r * Math.cos(a);
            double y1 = r * Math.sin(a);
            ld.drawLine(x, y, x + (int) x1, y + (int) y1);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();
    }
}
