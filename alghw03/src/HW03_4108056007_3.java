public class HW03_4108056007_3 extends HillFinding
{
	public static void main (String[] args){
		HW03_4108056007_3 test= new HW03_4108056007_3();
		int[] A={2, 3, -1, 0, 1};
		System.out.println(test.H_Finding(A));
	}

	public int H_Finding(int[] A){
		int mid, low=0, high=A.length-1;
		while(low<=high){
			mid= low+(high-low)/2;
			if (high==low) return A.length-1-low;

			if (mid<high && A[mid+1]<A[mid]) return A.length-2-mid;
			else if (mid>low && A[mid]<A[mid-1]) return A.length-1-mid;

			if(A[high]>A[mid]) high=(mid-1);
			else low= (mid+1);
		}
		return -1;
	}
}
