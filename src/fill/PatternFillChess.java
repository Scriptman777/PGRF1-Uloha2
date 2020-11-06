package fill;

public class PatternFillChess implements PatternFill {
    @Override
    public int paint(int x, int y) {
       if (x%2==0) return 0xffffff;
       return 0x010101;
    }



}
