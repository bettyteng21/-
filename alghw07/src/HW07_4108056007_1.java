public class HW07_4108056007_1 extends Buy_Phone
{
	public static void main(String[] args){
		HW07_4108056007_1 test= new HW07_4108056007_1();

		int[][] inputArr= {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
		int[][] ans= test.bestPhone(inputArr);

		for (int[] an : ans) {
			System.out.println(an[0] + "," + an[1]);
		}
	}

	public int[][] bestPhone(int[][] inputArr){
		int j, ans_index=0;
		int[][] temp = new int[inputArr.length][2];
		boolean flag;
		for (int i=0; i< inputArr.length-1; ++i) {
			flag = false;
			for (j = i + 1; j < inputArr.length; ++j) {
				if (inputArr[i][0] <= inputArr[j][0] && inputArr[i][1] <= inputArr[j][1]) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				temp[ans_index][0] = inputArr[i][0];
				temp[ans_index++][1] = inputArr[i][1];
			}
		}
		int[][] ans = new int[ans_index][2];
		for (j=0; j<ans_index; ++j){
			ans[j]=temp[j];
		}
		quickSort(ans, 0, ans.length-1);
		return ans;
	}

	static int partition(int[][] arr, int low, int high)
	{
		int pivot = arr[high][0], i = (low - 1), temp1, temp2;

		for(int j = low; j <= high - 1; j++) {
			if (arr[j][0] < pivot)
			{
				temp1=arr[++i][0];
				arr[i][0]=arr[j][0];
				arr[j][0]=temp1;
				temp2=arr[i][1];
				arr[i][1]=arr[j][1];
				arr[j][1]=temp2;
			}

		}
		temp1=arr[i+1][0];
		arr[i+1][0]=arr[high][0];
		arr[high][0]=temp1;
		temp2=arr[i+1][1];
		arr[i+1][1]=arr[high][1];
		arr[high][1]=temp2;

		return (i + 1);
	}

	static void quickSort(int[][] arr, int low, int high)
	{
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
}
