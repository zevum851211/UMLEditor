package Modes;

import Shapes.BasicObj;
import Shapes.Line;

import java.awt.*;

public interface ShapeFactoryInterface {
    BasicObj createObj(String objType, Point p);
    Line createLine(String objType, Point start, Point end);
}
