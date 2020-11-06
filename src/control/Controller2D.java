package control;

import fill.SeedFill;
import model.Point;
import model.Polygon;
import rasterize.*;
import view.Panel;
import view.Window;

import javax.swing.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;
    private final Window window;
    private SeedFill seedFill;

    private Polygon polygon;

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
     }

     public void initInputs() {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), "clear");
        panel.getActionMap().put("clear",clear);
     }

     //ACTIONS
    /*
    Pokud má správně fungovat menu a mazání klávesou C, není možné použít KeyListener.
    KeyListener musí mít focus, který však mají tlačitka v menu.
     */

    Action clear = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            hardClear();
        }
    };



    @Override
    public void initListeners(Panel panel) {
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
                        polyRasterizer.drawPolygon(polygon);
                    } else if(window.getRadioFill().isSelected()) {
                        seedFill.setSeed(new Point(e.getX(),e.getY()));
                        seedFill.fill();
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
                update();
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
    }

    private void hardClear() {
        panel.clear();
        polygon = new Polygon();
    }

}
