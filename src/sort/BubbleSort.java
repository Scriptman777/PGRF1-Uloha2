package sort;

import model.Point;

import java.util.List;

public class BubbleSort {

    public static List<Point> sortPointsByX(List<Point> list) {


        /*
        Pro seřazení jsem použil upravenou verzi algoritmu BubbleSort.
        Nahrazením jednoho for cyklu while cyklem nemusíme řešit kontrolu seřazenosti. Pokud nebyly vyměněny žádné prvky, je seznam seřazen.
        Třída umí třídit body podle souřadnice x (pro ScanLine) a int hodnoty.
         */

        boolean sorted = false;
        Point temp;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).x > list.get(i+1).x){
                    temp = list.get(i);
                    list.set(i,list.get(i+1));
                    list.set(i+1,temp);
                    sorted = false;
                }
            }

        }
        return list;
    }

    public static List<Integer> sortInts(List<Integer> list) {

        boolean sorted = false;
        int temp;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i+1)){
                    temp = list.get(i);
                    list.set(i,list.get(i+1));
                    list.set(i+1,temp);
                    sorted = false;
                }
            }

        }
        return list;
    }



}
