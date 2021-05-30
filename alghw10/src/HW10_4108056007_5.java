public class HW10_4108056007_5 extends SortingArray{
    public static void main(String[] args){
        HW10_4108056007_5 test= new HW10_4108056007_5();
        int[] A= {-1,2,5,9,8,7,1,3,2};
        A=test.sorting(A);
        for (int i=0; i<A.length; ++i){
            System.out.print(A[i]+", ");
        }
        System.out.println();
    }

    void mergeSort(int[] A, int start, int finish){
        if (A == null) return;

        int i, j, k, len=(finish-start+1), count, left_len, right_len;
        if (len > 1) {
            int mid = len>>>1;
            int[] left = new int[mid];
            left_len= left.length;
            for (i=start, count=0; i<(mid+start); ++i) {
                left[count++] = A[i];
            }

            int[] right = new int[len-mid];
            right_len= right.length;
            for (i = start+mid, count=0; i <= finish; ++i) {
                right[count++]= A[i];
            }

            mergeSort(left, 0, left_len-1);
            mergeSort(right, 0, right_len-1);

            i= 0; j= 0; k= start;
            while (i < left_len && j < right_len) {
                if (left[i] < right[j]) {
                    A[k] = left[i++];
                } else {
                    A[k] = right[j++];
                }
                ++k;
            }
            while (i < left_len) {
                A[k++] = left[i++];
            }
            while (j < right_len) {
                A[k++] = right[j++];
            }
        }
    }

    void thread_merge(int[] A, int start1, int finish1, int start2, int finish2){
        int i, count, j, k;
        int[] left= new int[(finish1-start1+1)];
        int left_len= left.length;
        for (i=start1, count=0; i<=finish1; ++i){
            left[count++]= A[i];
        }
        int[] right= new int[(finish2-start2+1)];
        int right_len= right.length;
        for (i=start2, count=0; i<=finish2; ++i){
            right[count++]= A[i];
        }

        i= 0; j= 0; k= start1;
        while (i < left_len && j < right_len) {
            if (left[i] < right[j]) {
                A[k] = left[i++];
            } else {
                A[k] = right[j++];
            }
            ++k;
        }
        while (i < left_len) {
            A[k++] = left[i++];
        }
        while (j < right_len) {
            A[k++] = right[j++];
        }
    }

    public int[] sorting(int[] A){
        Thread[] T = new Thread[4];
        final int thread_len= A.length>>>2, final_index=A.length-1;
        int thread_num;
        for (thread_num=0; thread_num<4; ++thread_num){
            int curr_thread = thread_num;
            T[thread_num]= new Thread(()->{
                if (curr_thread==3){
                    mergeSort(A, curr_thread*thread_len, final_index);
                }
                else{
                    mergeSort(A, curr_thread*thread_len, ((curr_thread+1)*thread_len)-1);
                }
            });
            T[thread_num].start();
        }
        for(thread_num=0; thread_num<4; ++thread_num){
            try{
                T[thread_num].join();
            }
            catch (InterruptedException e) {}
        }
        thread_merge(A, 0, thread_len-1, thread_len, (thread_len<<1)-1);
        thread_merge(A, (thread_len<<1), (3*thread_len)-1, (3*thread_len), final_index);
        thread_merge(A, 0, (thread_len<<1)-1, (thread_len<<1), final_index);
        return A;
    }
}