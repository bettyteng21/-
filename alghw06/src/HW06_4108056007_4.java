public class HW06_4108056007_4 extends Dessert_Desert
{
	public static void main(String[] args){
		HW06_4108056007_4 test= new HW06_4108056007_4();
		int[][] inputArr={{2,2,2,1,1,1}, {1,1,1,1,1,1,1}, {1,3,5,7,9},{1,2,3}, {5,4,3,2,1},{2,1,3,2},{6,1,5,8,3,7,9},{7,1,2,4,6,5,3},{2,3,1,5,4}};
		int[] answer= test.maxBlocks(inputArr);
		for(int i=0; i<inputArr.length;++i){
			System.out.println(answer[i]);
		}
	}

	public int[] maxBlocks(int[][] inputArr){
		int[] ans= new int[inputArr.length];
		Thread[] T = new Thread[5];
		int thread_num;
		for (thread_num=0; thread_num<5; ++thread_num){
			final int start_index= thread_num;
			T[thread_num]= new Thread(()->{
				int j, maxLeft, len;
				for (int i=start_index; i< inputArr.length; i+=5){
					ans[i]=1; len= inputArr[i].length;
					int[] minRight= new int[len];
					minRight[len-1]=inputArr[i][len-1];
					for(j=(len-2); j>=0; --j){
						minRight[j]= (minRight[j+1]>inputArr[i][j])? inputArr[i][j]:minRight[j+1];
					}
					maxLeft=inputArr[i][0];
					for(j=1; j<len; ++j){
						if(minRight[j]>=maxLeft) {
							++ans[i]; maxLeft=inputArr[i][j];
						}
						else maxLeft= (maxLeft>inputArr[i][j])? maxLeft:inputArr[i][j];
					}
				}
			});
			T[thread_num].start();
		}
		for(thread_num=0; thread_num<5; ++thread_num){
			try{
				T[thread_num].join();
			}
			catch (InterruptedException e) {}
		}
		return ans;
	}
}
