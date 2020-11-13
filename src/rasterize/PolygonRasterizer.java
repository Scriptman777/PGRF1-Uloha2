package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.List;

public class PolygonRasterizer {

    private Raster raster;

    public PolygonRasterizer(Raster raster) {
        this.raster = raster;
    }

    //Vykreslí čáry ze kterých se polygon skládá
    public void drawPolygon(Polygon poly, Color color) {
        //Kontrola, že polygon má dostatek bodů
        if (poly.points.size() > 1)
        {
            Point first = poly.points.get(0);
            LineRasterizerGraphics fll = new LineRasterizerGraphics(raster);
            fll.setColor(color);


            for (int i = 1; i < poly.points.size(); i++) {
                fll.drawLine(poly.points.get(i - 1).x,poly.points.get(i - 1).y,poly.points.get(i).x,poly.points.get(i).y);

            }

            fll.drawLine(poly.points.get(poly.points.size() - 1).x,poly.points.get(poly.points.size() - 1).y,first.x,first.y);

        }


    }

}
