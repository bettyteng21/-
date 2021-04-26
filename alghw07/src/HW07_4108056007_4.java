public class HW07_4108056007_4 extends Buy_Phone
{
	public static void main(String[] args){
		HW07_4108056007_4 test= new HW07_4108056007_4();
		int[][] inputArr= {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
		int[][] ans= test.bestPhone(inputArr);
		for (int[] an : ans) {
			System.out.println(an[0] + "," + an[1]);
		}
	}

	public int[][] bestPhone(int[][] inputArr){
		int len= inputArr.length, len_minus1= inputArr.length-1, i, ans_index=0, curr=inputArr[len_minus1][1];
		mergeSort(inputArr);

		int[][] temp = new int[len][2];
		int[] max_of_y=new int[len];
		max_of_y[len_minus1]= -2147483648;
		for (i=len-2; i>=0; --i){
			max_of_y[i]=curr;
			if (inputArr[i][1]>curr) curr=inputArr[i][1];
		}

		for (i=0; i<len-1; ++i){
			if (inputArr[i][1]>max_of_y[i]) {
				temp[ans_index++]=inputArr[i];
			}
		}
		temp[ans_index++]=inputArr[len-1];

		int[][] ans = new int[ans_index][2];
		for (i=0; i<ans_index; ++i){
			ans[i]=temp[i];
		}
		return ans;
	}

	public static void mergeSort(int[][] array)
	{
		if(array == null) return;

		if(array.length > 1) {
			int mid = array.length / 2, i;
			int[][] left = new int[mid][2];
			for(i = 0; i < mid; i++) {
				left[i] = array[i];
			}

			int[][] right = new int[array.length - mid][2];
			for(i = mid; i < array.length; i++) {
				right[i - mid] = array[i];
			}
			mergeSort(left);
			mergeSort(right);

			i = 0;
			int j = 0, k=0;

			while(i < left.length && j < right.length)
			{
				if(left[i][0] < right[j][0]) {
					array[k] = left[i]; i++;
				}
				else if(left[i][0] == right[j][0]){
					if (left[i][1] >= right[j][1]){
						array[k] = right[j]; j++;
					}
					else{
						array[k] = left[i]; i++;
					}
				}
				else {
					array[k] = right[j]; j++;
				}
				k++;
			}

			while(i < left.length) {
				array[k] = left[i]; i++; k++;
			}
			while(j < right.length) {
				array[k] = right[j]; j++; k++;
			}
		}
	}
}
