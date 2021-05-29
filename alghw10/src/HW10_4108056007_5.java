public class HW10_4108056007_5 extends SortingArray{
    static int i, j;
    public static void main(String[] args){
        HW10_4108056007_5 test= new HW10_4108056007_5();
        int[] A= {-1,2,5,9,8,7,1,3,2};
        A=test.sorting(A);
        for (int i=0; i<A.length; ++i){
            System.out.print(A[i]+", ");
        }
        System.out.println();
    }

    public int[] sorting(int[] A){
        quicksort(A, 0, A.length-1);
        return A;
    }

    static void partition(int a[], int l, int r) {
        i = l - 1; j = r;
        int p = l - 1, q = r, temp, k;
        int v = a[r];

        while (true) {
            while (a[++i] < v) ;

            while (v < a[--j]){
                if (j == l) break;
            }

            if (i >= j) break;

            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            if (a[i] == v) {
                p++;
                temp = a[i];
                a[i] = a[p];
                a[p] = temp;

            }

            if (a[j] == v) {
                q--;
                temp = a[q];
                a[q] = a[j];
                a[j] = temp;
            }
        }

        temp = a[i];
        a[i] = a[r];
        a[r] = temp;

        j = i - 1;
        for (k = l; k < p; k++, j--) {
            temp = a[k];
            a[k] = a[j];
            a[j] = temp;
        }

        i = i + 1;
        for (k = r - 1; k > q; k--, i++) {
            temp = a[i];
            a[i] = a[k];
            a[k] = temp;
        }
    }

    static void quicksort(int a[], int l, int r)
    {
        if (r <= l) return;
        i = 0; j = 0;
        partition(a, l, r);
        quicksort(a, l, j);
        quicksort(a, i, r);
    }
}
