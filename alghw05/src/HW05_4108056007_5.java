public class HW05_4108056007_5 extends LLK
{
	boolean answer=false;
	public static void main(String[] args){
		HW05_4108056007_5 test= new HW05_4108056007_5();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}

	public class object{
		public double slope;
		public object next;
		public object(double slope, object next){
			this.slope=slope; this.next=next;
		}
	}

	public boolean checkLLK(int[][] array)
	{
		Thread[] T = new Thread[10];
		final int bucket = (1 << ((int) Math.ceil(Math.log10(array.length) / 0.3010)));
		int thread_num;
		for (thread_num = 0; thread_num < 10; ++thread_num) {
			final int start_index= array.length-1-thread_num;
			T[thread_num]= new Thread(()->{
				int index; double slope;
				object[] list_entry = new object[bucket];
				object new_entry, curr;
				for (int i = start_index; i >=0; i-=10) {
					for (int j = 0; j <i; ++j) {
						slope = (double)(array[i][0] - array[j][0]) / (double) (array[i][1] - array[j][1]);
						index = (Double.valueOf(slope).hashCode() & 0x7fffffff) % bucket;
						for (curr = list_entry[index]; curr!=null; curr=curr.next){
							if (curr.slope==slope) {
								answer=true; break;
							}
						}
						new_entry = new object(slope, list_entry[index]);
						list_entry[index] = new_entry;
					}
					if(answer) break;
					list_entry = new object[bucket];
				}
			});
			T[thread_num].start();
		}
		for (thread_num=0; thread_num<10; ++thread_num){
			try{
				T[thread_num].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return answer;
	}

}
