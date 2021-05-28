public class HW10_4108056007_4 extends SortingArray{
    int MIN_MERGE = 32;
    public static void main(String[] args){
        HW10_4108056007_4 test= new HW10_4108056007_4();
        int[] A= {-1,2,5,9,8,7,1,3,2};
        A=test.sorting(A);
        for (int i=0; i<A.length; ++i){
            System.out.print(A[i]+", ");
        }
        System.out.println();
    }

    public int minRunLength(int n)
    {
        assert n>=0;
        int r= 0;
        while (n>=MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return (n+r);
    }

    public static void insertionSort(int[] arr, int left, int right)
    {
        for (int i = left + 1, temp, j; i<=right; i++) {
            temp = arr[i];
            j= i-1;
            while (j >= left && arr[j] > temp) {
                arr[j+1]= arr[j--];
            }
            arr[j+1]= temp;
        }
    }

    public static void merge(int[] arr, int l, int m, int r)
    {
        int len1 = (m-l+1), len2 = (r-m), i, j, k;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (i = 0; i < len1; ++i) {
            left[i] = arr[l+i];
        }
        for (i = 0; i < len2; ++i){
            right[i] = arr[(m+1+i)];
        }

        i = 0; j = 0; k = l;
        while (i < len1 && j < len2){
            if (left[i] <= right[j]){
                arr[k] = left[i++];
            }
            else {
                arr[k] = right[j++];
            }
            ++k;
        }

        while (i < len1){
            arr[k++] = left[i++];
        }

        while (j < len2){
            arr[k++] = right[j++];
        }
    }

    public int[] sorting(int[] A) {
        int minRun = minRunLength(MIN_MERGE), n=A.length;

        for (int i = 0; i < n; i += minRun) {
            insertionSort(A, i, Math.min((i+MIN_MERGE-1), (n-1)));
        }

        for (int size = minRun, mid, right, left; size < n; size*=2) {
            for (left = 0; left < n; left += (2*size)) {
                mid = (left+size-1);
                right = Math.min((left+(2* size)-1), (n-1));
                if(mid<right) merge(A, left, mid, right);
            }
        }
        return A;
    }
}

