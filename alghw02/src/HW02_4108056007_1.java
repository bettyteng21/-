public class HW02_4108056007_1 extends ThreeSum
{
	public static void main(String[] args){
		long startTime = System.nanoTime();
		HW02_4108056007_1 test= new HW02_4108056007_1();
		int[] A={-1, 0, 1};
		System.out.println(test.T_sum(A));
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
	}
	static class multi_thread extends Thread{
		int[] A;
		int[] count;
		int thread_num;
		int index;
		public multi_thread(int[] A, int[] count, int thread_num, int index){
			this.A=A; this.count=count; this.thread_num=thread_num; this.index=index;
		}
		public void run(){
			int l, r, last;
			for (int i=index; i<A.length-2; i+=thread_num) {
				if (A[i] >= 0) break;
				r = A.length - 1;
				l = i + 1;
				last= -A[i];
				while (l < r) {
					if (A[l] + A[r]== last) {
						++count[index];
						++l; --r;
					} else if ( A[l] + A[r] < last) ++l;
					else --r;
				}
			}
		}
	}

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
		if(A.length<3) return 0;
		sort(A, 0, A.length-1);
		int[] count={0,0,0,0,0};
		multi_thread th0 = new multi_thread(A, count, 5, 0);
		multi_thread th1= new multi_thread(A, count, 5, 1);
		multi_thread th2= new multi_thread(A, count, 5, 2);
		multi_thread th3= new multi_thread(A, count, 5, 3);
		multi_thread th4= new multi_thread(A, count, 5, 4);
		th0.start();
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		try{
			th0.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			th1.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			th2.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			th3.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			th4.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		return (count[0]+count[1]+count[2]+count[3]+count[4]);
	}

}