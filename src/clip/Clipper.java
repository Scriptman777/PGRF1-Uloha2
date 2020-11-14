package clip;

import model.Line;
import model.Point;
import model.Polygon;
import sort.BubbleSort;

import java.util.ArrayList;
import java.util.List;

public class Clipper {



    public static Polygon clip(Polygon inPoly,Polygon clipPoly) {
        Polygon outpoly = new Polygon();
        if (clipPoly.points.size()<=2){
            return inPoly;
        }
        List<Line> clipLines = new ArrayList<>();
        for (int i = 0; i < clipPoly.points.size()-1; i++) {
            clipLines.add(new Line(clipPoly.points.get(i),clipPoly.points.get(i+1),0));

        }
        clipLines.add(new Line(clipPoly.points.get(0),clipPoly.getLastPoint(),0));

        Point p1 = inPoly.getLastPoint();
        Point temp;
        if (isInside(clipLines,p1)) {
            outpoly.points.add(p1);
        }


            //Algoritmus na nalezení bodů nového polygonu dle přednášky (Sutherland-Hodgman)
            //Pokud přímka prochází ořezávacím polygonem, ale její body jsou mimo tento polygon, je ignorována. Nepodařilo se mi najít tyto body ve správném pořadí.
            for (Point p2:inPoly.points) {
                if (isInside(clipLines,p1) && isInside(clipLines,p2)) {
                    outpoly.points.add(p2);
                }
                else if (isInside(clipLines,p1) && !isInside(clipLines,p2)) {
                    for (Line ln: clipLines) {
                        temp = findIntersection(ln,p1,p2);
                        if(temp != null) {outpoly.points.add(temp);}
                    }
                }
                else if (!isInside(clipLines,p1) && isInside(clipLines,p2)) {
                    for (Line ln: clipLines) {
                        temp = findIntersection(ln,p1,p2);
                        if(temp != null) {outpoly.points.add(temp);}
                    }
                    outpoly.points.add(p2);
                }

                p1 = p2;
            }




        return outpoly;
    }

    private static boolean isInside(List<Line> lines, Point p) {
        //Algoritmus je založen na části ScanLine. Zjišťuje, jestli je bod mezi lichým a sudým průsečíkem. Pokud ano, víme, že je uvnitř polygonu.
        int y = p.y;

        boolean out = false;
        List<Integer> intersections = new ArrayList<>();
        for (Line ln: lines) {
            if (ln.setOrientation().isIntercection(y)){
                intersections.add(ln.setOrientation().getIntersection(y));
            }
        }
        intersections = BubbleSort.sortInts(intersections);

        for (int i = 0; i < intersections.size(); i=i+2) {
            if (intersections.get(i) < p.x && p.x < intersections.get(i+1)) {
                out = true;
            }
        }

        return out;
    }

    private static Point findIntersection(Line ln1, Point p3, Point p4) {
        //Funkce nachází průsečík dvou čar, jedna je dána Line z ořezávacího polygonu, druhá dvěma body z ořezávaného polygonu. Podmínka na konci (0<t<1) zajišťuje, že bod se nachází na úsečce ořezávaného polygonu
        double x1,x2,x3,x4,y1,y2,y3,y4;

        x3 = ln1.getX1(); y3 = ln1.getY1();
        x4 = ln1.getX2(); y4 = ln1.getY2();
        x1 = p3.x; y1 = p3.y;
        x2 = p4.x; y2 = p4.y;


        double tTop = (x1-x3)*(y3-y4)-(y1-y3)*(x3-x4);
        double bottom = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
        double t = (tTop)/(bottom);

        int newX = (int) (x1+t*(x2-x1));
        int newY = (int) (y1+t*(y2-y1));



        if (0 < t && t < 1) {
            return new Point(newX,newY);
        }

        //Vím, že vracet null není dobrý nápad kvůli vyjímkám, ale tato funkce je vždy volána z bloku, který kontroluje, jestli byl průsečík nalezen.
        return null;



    }





}
