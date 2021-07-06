package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import Modes.Mode;
import Shapes.Group;
import Shapes.Shape;


public class Canvas extends JPanel {
    private static Canvas instance = null;
    private List<Shape> shapes = new ArrayList<>();
    protected Mode currentMode;
    private EventListener listener = null;
    public Shape selectedObj = null;
    public Rectangle SelectedArea = new Rectangle();
    public Shape tempLine = null;


    public Canvas(){
        setBackground (Color.white);
    }

    //singleton
    public static Canvas getInstance(){
        if(instance == null){
            instance = new Canvas();
        }
        return instance;
    }

    public void setCurrentMode(){
        removeMouseListener((MouseListener) listener);
        removeMouseMotionListener((MouseMotionListener) listener);
        listener = currentMode;
        addMouseListener((MouseListener) listener);
        addMouseMotionListener((MouseMotionListener) listener);
    }

    public List<Shape> getShapeList(){
        return this.shapes;
    }

    public void addShape(Shape shape){
        shapes.add(shape);
    }

    public void groupShape(){
        Group group = new Group();
        for(int i = 0; i < shapes.size(); i++){
            Shape shape = shapes.get(i);
            if(shape.group_selected){
                group.addShapes(shape);
                shapes.remove(i);
                i--;
            }
        }
        group.setBounds();
        shapes.add(group);
    }

    public void reset() {
        if(selectedObj != null){
            selectedObj.resetSelectedShape();   // for selected shape inside the group
            selectedObj = null;
        }
        SelectedArea.setBounds(0, 0, 0, 0);
    }

    private boolean checkSelectedArea(Shape shape){
        Point upperLeft = new Point(shape.getX1(), shape.getY1());
        Point lowerRight = new Point(shape.getX2(), shape.getY2());

        if(SelectedArea.contains(upperLeft) && SelectedArea.contains(lowerRight)){
            return true;
        }
        return false;
    }


    public void removeGroup(){
        Group group = (Group) selectedObj;
        List<Shape> groupShapes = group.getShapes();
        for(int i = 0; i < groupShapes.size(); i++){
            Shape shape = groupShapes.get(i);
            shapes.add(shape);
        }
        shapes.remove(selectedObj);
    }

    public void changeName(String name){
        if(selectedObj != null){
            selectedObj.changeName(name);
            repaint();
        }
    }

    public void paint(Graphics g){

        //set canvas area (important!!!!)
        Dimension dim = getSize();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, dim.width, dim.height);

        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));

        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            shape.draw(g);
            shape.group_selected = false;
            /* check group select */
            if (!SelectedArea.isEmpty() && checkSelectedArea(shape)) {
                shape.show(g);
                shape.group_selected = true;
            }
        }

        if (!SelectedArea.isEmpty()) {
            int alpha = 85; // 33% transparent
            g.setColor(new Color(37, 148, 216, alpha));
            g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
            g.setColor(new Color(37, 148, 216));
            g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
        }

        if(tempLine != null){
            tempLine.draw(g);
        }

        if(selectedObj != null){
            selectedObj.show(g);
        }
    }
}
