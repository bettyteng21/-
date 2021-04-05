public class HW03_4108056007_2 extends HillFinding
{
	static int ans_index=-1;
	public static void main(String[] args){
		int[] A={5, 6, 1, 2, 3, 4};
		HW03_4108056007_2 test= new HW03_4108056007_2();
		System.out.println(test.H_Finding(A));
	}

	static class multi_thread extends Thread{
		int[] A;
		int start_index;
		int end_index;
		public multi_thread(int[] A, int start_index, int end_index){
			this.A=A; this.start_index=start_index; this.end_index= end_index;
		}
		public void run(){
			for (int i=start_index; i<end_index; ++i){
				if(A[i+1]-A[i]<0) ans_index=i;
			}
		}
	}

	public int H_Finding(int[] A){
		int par1= (A.length-1)/5, par2=par1*2, par3= par1*3, par4=par1*4;

		multi_thread th0 = new multi_thread(A,0, par1);
		multi_thread th1= new multi_thread(A,par1+1, par2);
		multi_thread th2= new multi_thread(A, par2+1, par3);
		multi_thread th3= new multi_thread(A,par3+1, par4);
		multi_thread th4= new multi_thread(A,par4+1, A.length-1);
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

		if (ans_index==-1){
			if (A[par1]>A[par1+1]) ans_index=par1;
			else if(A[par2]>A[par2+1]) ans_index=par2;
			else if(A[par3]>A[par3+1]) ans_index=par3;
			else if(A[par4]>A[par4+1]) ans_index=par4;
		}
		return ans_index;
	}
}
