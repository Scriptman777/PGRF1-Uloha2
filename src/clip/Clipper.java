package clip;

import model.Line;
import model.Polygon;

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
        List<Line> clipLines = null; //TODO: Fill





//TODO todoooo



        return outpoly;
    }


}
