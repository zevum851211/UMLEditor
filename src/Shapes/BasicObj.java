package Shapes;

import java.awt.*;

public abstract class BasicObj extends Shape{
    private int offset = 5;
    protected int width, height;
    protected String objName = "Object Name";
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    protected Port[] ports = new Port[4];
    public abstract void draw(Graphics g);

    // show ports
    public void show(Graphics g){
        for(int i = 0; i < ports.length; i++){
            g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
        }
    }

    public String inside(Point p){
        int centerX = (x1+x2)/2;
        int centerY = (y1+y2)/2;

        Point[] points = {new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2)};

        for(int i = 0; i < points.length; i++){
            Polygon polygon = new Polygon();
            int nextPointIndex = (i + 1) % 4;
            polygon.addPoint(points[i].x, points[i].y);
            polygon.addPoint(points[nextPointIndex].x, points[nextPointIndex].y);
            polygon.addPoint(centerX, centerY);

            if(polygon.contains(p)){
                return Integer.toString(i);
            }
        }
        return null;
    }

    public void resetLocation(int moveX, int moveY){

        int x1 = this.x1 + moveX;
        int y1 = this.y1 + moveY;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;

        int[] xPoint = {(x1+x2)/2, x2, (x1+x2)/2, x1};
        int[] yPoint = {y1, (y1+y2)/2, y2, (y1+y2)/2};

        for(int i = 0; i < ports.length; i++){
            ports[i].setPort(xPoint[i], yPoint[i], offset);
            ports[i].resetLine();
        }
    }

    public Port getPort(int portIndex){
        return ports[portIndex];
    }

    public void changeName(String name){
        this.objName = name;
    }

    public void createPorts(){
        int[] xPoint = {(x1+x2)/2, x2, (x1+x2)/2, x1};
        int[] yPoint = {y1, (y1+y2)/2, y2, (y1+y2)/2};

        for(int i = 0; i < ports.length; i++){
            Port port = new Port();
            port.setPort(xPoint[i], yPoint[i], offset);
            ports[i] = port;
        }
    }
}
