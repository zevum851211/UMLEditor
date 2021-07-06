package Shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class Line extends Shape{
    public abstract void draw(Graphics g);
    protected Port[] ports = new Port[2];
    private String flag;

    public void setPorts(Port portStart, Port portEnd){
        this.ports[0] = portStart;
        this.ports[1] = portEnd;
    }
    // change color if line is selected
    public void show(Graphics g){
        g.setColor(new Color(0, 204, 0));
        this.draw(g);
        g.setColor(new Color(0, 0, 0));
    }
    public void resetLocation(){
        this.x1 = (int) ports[0].getCenterX();
        this.y1 = (int) ports[0].getCenterY();
        this.x2 = (int) ports[1].getCenterX();
        this.y2 = (int) ports[1].getCenterY();
    }

    public void resetPort(Port port, Line line){
        port.addLine(line);
        if(flag == "start"){
            this.ports[0].removeLine(line);
            this.ports[0] = port;
        }else if(flag == "end"){
            this.ports[1].removeLine(line);
            this.ports[1] = port;
        }
    }

    public void resetStartEnd(Point p){
        if(flag == "start"){
            this.x1 = p.x;
            this.y1 = p.y;
        }else if(flag == "end"){
            this.x2 = p.x;
            this.y2 = p.y;
        }
    }

    public String inside(Point p){
        int error = 5;
        if(calDistance(p) < error){
            double distanceToStart = Math.sqrt(Math.pow(p.x - x1, 2) + Math.pow(p.y - y1, 2));
            double distanceToEnd = Math.sqrt(Math.pow((p.x - x2), 2) + Math.pow((p.y - y2), 2));
            if(distanceToStart < distanceToEnd){
                flag = "start";
            }else{
                flag = "end";
            }
            return "insideLine";
        }else{
            return null;
        }
    }

    private double calDistance(Point p){
        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        return line.ptLineDist(p.getX(), p.getY());
    }
}
