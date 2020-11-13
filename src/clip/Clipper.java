package clip;

import model.Line;
import model.Point;
import model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Clipper {

    private class Vertex {
        double x,y;

        Vertex(Vertex vertex) {
            this.x = vertex.x;
            this.y = vertex.y;

        }

    }


    public static Polygon clip (Polygon inPoly,Polygon clipPoly) {
        Polygon outpoly = null;
        if (clipPoly.points.size()<=2){
            return inPoly;
        }
        List<Line> clipLines = new ArrayList<>();
        for (int i = 0; i < clipPoly.points.size()-1; i++) {
            clipLines.add(new Line(clipPoly.points.get(i),clipPoly.points.get(i+1),0));

        }
        clipLines.add(new Line(clipPoly.points.get(0),clipPoly.getLastPoint(),0));







        //TODO todoooo



        return outpoly;
    }


}
