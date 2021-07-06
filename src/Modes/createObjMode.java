package Modes;

import Shapes.BasicObj;

import java.awt.event.MouseEvent;

public class createObjMode extends Mode{
    private String objType = null;
    private ShapeFactoryInterface factory = new ShapeFactory();

    public createObjMode(String objType){
        this.objType = objType;
    }

    public void mousePressed(MouseEvent e){
        BasicObj basicObj = factory.createObj(objType, e.getPoint());
        canvas.addShape(basicObj);
        canvas.repaint();
    }
}
