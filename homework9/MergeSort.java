package hw9;

/**
 * merge sort without recursion
 *
 */

public class MergeSort {
    /**
     * set the first step of dividing sub arrays' length
     * @param arr the array waiting for being sorted
     */
    public static void sort(int[] arr)
    {
        //Merge-sort without recursion
        int len = arr.length;
        int k = 1;

        while(k < len)
        {
            mergePass(arr, k, len);
            k *= 2;
        }
    }

    /**
     * divide array into n/k pairs of subarrays
     * @param arr the array waiting for being sorted
     * @param k the length of every dividing sub array's length
     * @param n the length of original array
     */
    private static void mergePass(int[] arr, int k, int n)
    {
        int i = 0;

        //from front to end, divide array into n/k pairs of subarray each length is k
        while(i < n - 2*k + 1)
        {
            //sort and merge sub array
            merge(arr, i, i + k-1, i + 2*k - 1);
            i += 2*k;
        }

        //merge the remained part of original array, which doesn't have a pair subarray.
        if(i < n - k )
        {
            merge(arr, i, i+k-1, n-1);
        }

    }

    /*
    this part respond for couquer and combine
     */
    //combine and sorted two sorted array into one sorted array,
    private static void merge(int[] arr, int low, int mid, int high)
    {
        //array temp used for saving sorted result from 2 subarray.
        int[] temp = new int[high - low + 1];
        //左半边的指针
        int i = low;
        //右半边的指针
        int j = mid+1;
        //合并后数组的指针
        int k = 0;

        //conquer and combine sub array
        // sort element from 2 different array, and put it into temp array
        for(; i <= mid && j <= high; k++)
        {
            if(arr[i] < arr[j])
                temp[k] = arr[i++];
            else
                temp[k] = arr[j++];
        }

        //move remained element in one array into temp array
        while(i <= mid)
            temp[k++] = arr[i++];

        while(j <= high)
            temp[k++] = arr[j++];

        //move element in 'temp' array into original array 'arr'
        for(int l = 0; l < temp.length; l++)
            arr[low + l] = temp[l];
    }

}
