public class HW05_4108056007_1 extends LLK
{
	public static void main(String[] args){
		HW05_4108056007_1 test= new HW05_4108056007_1();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}

	public class node{
		public double slope;
		public node next;
		public node(double slope, node next){
			this.slope=slope; this.next=next;
		}
	}

	public boolean checkLLK(int[][] array){
		int bucket=(1 << ((int) Math.ceil(Math.log10(array.length) / 0.3010))), index;
		double slope;
		node[] list_entry= new node[bucket];
		for(int i=array.length-1; i>=0; --i){
			for (int j=i-1; j>=0; --j){
				slope= (double)(array[i][0]-array[j][0]) / (double)(array[i][1]-array[j][1]);
				index= (Double.valueOf(slope).hashCode() & 0x7fffffff) % bucket;
				if (contain(slope, list_entry, index)) return true;
				else {
					node new_entry= new node(slope, list_entry[index]);
					list_entry[index]= new_entry;
				}
			}
			list_entry= new node[bucket];
		}
		return false;
	}

	public boolean contain(double slope, node[] list_entry, int index){
		for (node curr = list_entry[index]; curr!=null; curr=curr.next){
			if (curr.slope==slope) return true;
		}
		return false;
	}
}
