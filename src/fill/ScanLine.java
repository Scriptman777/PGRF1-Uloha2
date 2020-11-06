package fill;

import model.Line;
import model.Polygon;

import java.util.List;

public class ScanLine implements Filler {

    private Polygon poly; //Hranice
    private List<Line> lines;
    private int fillColor;
    private int borderColor;




    @Override
    public void fill() {




    }

    private void process() {
        int yMin=0, yMax=0;

        for (int i=0; i<poly.points.size()-1;i++){
            Line ln = new Line(poly.points.get(i),poly.points.get(i+1),0xffff00);

            if (!ln.isHorizontal()){
                lines.add(ln.setOrientation());
            }

            //TODO najdi Min a Max

        }

        Line ln = new Line(poly.points.get(0),poly.points.get(poly.points.size()-1),0xffff00);
        if (!ln.isHorizontal()){
            lines.add(ln.setOrientation());
        }


        List<Integer> intersections = null;
        for (int y = yMax; y>yMin; y--){
            for (Line line: lines) {
                if(line.isIntercection(y)){
                    int x = line.getIntersection(y);
                    intersections.add(x);
                    //TODO: Sort the bloody thing
                }
            }

        }
        for (int i=0;i<intersections.size()-1;i+=2) {
            //TODO draw lines with rasterizer podle barvy
        }

        //TODO Vykreslit outline





    }

}
