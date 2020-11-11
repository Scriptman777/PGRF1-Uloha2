package sort;

import model.Point;

import java.util.List;

public class BubbleSort {

    public static List<Point> sortPointsByX(List<Point> list) {

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


/*
        int n = list.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (list.get(j).x > list.get(j+1).x)
                {
                    // swap arr[j+1] and arr[j]
                    Point temp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }
        }
        return list;

*/



    }



}
