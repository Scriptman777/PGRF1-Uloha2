package fill;

public class PatternFillDots implements PatternFill {
    @Override
    public int paint(int x, int y) {
        if(x%2 == 0 && (y+1)%2 ==0) return 0xffffff;
        return 0x010101;

    }
}
