package fill;

import model.Point;
import rasterize.Raster;

public class SeedFillPattern implements Filler {
    private final Raster raster;
    private Point seed;
    private PatternFill pattern;
    public SeedFillPattern(Raster raster) {
        this.raster = raster;
        this.pattern = pattern;
    }

    public void setPattern(PatternFill pattern) {
        this.pattern = pattern;
    }



    @Override
    public void fill() {
        seedFill(seed.x,seed.y,0x0000ff,raster.getPixel(seed.x,seed.y));

    }

    private void seedFill(int seedX,int seedY, int color, int backColor){
        if (raster.getPixel(seedX,seedY) == backColor){
            raster.setPixel(seedX,seedY,pattern.paint(seedX,seedY));
            seedFill(seedX+1,seedY,color,backColor);
            seedFill(seedX-1,seedY,color,backColor);
            seedFill(seedX,seedY+1,color,backColor);
            seedFill(seedX,seedY-1,color,backColor);
        }
    }


    public Point getSeed() {
        return seed;
    }

    public void setSeed(Point seed) {
        this.seed = seed;
    }
}
