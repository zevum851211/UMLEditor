package Shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Port extends Rectangle{
    private List<Line> lines = new ArrayList<>();

    public void setPort(int centerX, int centerY, int offset){
        int x = centerX - offset;
        int y = centerY - offset;
        int height = offset * 2;
        int width = offset * 2;
        setBounds(x, y, height, width);
    }

    public void addLine(Line line){
        lines.add(line);
    }

    public void removeLine(Line line){
        lines.remove(line);
    }

    public void resetLine(){
        for(int i = 0; i < lines.size(); i++){
            Line line = lines.get(i);
            line.resetLocation();
        }
    }
}
