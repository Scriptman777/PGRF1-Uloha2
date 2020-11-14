package fill;

public class PatternFillLines implements PatternFill {
    @Override
    public int paint(int x, int y) {
        if(x%5 > 1) return 0xffffff;
        return 0x010101;

    }
}
