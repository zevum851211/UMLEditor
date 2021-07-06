package Shapes;

import java.awt.*;

public class UseCaseObj extends BasicObj{
    public UseCaseObj(int x1, int y1){
        this.width = 120;
        this.height = 100;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
        createPorts();

    }
    @Override
    public void draw(Graphics g) {
        g.drawOval(x1, y1, width, height);

        int stringWidth = g.getFontMetrics(font).stringWidth(objName);
        double empty = (Math.abs(x1-x2) - stringWidth) / 2;
        g.setFont(font);
        g.drawString(objName, x1 + (int)empty, y1 + 50);
    }
}
