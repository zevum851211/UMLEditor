package Shapes;

import java.awt.*;

public class CompositionLine extends Line{
    private int arrowW = 10, arrowH = 10;

    public CompositionLine(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(x1, y1, x2, y2);

        int dx = x2 - x1, dy = y2 - y1;
        double length = Math.sqrt(dx*dx + dy*dy);
        double xm = length - arrowW, xn = xm, ym = arrowH, yn = -arrowH, x;
        double sin = dy/length, cos = dx/length;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        double xq = (arrowH*2/length)*x1 + ((length-arrowH*2)/length)*x2;
        double yq = (arrowH*2/length)*y1 + ((length-arrowH*2)/length)*y2;

        int[] xpoints = {x2, (int) xm, (int) xq, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yq, (int) yn};

        g.drawPolygon(xpoints, ypoints, 4);
    }
}
