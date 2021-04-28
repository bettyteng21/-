public class HW07_4108056007_5 extends Buy_Phone
{
	public static void main(String[] args){
		HW07_4108056007_5 test= new HW07_4108056007_5();
		int[][] inputArr= {null,{2,4},{2,1},{4,4},{4,3},{5,1},{3,4},{10,2},{10,10}, {11,0}, {12,0}, null};
		int[][] ans= test.bestPhone(inputArr);
		for (int[] an : ans) {
			System.out.println(an[0] + ", " + an[1]);
		}
	}

	public int[][] bestPhone(int[][] inputArr){
		int total=inputArr.length, index=0;
		for (int i=0; i<inputArr.length; ++i){
			if (inputArr[i]==null) {
				--total; continue;
			}
			for (int j= inputArr.length-1; j>i; --j){
				if (inputArr[j]==null) continue;
				if (inputArr[i][0]<=inputArr[j][0] && inputArr[i][1]<=inputArr[j][1]){
					inputArr[i]=null; --total; break;
				}
			}
		}
		int[][] ans= new int[total][];
		for (int[] ints : inputArr) {
			if (ints != null) {
				ans[index] = ints;
				index++;
			}
		}
		return ans;
	}
}
