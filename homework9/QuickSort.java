package hw9;

public class QuickSort {

    public void sort(int[] arr, int low, int high) {
        if (arr.length <= 0) return;
        if (low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //pivot
        while (left < right) {
            while (left < right && arr[right] >= temp) {  //search from right to left, if left< right, exchange left & right
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {   //search from left to right, if right < left, exchange right & left
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        //divide and conquer
        sort(arr, low, left - 1);
        sort(arr, left + 1, high);
    }
}
