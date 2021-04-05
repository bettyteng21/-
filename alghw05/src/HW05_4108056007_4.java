public class HW05_4108056007_4 extends LLK
{
	boolean answer=false;
	public static void main(String[] args){
		HW05_4108056007_4 test= new HW05_4108056007_4();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}

	public static class Node{
		public double slope;
		public Node next;
		public Node(double slope, final Node next){
			this.slope= slope; this.next=next;
		}
	}

	public boolean checkLLK(int[][] array)
	{
		Thread[] T = new Thread[4];
		final int bucket = (int)((array.length) * 1.7);
		int thread_num;
		for (thread_num = 0; thread_num < 4; ++thread_num) {
			final int start_index=thread_num;
			T[thread_num]= new Thread(()->{
				int index; double slope;
				Node[] list_entry = new Node[bucket];
				for (int i = start_index; i < array.length; i+=5) {
					for (int j = i + 1; j < array.length; ++j) {
						slope = (double) (array[i][0] - array[j][0]) / (double) (array[i][1] - array[j][1]);
						index = (Double.valueOf(slope).hashCode() & 0x7fffffff) % bucket;
						if (contain(slope, list_entry, index)) {
							answer=true; break;
						}
						else {
							Node new_entry = new Node(slope, list_entry[index]);
							list_entry[index] = new_entry;
						}
					}
					list_entry = new Node[bucket];
				}
			});
			T[thread_num].start();
		}
		for (thread_num=0; thread_num<4; ++thread_num){
			try{
				T[thread_num].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return answer;
	}

	public boolean contain(double slope, Node[] list_entry, int index){
		for (Node curr = list_entry[index]; curr!=null; curr=curr.next){
			if (curr.slope==slope) return true;
		}
		return false;
	}
}
