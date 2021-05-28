public class HW10_4108056007_1 extends SortingArray{
    public static void main(String[] args){
        HW10_4108056007_1 test= new HW10_4108056007_1();
        int[] A= {-1,2,5,9,8,7,1,3,2};
        A=test.sorting(A);
        for (int i=0; i<A.length; ++i){
            System.out.print(A[i]+", ");
        }
        System.out.println();
    }

    int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high], i = (low - 1), temp;
        for(int j = low; j <= high - 1; ++j) {
            if (arr[j] < pivot)
            {
                temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return (i+1);
    }

    void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public int[] sorting(int[] A){
        quickSort(A, 0, A.length-1);
        return A;
    }
}
