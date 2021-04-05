public class HW02_4108056007_3 extends ThreeSum
{
	private static void sort(int[] A, int left, int right){
		if (left>=right) return;
		int key= A[left], i=left, j=right, temp;
		while(i!=j) {
			while (A[j] > key && i < j) --j;
			while (A[i] <= key && i < j) ++i;

			if (i < j) {
				temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		temp= A[left];
		A[left]= A[i];
		A[i] = temp;

		sort(A, left, i-1);
		sort(A, i+1, right);
	}

	public int T_sum(int[] A){
		sort(A, 0, A.length-1);
		int count=0, l, r;
		for (int i=0; i<A.length-2; ++i){
			if (A[i]>=0) break;
			r= A.length-1;
			l= i+1;
			while(l<r){
				if (A[i]+A[l]+A[r]==0){
					++count; ++l; --r;
				}
				else if (A[i]+A[l]+A[r]<0) ++l;
				else --r;
			}
		}
		return count;
	}
	public static void main(String[] args){
		HW02_4108056007_3 test= new HW02_4108056007_3();
		int[] A={-1, 0, 1};
		System.out.println(test.T_sum(A));
	}
}
