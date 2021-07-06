package Modes;

import Shapes.*;

import java.awt.*;

public class ShapeFactory implements ShapeFactoryInterface{
    public BasicObj createObj(String objType, Point p){
        if(objType.equals("Class")){
            return new ClassObj(p.x, p.y);
        }else if(objType.equals("UseCase")){
            return new UseCaseObj(p.x, p.y);
        }
        return null;
    }

    public Line createLine(String lineType, Point start, Point end){
        if(lineType.equals("AssociationLine")){
            return new AssociationLine(start.x, start.y, end.x, end.y);
        }
        else if(lineType.equals("CompositionLine")){
            return new CompositionLine(start.x, start.y, end.x, end.y);
        }
        else if(lineType.equals("GeneralizationLine")){
            return new GeneralizationLine(start.x, start.y, end.x, end.y);
        }
        return null;
    }

}
