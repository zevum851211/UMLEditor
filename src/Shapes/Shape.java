package Shapes;

import java.awt.*;
import java.util.List;

public abstract class Shape {
    protected int x1, y1, x2, y2;
    public boolean group_selected = false;
    public abstract void draw(Graphics g);

    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }


    public void resetLocation(){

    }

    public void resetLocation(int moveX, int moveY){

    }

    public void changeName(String name){

    }

    public void show(Graphics g){

    }

    public String inside(Point p){
        return null;
    }

    public Port getPort(int portIndex){
        return null;
    }

    public Shape getSelectedShape(){
        return null;
    }

    public void resetSelectedShape(){

    }

    public List<Shape> getShapes(){
        return null;
    }
}
