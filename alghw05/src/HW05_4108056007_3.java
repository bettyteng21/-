public class HW05_4108056007_3 extends LLK
{
	public static void main(String[] args){
		HW05_4108056007_3 test= new HW05_4108056007_3();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}

	public static class entry{
		public double slope;
		public entry next;
	}

	public boolean checkLLK(int[][] array){
		int capacity=(int)((array.length)*1.7), index;
		double slope;
		entry[] list_entry= new entry[capacity];
		for(int i=0; i< array.length; ++i){
			for (int j=i+1; j<array.length; ++j){
				slope= (double)(array[i][0]-array[j][0]) / (double)(array[i][1]-array[j][1]);
				index= (Double.valueOf(slope).hashCode() & 0x7fffffff) % capacity;
				if (contain(slope, list_entry, index)) return true;
				else {
					entry new_entry= new entry();
					new_entry.slope= slope;
					new_entry.next= list_entry[index];
					list_entry[index]= new_entry;
				}
			}
			list_entry= new entry[capacity];
		}
		return false;
	}

	public boolean contain(double slope, entry[] list_entry, int index){
		for (entry curr = list_entry[index]; curr!=null; curr=curr.next){
			if (curr.slope==slope) return true;
		}
		return false;
	}
}
