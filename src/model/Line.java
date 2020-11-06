package model;

public class Line {

    private final int  x1;
    private final int x2;
    private final int y1;
    private final int y2;
    private final int color;

    public Line(int x1, int y1, int x2, int y2, int color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public Line(Point p1, Point p2, int color) {
        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
        this.color = color;
    }


    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getColor() {
        return color;
    }

    public boolean isHorizontal(){
        return y1 == y2;
    }


    public Line setOrientation() {

        if (y1 > y2){
            return this;
        }
        else {
            return new Line(x2,y2,x1,y1,0xffff00);
        }




    }

    public boolean isIntercection(int y) {
        return ((y>=y1) && (y<=y2));

    }

    public int getIntersection(int y) {
        return 0; //x=ky+q;

    }
}
