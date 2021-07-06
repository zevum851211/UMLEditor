package Modes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import Shapes.Line;
import Shapes.Shape;

public class createLineMode extends Mode{
    private String lineType = null;
    private ShapeFactoryInterface factory = new ShapeFactory();
    private Point startP = null;
    private List<Shape> shapes;
    private int portIndex_start = -1, portIndex_end = -1;
    private Shape shape_1 = null, shape_2 = null;

    public createLineMode(String lineType){
        this.lineType = lineType;
    }

    public void mousePressed(MouseEvent e){
        shapes = canvas.getShapeList();

        startP = findConnectedObj(e.getPoint(), "first");
    }

    public void mouseDragged(MouseEvent e){
        if(startP != null){
            Line line = factory.createLine(lineType, startP, e.getPoint());
            canvas.tempLine = line;
            canvas.repaint();
        }
    }

    public void mouseReleased(MouseEvent e){
        Point endP = null;
        if(startP != null){
            endP = findConnectedObj(e.getPoint(), "second");

            if(endP != null){
                Line line = factory.createLine(lineType, startP, endP);
                canvas.addShape(line);

                line.setPorts(shape_1.getPort(portIndex_start), shape_2.getPort(portIndex_end));

                shape_1.getPort(portIndex_start).addLine(line);
                shape_2.getPort(portIndex_end).addLine(line);
            }

            canvas.tempLine = null;
            canvas.repaint();
            startP = null;
        }
    }

    private Point findConnectedObj(Point p, String target){
        for(int i = 0; i < shapes.size(); i++){
            Shape shape = shapes.get(i);

            int portIndex;
            String judgeInside = shape.inside(p);

            if(judgeInside != null && judgeInside != "insideLine"){
                if(judgeInside == "insideGroup") {
                    shape = shape.getSelectedShape();
                    portIndex = Integer.parseInt(shape.inside(p));
                }else{
                    portIndex = Integer.parseInt(judgeInside);
                }

                switch(target){
                    case "first":
                        shape_1 = shape;
                        portIndex_start = portIndex;
                        break;
                    case "second":
                        shape_2 = shape;
                        portIndex_end = portIndex;
                        break;
                }

                Point portLocation = new Point();
                portLocation.setLocation(shape.getPort(portIndex).getCenterX(), shape.getPort(portIndex).getCenterY());
                return portLocation;
            }
        }
        return null;
    }
}
