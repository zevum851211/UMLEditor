package Modes;

import Shapes.Line;
import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class SelectMode extends Mode{
    private List<Shape> shapes;
    private Point startP = null;
    private String judgeInside = null;
    private Line selectedLine = null;

    public void mousePressed(MouseEvent e){
        startP = e.getPoint();
        shapes = canvas.getShapeList();

        canvas.reset();

        for(int i = shapes.size() - 1; i >= 0; i--){
            Shape shape = shapes.get(i);
            judgeInside = shape.inside(e.getPoint());
            if(judgeInside != null){
                canvas.selectedObj = shape;
                break;
            }
        }

        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e){
        int moveX = e.getX() - startP.x;
        int moveY = e.getY() - startP.y;

        if(canvas.selectedObj != null){
            if(judgeInside == "insideLine"){
                selectedLine = (Line) canvas.selectedObj;
                selectedLine.resetStartEnd(e.getPoint());
            }else{
                canvas.selectedObj.resetLocation(moveX, moveY);
            }
            startP.x = e.getX();
            startP.y = e.getY();
        }

        else{
            if(e.getX() > startP.x)
                canvas.SelectedArea.setBounds(startP.x, startP.y, Math.abs(moveX), Math.abs(moveY));
            else
                canvas.SelectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));
        }
        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e){
        if(canvas.selectedObj != null){
            if(judgeInside == "insideLine"){
                selectedLine = (Line) canvas.selectedObj;
                reconnectLine(e.getPoint());
            }
        }

        else{
            canvas.SelectedArea.setSize(Math.abs(e.getX() - startP.x), Math.abs(e.getY() - startP.y));
        }
        canvas.repaint();
    }

    private void reconnectLine(Point p){
        for(int i = 0; i < shapes.size(); i++){
            Shape shape = shapes.get(i);
            int portIndex;
            String judgeInside = shape.inside(p);
            if(judgeInside != null && judgeInside != "insideLine"){
                if(judgeInside == "insideGroup"){
                    shape = shape.getSelectedShape();
                    portIndex = Integer.parseInt(shape.inside(p));
                }else{
                    portIndex = Integer.parseInt(judgeInside);
                }

                selectedLine.resetPort(shape.getPort(portIndex), selectedLine);
                selectedLine.resetLocation();
            }
        }
    }
}
