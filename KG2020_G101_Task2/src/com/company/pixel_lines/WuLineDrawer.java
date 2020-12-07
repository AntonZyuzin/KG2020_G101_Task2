package com.company.pixel_lines;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        Color c = new Color(0, 153, 255);

        int signX = (x2 - x1 >= 0 ? 1 : -1);
        int signY = (y2 - y1 >= 0 ? 1 : -1);

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int x = x1;
        int y = y1;
        int d = 0;
        int gradient;

        int length = Math.max(dx, dy);
        Color c1, c2;

        length++;
        if (dx >= dy) {
            while (length > 0) {
                if (dx == 0) {
                    gradient = 255;
                } else {
                    gradient = (255 * d) / (2 * dx);
                }

                c1 = setColor(255 - Math.abs(gradient), c);
                c2 = setColor(Math.abs(gradient), c);
                pd.drawPixel(x, y, c1);

                if (gradient > 0) {
                    pd.drawPixel(x, y + signY, c2);
                } else {
                    pd.drawPixel(x, y - signY, c2);
                }

                d += 2 * dy;
                if (d > dx) {
                    y += signY;
                    d -= 2 * dx;
                } else if (d < -dx) {
                    y -= signY;
                }
                x += signX;
                length--;
            }
        } else {

            while (length > 0) {
                if (dy == 0) {
                    gradient = 255;
                } else {
                    gradient = (255 * d) / (2 * dy);
                }

                c1 = setColor(255 - Math.abs(gradient), c);
                c2 = setColor(Math.abs(gradient), c);
                pd.drawPixel(x, y, c1);

                if (gradient > 0) {
                    pd.drawPixel(x + signX, y, c2);
                } else {
                    pd.drawPixel(x - signX, y, c2);
                }

                d += 2 * dx;
                if (d > dy) {
                    x += signX;
                    d -= 2 * dy;
                } else if (d < -dy) {
                    x -= signX;
                }
                y += signY;
                length--;
            }
        }

    }

    private Color setColor(int t, Color c) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), t);
    }

}