package hw9;


import java.util.Arrays;
import java.util.Random;

public class SortingComparison {


    //import method to create elapseTime graph;
    public static long comparingSort(int[] arr, String s) {

        int[] arr1 = arr.clone(); //clone a temp array list for testing every sort method.

        long startTime = System.currentTimeMillis();
        //execute insertion sort;
        if (s.matches("insertionsort")) {
            InsertionSort is = new InsertionSort();
            is.sort(arr1);
        }
        //execute quick sort;
        if (s.matches("quicksort")) {
            QuickSort qs = new QuickSort();
            qs.sort(arr1, 0, arr1.length - 1);
        }
        //execute merge sort;
        if (s.matches("mergesort")) {
            MergeSort ms = new MergeSort();
            ms.sort(arr1);
        }
        //execute heap sort;
        if (s.matches("heapsort")) {
            HeapSort hs = new HeapSort();
            hs.sort(arr1);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return elapsedTime;

    }

    public static void main(String[] args) {
        Random r = new Random();
        //save all running time data into a 2-D array.
        String[][] data = new String[4][11];
        //each row of this array is a sort method running time
        data[0][0] = "Insertion-Sort";
        data[1][0] = "Quick-Sort";
        data[2][0] = "Merge-Sort";
        data[3][0] = "Heap-Sort";

        //this line shows the table's title
        String[] title = {"n\nalgorithms", "10000", "20000", "30000", "40000", "50000", "60000", "70000", "80000", "90000", "100000"};
        for (int i = 1; i <= 2; i++) {
            int[] a = new int[i * 10000];
            for (int j = 0; j < i * 10000; j++) {
                a[j] = r.nextInt(1000000) + 1;
            }

            //test array
//            int[] a = new int[i*10];
//            for (int j = 0; j < i * 10; j++) {
//                a[j] = r.nextInt(100) + 1;
//            }
            //insert each running result into array
            data[0][i] = String.valueOf(SortingComparison.comparingSort(a, "insertionsort"));
            data[1][i] = String.valueOf(SortingComparison.comparingSort(a, "quicksort"));
            data[2][i] = String.valueOf(SortingComparison.comparingSort(a, "mergesort"));
            data[3][i] = String.valueOf(SortingComparison.comparingSort(a, "heapsort"));
//            System.out.print(Arrays.toString(a));
        }
        TimeComplexityTable table = new TimeComplexityTable(data, title);
        XYChart chart = new XYChart();
        //add each agorithm line into graph
        chart.addLine(title, data[0]);
        chart.addLine(title, data[1]);
        chart.addLine(title, data[2]);
        chart.addLine(title, data[3]);
        new TimeCompGroup(table, chart);
    }
}


