package fill;


import model.Point;
import rasterize.Raster;

public class SeedFillBorder implements Filler {

    private final Raster raster;
    private Point seed;
    private int border;

    public SeedFillBorder(Raster raster) {
        this.raster = raster;

    }

    @Override
    public void fill() {
        seedFill(seed.x,seed.y,0x0000ff);

    }

    private void seedFill(int seedX,int seedY, int color){
        if (raster.getPixel(seedX,seedY) != border){
            raster.setPixel(seedX,seedY,color);
            seedFill(seedX+1,seedY,color);
            seedFill(seedX-1,seedY,color);
            seedFill(seedX,seedY+1,color);
            seedFill(seedX,seedY-1,color);
        }
    }


    public void setSeed(Point seed) {
        this.seed = seed;
    }

    public void setBorder(int color) {this.border = color;}

}
