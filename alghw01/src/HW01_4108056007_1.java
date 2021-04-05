public class HW01_4108056007_1 extends ArrayData
{
	public HW01_4108056007_1(int [] A){
		this.A=A;
	}
	public static void main (String[] args){
		long startTime = System.nanoTime();
		int[] A={2,4,6,6,2,14,6,7,4,63,1,654,756,8534,5,6,4,7,243,65,3765,658,7689,3,1,4,5};
		HW01_4108056007_1 test= new HW01_4108056007_1(A);
		System.out.println(test.max());
		System.out.println(test.min());
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
	}

	public int max(){
		int Max= A[0];
		for (int i=1; i<A.length; i++){
			if (Max<A[i]) Max= A[i];
		}
		return Max;
	}
	public int min(){
		int Min=A[0];
		for (int i=1; i<A.length; i++){
			if (Min>A[i]) Min= A[i];
		}
		return Min;




	}
}














