package control;

import clip.Clipper;
import fill.*;
import model.Point;
import model.Polygon;
import rasterize.*;
import view.Panel;
import view.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;
    private final Window window;
    private SeedFill seedFill;
    private SeedFillPattern seedFillPattern;

    private Polygon polygon;
    private Polygon clipPolygon;

    private int x,y;
    private LineRasterizerGraphics rasterizer;
    private PolygonRasterizer polyRasterizer;

    public Controller2D(Window window) {
        this.window = window;
        this.panel = window.getPanel();

        initObjects(panel.getRaster());
        initListeners(panel);
        initInputs();
    }

    public void initObjects(Raster raster) {
        seedFill = new SeedFill(raster);
        rasterizer = new LineRasterizerGraphics(raster);
        polyRasterizer = new PolygonRasterizer(raster);
        polygon = new Polygon();
        clipPolygon = new Polygon();
        seedFillPattern= new SeedFillPattern(raster);
     }

     public void initInputs() {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), "clear");
        panel.getActionMap().put("clear",clear);
     }

     //ACTIONS
    /*
    Pokud má správně fungovat menu a mazání klávesou C, není možné použít KeyListener.
    KeyListener musí mít focus, který však mají tlačitka v menu po kliknutí.
     */

    Action clear = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            hardClear();
        }
    };


    @Override
    public void initListeners(Panel panel) {

        window.getButtonClip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clip();
            }
        });


        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {

                    if (window.getRadioNewPoly().isSelected()) {
                        polygon.points.add(new Point(e.getX(),e.getY()));
                        update();

                    } else if(window.getRadioFill().isSelected()) {

                        seedFill.setSeed(new Point(e.getX(),e.getY()));
                        seedFill.fill();

                    } else if(window.getRadioClip().isSelected()) {
                        clipPolygon.points.add(new Point(e.getX(),e.getY()));
                        update();
                    }
                    else if(window.getRadioPattern().isSelected()) {
                        seedFillPattern.setSeed(new Point(e.getX(),e.getY()));
                        seedFillPattern.setPattern(new PatternFillDots());
                        seedFillPattern.fill();
                    }
                    else if(window.getRadioPattern2().isSelected()) {
                        seedFillPattern.setSeed(new Point(e.getX(),e.getY()));
                        seedFillPattern.setPattern(new PatternFillLines());
                        seedFillPattern.fill();
                    }




                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {

                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        //TODO
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        //TODO
                    }
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                }
                //update();
            }
        });



        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void update() {
        panel.clear();
        polyRasterizer.drawPolygon(polygon,Color.WHITE);
        polyRasterizer.drawPolygon(clipPolygon, Color.CYAN);
    }

    private void hardClear() {
        panel.clear();
        polygon = new Polygon();
        clipPolygon = new Polygon();
    }

    private void clip() {

        polygon = Clipper.clip(polygon,clipPolygon);
        //Podmínka pro případ, že ořezávaný polygon má včechny body mimo ořezávací polygon a vytvoří se prázdný
        if (polygon.points.size() > 2) {
            ScanLine sl = new ScanLine(polygon,rasterizer,0xffff00,0xff0000);
            sl.fill();
        }



    }

}
