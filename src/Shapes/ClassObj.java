package Shapes;

import java.awt.*;

public class ClassObj extends BasicObj{
    public ClassObj(int x1, int y1){
        this.width = 100;
        this.height = 120;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
        createPorts();
    }

    public void draw(Graphics g) {
        g.drawRect(x1, y1, width, height);

        int body = height/3;
        g.drawLine(x1, y1 + body, x2, y1 + body);
        g.drawLine(x1, y1 + body * 2, x2, y1 + body * 2);

        int stringWidth = g.getFontMetrics(font).stringWidth(objName);
        double empty = (Math.abs(x1-x2) - stringWidth) / 2;
        g.setFont(font);
        g.drawString(objName, x1 + (int)empty, y1 + 25);
    }
}
