public class HW06_4108056007_1 extends Dessert_Desert
{

	public static void main(String[] args){
		HW06_4108056007_1 test= new HW06_4108056007_1();
		int[][] inputArr={{2,2,2,1,1,1}, {1,1,1,1,1,1,1}, {1,3,5,7,9},{1,2,3}, {5,4,3,2,1},{2,1,3,2},{6,1,5,8,3,7,9},{7,1,2,4,6,5,3},{2,3,1,5,4}};
		int[] answer= test.maxBlocks(inputArr);
		for(int i=0; i<inputArr.length;++i){
			System.out.println(answer[i]);
		}
	}

	public int[] maxBlocks(int[][] inputArr){
		int[] ans= new int[inputArr.length];
		int j, maxLeft;
		for (int i=0; i< inputArr.length; ++i){
			ans[i]=1;
			int[] minRight= new int[inputArr[i].length];

			minRight[inputArr[i].length-1]=inputArr[i][inputArr[i].length-1];
			for(j=(inputArr[i].length-2); j>=0; --j){
				minRight[j]= (minRight[j+1]>inputArr[i][j])? inputArr[i][j]:minRight[j+1];
			}

			maxLeft=inputArr[i][0];
			for(j=1; j<inputArr[i].length; ++j){
				if(minRight[j]>=maxLeft) {
					++ans[i]; maxLeft=inputArr[i][j];
				}
				else maxLeft= (maxLeft>inputArr[i][j])? maxLeft:inputArr[i][j];
			}
		}
		return ans;
	}
}
