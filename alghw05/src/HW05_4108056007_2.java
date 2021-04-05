public class HW05_4108056007_2 extends LLK
{
	public static void main(String[] args){
		HW05_4108056007_2 test= new HW05_4108056007_2();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}

	public boolean checkLLK(int[][] array){
		hashMap h= new hashMap(1 << ((int) Math.ceil(Math.log10(array.length) / 0.3010)));
		for(int i=0; i< array.length; ++i){
			for (int j=i+1; j<array.length; ++j){
				if (h.contain(((double) (array[i][0]-array[j][0]))/((double) (array[i][1]-array[j][1])))) return true;
				else h.put(((double) (array[i][0]-array[j][0]))/((double) (array[i][1]-array[j][1])));
			}
			h.reset();
		}
		return false;
	}

	public class Entry{
		public double slope;
		public Entry next;
	}

	public class hashMap{
		int capacity;
		Entry[] list_entry;
		hashMap(int capacity){
			this.capacity= capacity; this.list_entry= new Entry[this.capacity];
		}

		public boolean contain(double slope){
			int index= (Double.valueOf(slope).hashCode() & 0x7fffffff) % this.capacity;
			for (Entry curr= list_entry[index]; curr!=null; curr=curr.next){
				if (curr.slope==slope) return true;
			}
			return false;
		}

		public void put(double slope){	//put if not contain
			if (!contain(slope)){
				int index= (Double.valueOf(slope).hashCode() & 0x7fffffff) % this.capacity;
				Entry new_entry= new Entry();
				new_entry.slope= slope;
				new_entry.next= list_entry[index];
				list_entry[index]= new_entry;
			}
		}
		public void reset(){
			this.list_entry= new Entry[this.capacity];
		}
	}
}
