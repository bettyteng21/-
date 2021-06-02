public class HW10_4108056007_3 extends SortingArray{
    public static void main(String[] args){
        HW10_4108056007_3 test= new HW10_4108056007_3();
        int[] A= {-1,2,5,9,8,7,1,3,2};
        A=test.sorting(A);
        for (int i=0; i<A.length; ++i){
            System.out.print(A[i]+", ");
        }
        System.out.println();
    }
    void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1, n2 = r - m, i, j, k;
        int[] L= new int[n1];
        int[] R= new int[n2];

        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        i = 0; j = 0; k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            }
            else {
                arr[k] = R[j++];
            }
            k++;
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    void sort(int arr[], int l, int r)
    {
        if ((r-l) < 10){
            for (int i = l + 1, temp, j; i<=r; ++i) {
                temp = arr[i];
                j= i-1;
                while (j >= l && arr[j] > temp) {
                    arr[j+1]= arr[j--];
                }
                arr[j+1]= temp;
            }
            return;
        }

        int m =l+((r-l)>>>2);
        sort(arr, l, m);
        sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public int[] sorting(int[] A){
        sort(A, 0, A.length-1);
        return A;
    }
}