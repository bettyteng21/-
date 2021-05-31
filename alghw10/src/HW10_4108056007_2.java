public class HW10_4108056007_2 extends SortingArray{
    public static void main(String[] args){
        HW10_4108056007_2 test= new HW10_4108056007_2();
        int[] A= {-1,2,5,9,8,7,1,3,2};
        A=test.sorting(A);
        for (int i=0; i<A.length; ++i){
            System.out.print(A[i]+", ");
        }
        System.out.println();
    }

    void merge(int[] arr, int l, int m, int r)
    {
        if (arr[m-1]<=arr[m]) return;
        int n2 = r-m, i;
        int[] temp= new int[r-l];
        System.arraycopy(arr, l, temp, 0, r-l);

        i = 0;
        int j = 0, k = l;
        while (i != m && j != n2) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            }
            else {
                arr[k++] = temp[j++];
            }
        }

        while (i < m) {
            arr[k++] = temp[i++];
        }

        while (j < n2) {
            arr[k++] = temp[j++];
        }
    }

    void sort(int arr[], int l, int r)
    {
        if (r-l < 10){
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

        int m =((l+r)>>>2);
        sort(arr, l, m);
        sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public int[] sorting(int[] A){
        if (A == null) return null;
        sort(A, 0, A.length-1);
        return A;
    }
}
