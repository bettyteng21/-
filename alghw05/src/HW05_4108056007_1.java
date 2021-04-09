public class HW05_4108056007_1 extends LLK
{
	public static void main(String[] args){
		HW05_4108056007_1 test= new HW05_4108056007_1();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}

	public static class Node{
		public double slope;
		public Node next;
		public Node(double slope, Node next){
			this.slope= slope; this.next=next;
		}
	}

	public boolean checkLLK(int[][] array){
		int index;
		double slope;
		final int bucket = (1 << ((int) Math.ceil(Math.log10(array.length) / 0.3010)));
		Node[] list_entry= new Node[bucket];
		Node curr, new_entry;
		for(int i=array.length-1; i>=0; --i){
			slope= (double)(array[i][0]) / (double)(array[i][1]);
			index= (Double.valueOf(slope).hashCode() & 0x7fffffff) % bucket;
			for (curr = list_entry[index]; curr!=null; curr=curr.next){
				if (curr.slope==slope) return true;
			}
			new_entry= new Node(slope, list_entry[index]);
			list_entry[index]= new_entry;
		}
		return false;
	}
}
