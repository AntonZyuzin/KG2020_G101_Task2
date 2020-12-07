package com.company.pixel_lines;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        int signX = (x2 - x1 >= 0 ? 1 : -1);
        int signY = (y2 - y1 >= 0 ? 1 : -1);

        int dx = Math.abs(x2 - x1);//B
        int dy = Math.abs(y2 - y1);//A

        int length = Math.max(dx, dy);

        if (length == 0) {
            Color c = new Color(99, 132, 0);
            pd.drawPixel(x1, y1, c);
        }

        if (dy < dx) {
            int x = x1;
            int y = y1;
            int d = -dx;

            length++;
            while (length > 0) {
                Color c = new Color(99, 132, 0);
                pd.drawPixel(x, y, c);
                x += signX;
                d += 2 * dy;
                if (d > 0) {
                    d -= 2 * dx;
                    y += signY;
                }
                length--;
            }
        } else {
            int x = x1;
            int y = y1;
            int d = -dy;

            length++;
            while (length> 0) {
                Color c = new Color(99, 132, 0);
                pd.drawPixel(x, y, c);
                y += signY;
                d += 2 * dx;
                if (d > 0) {
                    d -= 2 * dy;
                    x += signX;
                }
                length--;
            }
        }
    }
}