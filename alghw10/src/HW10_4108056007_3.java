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

    public int[] sorting(int[] A){
        int mid, i, j, k;
        int[] left, right;
        if (A.length > 1) {
            mid = A.length / 2;
            left = new int[mid];
            for (i=0; i < mid; ++i) {
                left[i] = A[i];
            }

            right = new int[A.length - mid];
            for (i = mid; i < A.length; ++i) {
                right[i-mid]= A[i];
            }
            sorting(left);
            sorting(right);

            i= 0; j= 0; k= 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    A[k] = left[i++];
                } else {
                    A[k] = right[j++];
                }
                ++k;
            }

            while (i < left.length) {
                A[k++] = left[i++];
            }
            while (j < right.length) {
                A[k++] = right[j++];
            }
        }
        return A;
    }
}
