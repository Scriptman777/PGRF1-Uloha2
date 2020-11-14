package fill;

import model.Line;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerGraphics;
import sort.BubbleSort;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScanLine implements Filler {

    private Polygon poly;
    private List<Line> lines;
    private int fillColor;
    private int borderColor;
    private LineRasterizerGraphics rasterizer;

    public ScanLine(Polygon poly, LineRasterizerGraphics rasterizer,int fillColor, int borderColor) {
        this.poly = poly;
        this.rasterizer = rasterizer;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        lines = new ArrayList<Line>();
    }




    @Override
    public void fill() {
        //V teorii zbytečné volání další funkce, chtěl jsem však zachovat kód ze cvičení.
        process();

    }

    private void process() {
        int yMin=10000;
        int yMax=0;

        for (int i=0; i<poly.points.size()-1;i++){
            Line ln = new Line(poly.points.get(i),poly.points.get(i+1),0xffff00);

            if (!ln.isHorizontal()){
                lines.add(ln.setOrientation());
            }
            if (ln.setOrientation().getY1()>yMax) {
                yMax = ln.setOrientation().getY1();
            }
            if (ln.setOrientation().getY2()<yMin) {
                yMin = ln.setOrientation().getY2();
            }


        }

        Line ln = new Line(poly.points.get(0),poly.points.get(poly.points.size()-1),0xffff00);
        if (!ln.isHorizontal()){
            lines.add(ln.setOrientation());
        }
        if (ln.setOrientation().getY1()>yMax) {
            yMax = ln.setOrientation().getY1();
        }
        if (ln.setOrientation().getY2()<yMin) {
            yMin = ln.setOrientation().getY2();
        }


        //Nalezení průsečíků a vykreslení čar mezi nimi
        List<Point> intersections = new ArrayList<>();
        List<Point> tempIntersections = new ArrayList<>();
        for (int y = yMax; y>yMin; y--){
            for (Line line: lines) {
                if(line.isIntercection(y)){
                    int x = line.getIntersection(y);
                    tempIntersections.add(new Point(x,y));


                }
            }
            tempIntersections = BubbleSort.sortPointsByX(tempIntersections);
            intersections.addAll(tempIntersections);
            tempIntersections.clear();
        }

        rasterizer.setColor(fillColor);
        for (int i=0;i<intersections.size()-1;i+=2) {
            rasterizer.drawLine(intersections.get(i).x,intersections.get(i).y,intersections.get(i+1).x,intersections.get(i+1).y);
        }


        //Vykreslení okrajů
        rasterizer.setColor(borderColor);

            Point first = poly.points.get(0);
            for (int i = 1; i < poly.points.size(); i++) {
                rasterizer.drawLine(poly.points.get(i - 1).x,poly.points.get(i - 1).y,poly.points.get(i).x,poly.points.get(i).y);

            }
            rasterizer.drawLine(poly.points.get(poly.points.size() - 1).x,poly.points.get(poly.points.size() - 1).y,first.x,first.y);








    }

}
