public class HW03_4108056007_1 extends HillFinding
{
	public static void main (String[] args){
		HW03_4108056007_1 test= new HW03_4108056007_1();
		int[] A={1, 1, 1, 1, 1, 8, 1, 1};
		System.out.println(test.H_Finding(A));
	}

	public int H_Finding(int[] A){
		int mid, low=0, high=A.length-1, end=A.length-1;
		while(low<=high){
			mid= (high+low)>>>1;

			if (A[mid+1]<A[mid]) return end-1-mid;
			else if (A[mid]<A[mid-1]) return end-mid;

			if(A[high]>A[mid]) high=(mid-1);
			else /*if(A[low]<A[mid])*/ low= (mid+1);
			//else break;
		}
		return (extreme_case(A));
	}

	int extreme_case(int[] A){
		int min=A[0], index=-1;
		for (int i=1; min<=A[i]; i++){
			min=A[i]; index=i+1;
		}
		return A.length-1-index;
	}
}
